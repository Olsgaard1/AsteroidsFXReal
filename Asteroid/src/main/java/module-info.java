import dk.sdu.cbse.Common.services.IEntityProcessingService;
import dk.sdu.cbse.Common.services.IGamePluginService;

module Asteroid {
    exports dk.sdu.cbse.Asteroid;
    requires Common;
    requires CommonAsteroid;
    provides IEntityProcessingService with dk.sdu.cbse.Asteroid.AsteroidProcessor;
    provides IGamePluginService with dk.sdu.cbse.Asteroid.Asteroidplugin;
    provides dk.sdu.cbse.CommonAsteroid.IAsteroidSplitter with dk.sdu.cbse.Asteroid.Asteroidplugin;
}