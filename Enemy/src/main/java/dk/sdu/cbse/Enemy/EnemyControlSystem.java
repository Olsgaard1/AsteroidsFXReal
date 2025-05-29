package dk.sdu.cbse.Enemy;


import dk.sdu.cbse.Common.data.Entity;
import dk.sdu.cbse.Common.data.GameData;
import dk.sdu.cbse.Common.data.World;
import dk.sdu.cbse.Common.services.IEntityProcessingService;
import dk.sdu.cbse.CommonBullet.IBulletSPI;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {

    private float respawnTimer = 0;
    private final float respawnDelay = 3.0f;
    private float shootCooldown = 0;
    private final float ShootDelay = 1.5f;
    final float Speed = 50f;// sekunder
    @Override
    public void process(GameData gameData, World world) {
        Random rand = new Random();
        Collection<Entity> enemies = world.getEntities(Enemy.class);


        if (enemies.isEmpty()) {
            respawnTimer += gameData.getDelta();
            if (respawnTimer >= respawnDelay) {
                Entity newEnemy = createEnemy(gameData);
                world.addEntity(newEnemy);
                respawnTimer = 0;

            }
            return;
        }
        for (Entity enemy : enemies) {
            updateEnemy(enemy, gameData,world);
        }

        int randInt;
        int randInt2;

        for (Entity enemy : world.getEntities(Enemy.class)) {
            randInt = rand.nextInt(2);
            randInt2 = rand.nextInt(30);


            if (randInt == 0) {
                enemy.setRotation(enemy.getRotation() - 5);

            }
            if (randInt == 1) {
                enemy.setRotation(enemy.getRotation() + 5);
            }
            if (true) {
                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX * Speed * gameData.getDelta());
                enemy.setY(enemy.getY() + changeY * Speed * gameData.getDelta());
            }
            shootCooldown -= gameData.getDelta();
            if (shootCooldown <= 0) {

                getBulletSPI().stream().findFirst().ifPresent(
                        bullet -> {
                            world.addEntity(bullet.CreateBullet(enemy, gameData));
                        });
                shootCooldown = ShootDelay;
            }

            if (enemy.getX() < 0) {
                enemy.setX(1);
                enemy.setRotation(enemy.getRotation() + 180);
            }

            if (enemy.getY() < 0) {
                enemy.setY(1);
                enemy.setRotation(enemy.getRotation() + 180);
            }

            if (enemy.getX() > gameData.getDisplayWidth()){
                enemy.setX(gameData.getDisplayWidth() - 1);
                enemy.setRotation(enemy.getRotation() + 180);

            }

            if (enemy.getY() > gameData.getDisplayHeight()){
                enemy.setY(gameData.getDisplayHeight() - 1);
                enemy.setRotation(enemy.getRotation() + 180);
            }
        }
    }


    private Entity createEnemy(GameData gameData) {
        Entity enemy = new Enemy();
        enemy.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        enemy.setX(gameData.getDisplayWidth() / 2.0 + 100);
        enemy.setY(gameData.getDisplayHeight() / 2.0 + 100);
        return enemy;
    }

    private void updateEnemy(Entity enemy, GameData gameData, World world) {
        Random rand = new Random();

        int randInt = rand.nextInt(2);
        int randInt2 = rand.nextInt(10);

        if (randInt == 0) {
            enemy.setRotation(enemy.getRotation() - 5);
        } else {
            enemy.setRotation(enemy.getRotation() + 5);
        }

        double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
        double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
        enemy.setX(enemy.getX() + changeX);
        enemy.setY(enemy.getY() + changeY);

        if (randInt2 == 1) {
            getBulletSPI().stream().findFirst().ifPresent(
                    bullet -> world.addEntity(bullet.CreateBullet(enemy, gameData))
            );
        }

        // bounce fra kanterne
        if (enemy.getX() < 0 || enemy.getX() > gameData.getDisplayWidth()) {
            enemy.setRotation(enemy.getRotation() + 180);
        }
        if (enemy.getY() < 0 || enemy.getY() > gameData.getDisplayHeight()) {
            enemy.setRotation(enemy.getRotation() + 180);
        }
    }

    private Collection<? extends IBulletSPI> getBulletSPI() {
        return ServiceLoader.load(IBulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
