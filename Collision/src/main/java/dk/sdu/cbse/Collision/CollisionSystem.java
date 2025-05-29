package dk.sdu.cbse.Collision;

import dk.sdu.cbse.Common.data.Entity;
import dk.sdu.cbse.Common.data.GameData;
import dk.sdu.cbse.Common.data.World;
import dk.sdu.cbse.Common.services.IPostEntityProcessingService;
import dk.sdu.cbse.CommonAsteroid.IAsteroidSplitter;
import dk.sdu.cbse.Enemy.Enemy;
import dk.sdu.cbse.Player.Player;
import dk.sdu.cbse.bullet.Bullet;
import javafx.application.Platform;

import java.util.*;

public class CollisionSystem implements IPostEntityProcessingService {
    private final Set<String> checkedPairs = new HashSet<>();

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> entities = new ArrayList<>(world.getEntities());

        for (Entity a : entities) {
            for (Entity b : entities) {
                if (a == b) continue;


                String pairKey=makeKey(a, b);
                if (checkedPairs.contains(pairKey)) continue;
                checkedPairs.add(pairKey);

                if (collides(a, b)) {
                    handleCollision(a, b, world, gameData);
                }
            }
        }
    }

    private boolean collides(Entity a, Entity b) {
        float dx = (float) (a.getX() - b.getX());
        float dy = (float) (a.getY() - b.getY());
        float distanceSquared = dx * dx + dy * dy;
        float radiusSum = a.getRadius() + b.getRadius();

        return distanceSquared < radiusSum * radiusSum;
    }

    private void handleCollision(Entity a, Entity b, World world,GameData gameData) {
        // Bullet rammer Player (kun hvis bullet IKKE er skudt af Player selv)
        if (a instanceof Bullet && b instanceof Player) {
            if (!"Player".equals(((Bullet) a).getOwnerType())) {
                world.removeEntity(b);
                world.removeEntity(a);
                Platform.exit();
            }
            return;
        } else if (b instanceof Bullet && a instanceof Player) {
            if (!"Player".equals(((Bullet) b).getOwnerType())) {
                world.removeEntity(a);
                world.removeEntity(b);
                Platform.exit();

            }
            return;
        }

// Bullet rammer Enemy (kun hvis bullet er skudt af Player)
        if (a instanceof Bullet && b instanceof Enemy) {
            if ("Player".equals(((Bullet) a).getOwnerType())) {
                world.removeEntity(b);
                world.removeEntity(a);
            }
            return;
        } else if (b instanceof Bullet && a instanceof Enemy) {
            if ("Player".equals(((Bullet) b).getOwnerType())) {
                world.removeEntity(a);
                world.removeEntity(b);
            }
            return;
        }

// Bullet rammer Asteroid (kun hvis bullet er skudt af Player)
        if (a instanceof Bullet && isAsteroid(b)) {
            if ("Player".equals(((Bullet) a).getOwnerType())) {
                splitAsteroid(b, world);
                world.removeEntity(a);
                world.removeEntity(b);
                gameData.incrementAsteroidsDestroyed();
            }
            return;
        } else if (b instanceof Bullet && isAsteroid(a)) {
            if ("Player".equals(((Bullet) b).getOwnerType())) {
                splitAsteroid(a, world);
                world.removeEntity(b);
                world.removeEntity(a);
            }
            return;
        }

// Player dør ved kontakt med Asteroid
        if (a instanceof Player && isAsteroid(b) || b instanceof Player && isAsteroid(a)) {
            world.removeEntity(a instanceof Player ? a : b);
            Entity player = a instanceof Player ? a : b;
            world.removeEntity(player);
            Platform.exit();
            return;
        }
    }
    private String makeKey(Entity a, Entity b) {
        String id1 = a.getID().toString();
        String id2 = b.getID().toString();
        return id1.compareTo(id2) < 0 ? id1 + "-" + id2 : id2 + "-" + id1;
    }

    private boolean isAsteroid(Entity e) {
        return e.getClass().getSimpleName().toLowerCase().contains("asteroid");
    }

    private void splitAsteroid(Entity asteroid, World world) {
        for (IAsteroidSplitter splitter : ServiceLoader.load(IAsteroidSplitter.class)) {
            splitter.splitAsteroid(asteroid, world);
        }
    }
}

