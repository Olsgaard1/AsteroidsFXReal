package dk.sdu.Asteroid;

import dk.sdu.cbse.Common.data.Entity;
import dk.sdu.cbse.Common.data.GameData;
import dk.sdu.cbse.Common.data.World;
import dk.sdu.cbse.Common.services.IEntityProcessingService;

import java.util.ArrayList;
import java.util.Random;


public class AsteroidProcessor implements IEntityProcessingService {




    private static final Random random = new Random();
    private double spawnTimer = 0;
    private final double spawnInterval = 1.0;// seconds
    private final int MAX_ASTEROIDS = 5;


@Override
    public void process(GameData gameData, World world) {
        spawnTimer += gameData.getDelta();
    long CurrentAsteroids = world.getEntities(Asteroid.class).stream().count();

        if (spawnTimer >= spawnInterval && CurrentAsteroids < MAX_ASTEROIDS) {
            spawnTimer = 0;
            System.out.println("Spawning new asteroid ");

            int tospawnAsteroids = Math.min(2,MAX_ASTEROIDS - (int)CurrentAsteroids);

            for (int i = 0; i < tospawnAsteroids; i++) {
                float x = 0, y = 0;

                int side = random.nextInt(4);
                switch (side) {
                    case 0:
                        x = random.nextFloat() * gameData.getDisplayWidth();
                        y = -20;
                        break;
                    case 1:
                        x = gameData.getDisplayWidth() + 20;
                        y = random.nextFloat() * gameData.getDisplayHeight();
                        break;
                    case 2:
                        x = random.nextFloat() * gameData.getDisplayWidth();
                        y = gameData.getDisplayHeight() + 20;
                        break;
                    case 3:
                        x = -20;
                        y = random.nextFloat() * gameData.getDisplayHeight();
                        break;
                }

                float centerX = gameData.getDisplayWidth() / 2f;
                float centerY = gameData.getDisplayHeight() / 2f;
                float dirX = centerX - x;
                float dirY = centerY - y;
                float length = (float) Math.sqrt(dirX * dirX + dirY * dirY);
                float speed = 20 + random.nextFloat() * 20;
                float dx = (dirX / length) * speed;
                float dy = (dirY / length) * speed;
                float radius = 20 + random.nextFloat() * 20;


                Asteroid asteroid = new Asteroid(x, y, dx, dy, radius);
                asteroid.setPolygonCoordinates(generateRandomAsteroidShape(radius));
                world.addEntity(asteroid);
            }
        }

        // Flytning og fjernelse
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
    private double[] generateRandomAsteroidShape(float radius) {
        int numPoints = 8 + random.nextInt(5); // 8–12 hjørner
        double[] coords = new double[numPoints * 2];

        for (int i = 0; i < numPoints; i++) {
            double angle = 2 * Math.PI * i / numPoints;
            double r = radius + (random.nextDouble() * radius * 0.4 - radius * 0.2); // ±20% ujævnhed
            coords[i * 2] = Math.cos(angle) * r;
            coords[i * 2 + 1] = Math.sin(angle) * r;
        }

        return coords;
    }
}


