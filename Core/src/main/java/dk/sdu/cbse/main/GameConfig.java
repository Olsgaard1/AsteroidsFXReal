package dk.sdu.cbse.main;

import dk.sdu.cbse.Asteroid.Asteroidplugin;
import dk.sdu.cbse.Asteroid.AsteroidProcessor;
import dk.sdu.cbse.Collision.CollisionSystem;
import dk.sdu.cbse.Common.services.IPostEntityProcessingService;
import dk.sdu.cbse.bullet.BulletControlSystem;
import dk.sdu.cbse.bullet.BulletPlugin;
import dk.sdu.cbse.Enemy.EnemyControlSystem;
import dk.sdu.cbse.Enemy.EnemyPlugin;
import dk.sdu.cbse.Player.PlayerControlSystem;
import dk.sdu.cbse.Player.PlayerPlugin;
import dk.sdu.cbse.Common.services.IGamePluginService;
import dk.sdu.cbse.Common.services.IEntityProcessingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GameConfig {

    @Bean
    public Game game(List<IGamePluginService> gamePluginServices,
                     List<IEntityProcessingService> entityProcessingServices,
                     List<IPostEntityProcessingService> postEntityProcessingServices) {



        return new Game(entityProcessingServices, postEntityProcessingServices, gamePluginServices);
    }

    @Bean
    public IGamePluginService asteroidPlugin() {
        return new Asteroidplugin();
    }

    @Bean
    public IGamePluginService bulletPlugin() {
        return new BulletPlugin();
    }

    @Bean
    public IGamePluginService enemyPlugin() {
        return new EnemyPlugin();
    }

    @Bean
    public IGamePluginService playerPlugin() {
        return new PlayerPlugin();
    }

    @Bean
    public IEntityProcessingService asteroidControlSystem() {
        return new AsteroidProcessor();
    }

    @Bean
    public IEntityProcessingService bulletControlSystem() {
        return new BulletControlSystem();
    }

    @Bean
    public IEntityProcessingService enemyControlSystem() {
        return new EnemyControlSystem();
    }

    @Bean
    public IEntityProcessingService playerControlSystem() {
        return new PlayerControlSystem();
    }
    @Bean
    public IPostEntityProcessingService collisionSystem(){
        return new CollisionSystem();
    }
}
