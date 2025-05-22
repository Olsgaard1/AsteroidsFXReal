package dk.sdu.cbse.bullet;

import dk.sdu.cbse.Common.data.Entity;
import dk.sdu.cbse.Common.data.GameData;
import dk.sdu.cbse.Common.data.World;
import dk.sdu.cbse.Common.services.IGamePluginService;


public class BulletPlugin implements IGamePluginService {

    private Entity bullet;


    @Override
    public void StartGame(GameData gameData, World world) {

    }

    @Override
    public void EndGame(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }
}
