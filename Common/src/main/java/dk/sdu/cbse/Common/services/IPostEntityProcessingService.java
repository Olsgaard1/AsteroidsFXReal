package dk.sdu.cbse.Common.services;

import dk.sdu.cbse.Common.data.GameData;
import dk.sdu.cbse.Common.data.World;

public interface IPostEntityProcessingService {
    void process(GameData gameData, World world);
}
