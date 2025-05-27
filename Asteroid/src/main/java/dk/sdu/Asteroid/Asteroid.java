package dk.sdu.Asteroid;

import dk.sdu.cbse.Common.data.Entity;
import dk.sdu.cbse.Common.data.World;

public class Asteroid extends Entity {
    private float x,y,dx,dy;
    private static final float MIN_RADIUS = 10f;
    private boolean hasSplit = false;

    public Asteroid(float x, float y, float dx, float dy,float radius) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        setRadius(radius);

        setPolygonCoordinates(generateRandomAsteroidShape(radius));

    }

    public void splitter(World world) {
        if (getRadius() < MIN_RADIUS) return;

            float newRadius = getRadius() / 2;

            float newDx1 = dx + (float) (Math.random() * 20 - 10);
            float newDy1 = dy + (float) (Math.random() * 20 - 10);
            float newDx2 = dx + (float) (Math.random() * 20 - 10);
            float newDy2 = dy + (float) (Math.random() * 20 - 10);

            float newX1 = x + 10;
            float newX2 = x - 10;
            Asteroid smallerAsteroid1 = new Asteroid(newX1, y, newDx1, newDy1, newRadius);
            Asteroid smallerAsteroid2 = new Asteroid(newX2, y, newDx2, newDy2, newRadius);

            smallerAsteroid1.setPolygonCoordinates(generateRandomAsteroidShape(newRadius));
            smallerAsteroid2.setPolygonCoordinates(generateRandomAsteroidShape(newRadius));



            world.addEntity(smallerAsteroid1);
            world.addEntity(smallerAsteroid2);

    }

    private double[] generateRandomAsteroidShape(float radius) {
        int numPoints = 8 + (int)(Math.random() * 5); // 8–12 hjørner
        double[] coords = new double[numPoints * 2];

        for (int i = 0; i < numPoints; i++) {
            double angle = 2 * Math.PI * i / numPoints;
            double r = radius + (Math.random() * radius * 0.4 - radius * 0.2); // ujævnhed ±20%
            coords[i * 2] = Math.cos(angle) * r;
            coords[i * 2 + 1] = Math.sin(angle) * r;
        }

        return coords;
    }

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

    public boolean hasSplit() {
        return hasSplit;
    }

    public void markSplit() {
        this.hasSplit = true;
    }
}