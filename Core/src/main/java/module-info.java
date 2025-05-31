module Core {
    requires javafx.controls;

    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires spring.aop;
    requires spring.expression;
    requires spring.jcl;

    requires Collision;
    requires Player;
    requires Enemy;
    requires Bullet;
    requires Asteroid;
    requires Common;
    opens dk.sdu.cbse.main to javafx.graphics,spring.core,spring.beans,spring.context;


    uses dk.sdu.cbse.Common.services.IGamePluginService;
    uses dk.sdu.cbse.Common.services.IEntityProcessingService;
    uses dk.sdu.cbse.Common.services.IPostEntityProcessingService;


}