package dk.sdu.cbse.Enemy;

import dk.sdu.cbse.Common.data.Entity;
import dk.sdu.cbse.Common.data.GameData;
import dk.sdu.cbse.Common.data.World;
import dk.sdu.cbse.Common.services.IGamePluginService;

public class EnemyPlugin  implements IGamePluginService {
    private Entity enemy;

    public EnemyPlugin(){

    }
    @Override
    public void StartGame(GameData gameData, World world) {
        enemy = CreateEnemy(gameData);
        world.addEntity(enemy);
    }

    private Entity CreateEnemy(GameData gameData){

        Entity EnemyShip = new Enemy();
        EnemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        EnemyShip.setX(gameData.getDisplayWidth() / 2.0 + 100);
        EnemyShip.setY(gameData.getDisplayHeight() / 2.0 + 100);
        return EnemyShip;
    }


    @Override
    public void EndGame(GameData gameData, World world) {
        world.removeEntity(enemy);

    }
}
