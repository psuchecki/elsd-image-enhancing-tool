package fr.enseeiht.ubee;

public class LinePair {
    private static double DISTANCE_THRESHOLD = 50d;
    private Line line1;
    private Line line2;
    private IntersectionPoint intersectionPoint;

    public LinePair(Line line1, Line line2) {
        this.line1 = line1;
        this.line2 = line2;
        intersectionPoint = computeIntersectionPoint();
    }

    public boolean shouldConnectLines() {
        boolean connectLines = false;
        if (intersectionPoint.isNotEmpty()) {
            LineDistanceInfo line1DistanceInfo = getLine1DistanceInfo();
            LineDistanceInfo line2DistanceInfo = getLine2DistanceInfo();

            double totalDistance = line1DistanceInfo.getShorterDistance() + line2DistanceInfo.getShorterDistance();
            connectLines = totalDistance < DISTANCE_THRESHOLD;
        }

        return connectLines;
    }

    //magic happens here
    private IntersectionPoint computeIntersectionPoint() {
        float a, b, numerator1, numerator2, denominator;
        IntersectionPoint intersectionPoint = new IntersectionPoint();

        denominator =
                ((line2.getYDistance()) * (line1.getXDistance())) - ((line2.getXDistance()) * (line1.getYDistance()));
        if (denominator == 0) { // lines are parallel
            intersectionPoint.setEmpty(true);
            return intersectionPoint;
        }

        a = line1.getY1() - line2.getY1();
        b = line1.getX1() - line2.getX1();
        numerator1 = (line2.getXDistance() * a) - (line2.getYDistance() * b);
        numerator2 = (line1.getXDistance() * a) - (line1.getYDistance() * b);

        a = numerator1 / denominator;
        b = numerator2 / denominator;

        intersectionPoint.setX(line1.getX1() + (a * line1.getXDistance()));
        intersectionPoint.setY(line1.getY1() + (a * line1.getYDistance()));
        if (a > 0 && a < 1) {
            intersectionPoint.setOnFirstLine(true);
        }

        if (b > 0 && b < 1) {
            intersectionPoint.setOnSecondLine(true);
        }

        return intersectionPoint;
    }

    public IntersectionPoint getIntersectionPoint() {
        return intersectionPoint;
    }

    public int getLine1Index() {
        return line1.getLineIndex();
    }

    public int getLine2Index() {
        return line2.getLineIndex();
    }

    public LineDistanceInfo getLine1DistanceInfo() {
        return line1.getLineDistanceInfo(intersectionPoint);
    }

    public LineDistanceInfo getLine2DistanceInfo() {
        return line2.getLineDistanceInfo(intersectionPoint);
    }
}
