package fr.enseeiht.ubee;

public class IntersectionPoint {
    private float x;
    private float y;
    private boolean onFirstLine;
    private boolean onSecondLine;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isOnFirstLine() {
        return onFirstLine;
    }

    public void setOnFirstLine(boolean onFirstLine) {
        this.onFirstLine = onFirstLine;
    }

    public boolean isOnSecondLine() {
        return onSecondLine;
    }

    public void setOnSecondLine(boolean onSecondLine) {
        this.onSecondLine = onSecondLine;
    }
}
