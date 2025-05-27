import dk.sdu.cbse.Common.services.IEntityProcessingService;
import dk.sdu.cbse.Common.services.IGamePluginService;
import dk.sdu.cbse.CommonBullet.IBulletSPI;

module Bullet {
    exports dk.sdu.cbse.bullet;
    requires Common;
    requires CommonBullet;
    provides IGamePluginService with dk.sdu.cbse.bullet.BulletPlugin;
    provides IBulletSPI with dk.sdu.cbse.bullet.BulletControlSystem;
    provides IEntityProcessingService with dk.sdu.cbse.bullet.BulletControlSystem;
}