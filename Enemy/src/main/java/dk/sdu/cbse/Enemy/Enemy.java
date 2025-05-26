package dk.sdu.cbse.Enemy;

import dk.sdu.cbse.Common.data.Entity;

public class Enemy extends Entity {

    public Enemy() {
        setRadius(15f);
       setPolygonCoordinates(generatePolygon(getRadius()));
    }
}
