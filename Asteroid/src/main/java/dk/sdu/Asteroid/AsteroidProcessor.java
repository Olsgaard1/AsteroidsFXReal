package dk.sdu.Asteroid;

import dk.sdu.cbse.Common.data.Entity;
import dk.sdu.cbse.Common.data.GameData;
import dk.sdu.cbse.Common.data.World;
import dk.sdu.cbse.Common.services.IEntityProcessingService;

import java.util.Random;


public class AsteroidProcessor implements IEntityProcessingService {



    private static final Random random = new Random();
    private float spawnTimer = 0;
    private final float spawnInterval = 1.0f; // seconds

@Override
    public void process(GameData gameData, World world) {
        spawnTimer += gameData.getDelta();

        if (spawnTimer >= spawnInterval) {
            spawnTimer = 0;

            for (int i = 0; i < 2; i++) {
                float x = 0, y = 0;

                int side = random.nextInt(4); // 0=top, 1=right, 2=bottom, 3=left
                switch (side) {
                    case 0: // top
                        x = random.nextFloat() * gameData.getDisplayWidth();
                        y = -20;
                        break;
                    case 1: // right
                        x = gameData.getDisplayWidth() + 20;
                        y = random.nextFloat() * gameData.getDisplayHeight();
                        break;
                    case 2: // bottom
                        x = random.nextFloat() * gameData.getDisplayWidth();
                        y = gameData.getDisplayHeight() + 20;
                        break;
                    case 3: // left
                        x = -20;
                        y = random.nextFloat() * gameData.getDisplayHeight();
                        break;
                }

                // Aim towards somewhere near the center of the screen
                float centerX = gameData.getDisplayWidth() / 2f;
                float centerY = gameData.getDisplayHeight() / 2f;

                float dirX = centerX - x;
                float dirY = centerY - y;
                float length = (float) Math.sqrt(dirX * dirX + dirY * dirY);

                // Normalize and scale
                float speed = 50 + random.nextFloat() * 50;
                float dx = (dirX / length) * speed;
                float dy = (dirY / length) * speed;

                float radius = 20 + random.nextFloat() * 20;

                world.addEntity(new Asteroid(x, y, dx, dy, radius));
            }
        }

        // Process movement and remove off-screen asteroids
        for (Entity e : world.getEntities(Asteroid.class)) {
            Asteroid asteroid = (Asteroid) e;

            double newX = asteroid.getX() + asteroid.getDx() * gameData.getDelta();
            double newY = asteroid.getY() + asteroid.getDy() * gameData.getDelta();

            asteroid.setX((float) newX);
            asteroid.setY((float) newY);

            int buffer = 100;

            if (newX < -buffer || newX > gameData.getDisplayWidth() + buffer ||
                    newY < -buffer || newY > gameData.getDisplayHeight() + buffer) {
                world.removeEntity(asteroid);
            }
        }
    }

}

