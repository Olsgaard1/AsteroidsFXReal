module Core {
    requires CommonBullet;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires Common;
    opens dk.sdu.cbse.main to javafx.graphics;


    uses dk.sdu.cbse.Common.services.IGamePluginService;
    uses dk.sdu.cbse.Common.services.IEntityProcessingService;
    uses dk.sdu.cbse.Common.services.IPostEntityProcessingService;


}