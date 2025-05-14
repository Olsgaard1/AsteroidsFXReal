package dk.sdu.cbse.Common.services;

import dk.sdu.cbse.Common.data.GameData;
import dk.sdu.cbse.Common.data.World;

public interface IGamePluginService {
    void StartGame(GameData gameData, World world);
    void EndGame(GameData gameData, World world);
}
