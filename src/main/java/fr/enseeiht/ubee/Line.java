package fr.enseeiht.ubee;

public class Line {
    private float x1;
    private float x2;
    private float y1;
    private float y2;

    public Line(float x1, float x2, float y1, float y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public float getXDistance() {
        return x2 - x1;
    }

    public float getYDistance() {
        return y2 - y1;
    }

    public float getX1() {
        return x1;
    }

    public float getX2() {
        return x2;
    }

    public float getY1() {
        return y1;
    }

    public float getY2() {
        return y2;
    }
}
