package fr.enseeiht.ubee;

public class Point {
    private float x;
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public double getDistanceToPoint(Point intersectionPoint) {
        return Math.sqrt((Math.pow(x - intersectionPoint.getX(), 2)) + (Math.pow(y - intersectionPoint.getY(), 2)));
    }

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
}
