import dk.sdu.cbse.Common.services.IEntityProcessingService;
import dk.sdu.cbse.Common.services.IGamePluginService;
import dk.sdu.cbse.CommonBullet.IBulletSPI;

module Bullet {
    requires Common;
    requires CommonBullet;
    provides IGamePluginService with dk.sdu.cbse.bullet.BulletPlugin;
    provides IBulletSPI with dk.sdu.cbse.bullet.BulletControlSystem;
    provides IEntityProcessingService with dk.sdu.cbse.bullet.BulletControlSystem;
}