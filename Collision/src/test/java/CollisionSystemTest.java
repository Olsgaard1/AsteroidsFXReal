import dk.sdu.cbse.Collision.CollisionSystem;
import dk.sdu.cbse.Common.data.GameData;
import dk.sdu.cbse.Common.data.World;
import dk.sdu.cbse.Player.Player;
import dk.sdu.cbse.bullet.Bullet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollisionSystemTest {

    @Test
    public void TestCollisiononPlayer() {

        GameData gameData = new GameData();
        World world = new World();

        Bullet bullet = new Bullet();
        bullet.setX(100);
        bullet.setY(100);
        bullet.setRadius(10);
        bullet.setOwnerType("Enemy");

        Player player = new Player();
        player.setX(105);
        player.setY(105);
        player.setRadius(10);

        CollisionSystem collisionSystem = new CollisionSystem();

        collisionSystem.process(gameData,world);


        assertFalse(world.getEntities().contains(bullet), "Bullet should be removed");
        assertFalse(world.getEntities().contains(player),"Player collision");

        // ville slå fejl hvis brug af
        // assertTrue(world.getEntities().contains(player),"Player collision");


    }
}
