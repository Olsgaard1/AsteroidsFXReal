import dk.sdu.Asteroid.AsteroidProcessor;
import dk.sdu.Asteroid.Asteroidplugin;
import dk.sdu.cbse.Common.services.IEntityProcessingService;
import dk.sdu.cbse.Common.services.IGamePluginService;

module Asteroid {
    requires Common;
    requires CommonAsteroid;
    provides IEntityProcessingService with AsteroidProcessor;
    provides IGamePluginService with Asteroidplugin;
}