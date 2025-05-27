module Collision {
    requires CommonAsteroid;
    requires Player;
    requires Enemy;
    requires Asteroid;
    requires Bullet;
    requires Common;
    requires javafx.graphics;

    uses dk.sdu.cbse.CommonAsteroid.IAsteroidSplitter;

    exports dk.sdu.cbse.Collision;

    provides dk.sdu.cbse.Common.services.IPostEntityProcessingService
            with dk.sdu.cbse.Collision.CollisionSystem;
}

