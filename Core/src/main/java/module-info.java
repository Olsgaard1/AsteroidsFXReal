module Core {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    opens dk.sdu.cbse.main to javafx.graphics;
    uses dk.sdu.cbse.Common.services.IGamePluginService;
    uses dk.sdu.cbse.Common.services.IEntityProcessingService;
    uses dk.sdu.cbse.Common.services.IPostEntityProcessingService;


}