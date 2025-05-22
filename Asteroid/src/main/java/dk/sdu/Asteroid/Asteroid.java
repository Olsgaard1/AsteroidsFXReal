package dk.sdu.Asteroid;

import dk.sdu.cbse.Common.data.Entity;
import dk.sdu.cbse.Common.data.World;

public class Asteroid extends Entity {
    private float x,y,dx,dy;
    private static final float MIN_RADIUS = 10f;


    public Asteroid(float x, float y, float dx, float dy,float radius) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        setRadius(radius);

        setPolygonCoordinates(generatePolygon(radius));

    }

    public void splitter(World world) {
        if (getRadius() < MIN_RADIUS) {
            float newRadius = getRadius() / 2;

            float newDx1 = dx + (float) (Math.random() * 20 - 10);
            float newDy1 = dy + (float) (Math.random() * 20 - 10);
            float newDx2 = dx + (float) (Math.random() * 20 - 10);
            float newDy2 = dy + (float) (Math.random() * 20 - 10);

            float newX1 = x + 10;
            float newX2 = x - 10;
            Asteroid smallerAsteroid1 = new Asteroid(newX1, y, newDx1, newDy1, newRadius);
            Asteroid smallerAsteroid2 = new Asteroid(newX2, y, newDx2, newDy2, newRadius);

            smallerAsteroid1.setRadius(newRadius);
            smallerAsteroid2.setRadius(newRadius);


            smallerAsteroid1.setPolygonCoordinates(generatePolygon(newRadius));
            smallerAsteroid2.setPolygonCoordinates(generatePolygon(newRadius));

            world.addEntity(smallerAsteroid1);
            world.addEntity(smallerAsteroid2);
        }
    }

    // Update the asteroid's position
    public void update(double deltaTime) {
        x += dx * deltaTime;
        y += dy * deltaTime;
    }



    public double getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }
}