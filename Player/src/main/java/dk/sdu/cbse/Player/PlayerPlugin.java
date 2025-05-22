package dk.sdu.cbse.Player;
import dk.sdu.cbse.Common.data.Entity;
import dk.sdu.cbse.Common.data.GameData;
import dk.sdu.cbse.Common.data.World;
import dk.sdu.cbse.Common.services.IGamePluginService;

    public class PlayerPlugin implements IGamePluginService {

        private Entity player;

        public PlayerPlugin() {
        }


        private Entity createPlayerShip(GameData gameData) {

            Entity playerShip = new Player();
            playerShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
            playerShip.setX(gameData.getDisplayHeight()/2);
            playerShip.setY(gameData.getDisplayWidth()/2);
            playerShip.setRadius(8);
            return playerShip;
        }



        @Override
        public void StartGame(GameData gameData, World world) {
                // Add entities to the world
                player = createPlayerShip(gameData);
                world.addEntity(player);

        }

        @Override
        public void EndGame(GameData gameData, World world) {
            world.removeEntity(player);
        }
    }

