import dk.sdu.cbse.Common.services.IEntityProcessingService;
import dk.sdu.cbse.Common.services.IGamePluginService;

module Enemy {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.cbse.CommonBullet.IBulletSPI;
    provides IGamePluginService with dk.sdu.cbse.Enemy.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.Enemy.EnemyControlSystem;
    exports dk.sdu.cbse.Enemy;
}