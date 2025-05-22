package dk.sdu.cbse.Common.data;

public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private float CursorX;
    private float CursorY;

    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    private double delta;

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public float getCursorX() {
        return CursorX;
    }

    public void setCursorX(float CursorX) {
        this.CursorX = CursorX;
    }

    public float getCursorY() {
        return CursorY;
    }

    public void setCursorY(float CursorY) {
        this.CursorY = CursorY;
    }

    private int asteroidsDestroyed = 0;

    public void incrementAsteroidsDestroyed() {
        asteroidsDestroyed++;
    }

    public int getAsteroidsDestroyed() {
        return asteroidsDestroyed;
    }


}
