import dk.sdu.cbse.Common.services.IEntityProcessingService;
import dk.sdu.cbse.Common.services.IGamePluginService;

module Player {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.cbse.CommonBullet.IBulletSPI;
    provides IGamePluginService with dk.sdu.cbse.Player.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.Player.PlayerControlSystem;
}