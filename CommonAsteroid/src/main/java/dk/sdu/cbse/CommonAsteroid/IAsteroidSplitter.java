package dk.sdu.cbse.CommonAsteroid;
import dk.sdu.cbse.Common.data.Entity;
import dk.sdu.cbse.Common.data.World;


public interface IAsteroidSplitter {
    Entity createAsteroid(float x, float y, float dx, float dy, float radius);

    void splitAsteroid(Entity asteroid, World world);
}

