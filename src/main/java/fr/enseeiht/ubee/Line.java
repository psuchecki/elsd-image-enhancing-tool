package fr.enseeiht.ubee;

public class Line {
    private Point startPoint;
    private Point endPoint;
    private int lineIndex;

    public Line(float x1, float x2, float y1, float y2, int lineIndex) {
        startPoint = new Point(x1, y1);
        endPoint = new Point(x2, y2);
        this.lineIndex = lineIndex;
    }

    public LineDistanceInfo getLineDistanceInfo(IntersectionPoint intersectionPoint) {
        double startPointDistance = intersectionPoint.getDistanceToPoint(startPoint);
        double endPointDistance = intersectionPoint.getDistanceToPoint(endPoint);

        return new LineDistanceInfo(startPointDistance, endPointDistance, intersectionPoint);
    }

    public float getXDistance() {
        return endPoint.getX() - startPoint.getX();
    }

    public float getYDistance() {
        return endPoint.getY() - startPoint.getY();
    }

    public float getX1() {
        return startPoint.getX();
    }

    public float getY1() {
        return startPoint.getY();
    }

    public int getLineIndex() {
        return lineIndex;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

}
