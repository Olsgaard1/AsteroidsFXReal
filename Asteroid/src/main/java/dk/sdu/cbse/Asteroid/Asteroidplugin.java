package dk.sdu.cbse.Asteroid;

import dk.sdu.cbse.Common.data.Entity;
import dk.sdu.cbse.Common.data.GameData;
import dk.sdu.cbse.Common.data.World;
import dk.sdu.cbse.Common.services.IGamePluginService;
import dk.sdu.cbse.CommonAsteroid.IAsteroidSplitter;

import java.util.Random;


public class Asteroidplugin implements IGamePluginService, IAsteroidSplitter {


    private static final Random random = new Random();
    @Override
    public void StartGame(GameData gameData, World world) {
        for (int i = 0; i < 1; i++) {
            float x = random.nextFloat() * gameData.getDisplayWidth();
            float y = random.nextFloat() * gameData.getDisplayHeight();
            float dx = random.nextFloat() * 100 - 50;
            float dy = random.nextFloat() * 100 - 50;

            float radius = 20 + random.nextFloat() * 20; // Radius between 20 and 40

            world.addEntity(new Asteroid(x, y, dx, dy, radius)); // Use radius-aware constructor
        }
    }

    @Override
    public void EndGame(GameData gameData, World world) {
        world.getEntities(Asteroid.class).forEach(world::removeEntity);
    }

    @Override
    public Entity createAsteroid(float x, float y, float dx, float dy, float radius) {
        return new Asteroid(x, y, dx, dy, radius);
    }

    @Override
    public void splitAsteroid(Entity asteroid, World world) {
        if (asteroid instanceof Asteroid) {
            Asteroid asteroidEntity = (Asteroid) asteroid;
            asteroidEntity.splitter(world); // Split the asteroid into two smaller ones
        }
    }
}
