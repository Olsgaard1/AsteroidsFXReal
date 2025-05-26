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

    @Override
    public void process(GameData gameData, World world) {
        Random rand = new Random();

        int randInt;
        int randInt2;

        for (Entity enemy : world.getEntities(Enemy.class)) {
            randInt = rand.nextInt(2);
            randInt2 = rand.nextInt(10);

            if (randInt == 0) {
                enemy.setRotation(enemy.getRotation() - 5);

            }
            if (randInt == 1) {
                enemy.setRotation(enemy.getRotation() + 5);
            }
            if (true) {
                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.cos(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX);
                enemy.setY(enemy.getY() + changeY);
            }
            if (randInt2 == 1) {

                getBulletSPI().stream().findFirst().ifPresent(
                        bullet -> {
                            world.addEntity(bullet.CreateBullet(enemy, gameData));
                        });
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

    private Collection<? extends IBulletSPI> getBulletSPI() {
        return ServiceLoader.load(IBulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
