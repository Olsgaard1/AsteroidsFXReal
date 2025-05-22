package dk.sdu.cbse.CommonBullet;

import dk.sdu.cbse.Common.data.Entity;
import dk.sdu.cbse.Common.data.GameData;


public interface IBulletSPI {
    Entity CreateBullet(Entity shooter,GameData gamedata);
}
