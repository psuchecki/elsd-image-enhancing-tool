package fr.enseeiht.ubee;

public class LineDistanceInfo {
    private double startPointDistance;
    private double endPointDistance;
    private IntersectionPoint startIntersectionPoint;
    private IntersectionPoint endIntersectionPoint;

    public LineDistanceInfo(double startPointDistance, double endPointDistance) {
        this.startPointDistance = startPointDistance;
        this.endPointDistance = endPointDistance;
    }

    public LineDistanceInfo(double startPointDistance, double endPointDistance, IntersectionPoint intersectionPoint) {
        this.startPointDistance = startPointDistance;
        this.endPointDistance = endPointDistance;
        if (isStartPointDistanceShorter()) {
            startIntersectionPoint = intersectionPoint;
        } else {
            endIntersectionPoint = intersectionPoint;
        }
    }

    public double getShorterDistance() {
        return Math.min(startPointDistance, endPointDistance);
    }

    public boolean isStartPointDistanceShorter() {
        return startPointDistance < endPointDistance;
    }

    public boolean hasStartIntersectionPoint() {
        return startIntersectionPoint != null;
    }

    public boolean hasEndIntersectionPoint() {
        return endIntersectionPoint != null;
    }

    public LineDistanceInfo toShorterOnlyDistanceInfo() {
        LineDistanceInfo shorterOnlyDistanceInfo;

        if (isStartPointDistanceShorter()) {
            shorterOnlyDistanceInfo = new LineDistanceInfo(startPointDistance, 0);
            shorterOnlyDistanceInfo.setStartIntersectionPoint(startIntersectionPoint);
        } else {
            shorterOnlyDistanceInfo = new LineDistanceInfo(0, endPointDistance);
            shorterOnlyDistanceInfo.setEndIntersectionPoint(endIntersectionPoint);
        }

        return shorterOnlyDistanceInfo;
    }

    public double getStartPointDistance() {
        return startPointDistance;
    }

    public void setStartPointDistance(double startPointDistance) {
        this.startPointDistance = startPointDistance;
    }

    public double getEndPointDistance() {
        return endPointDistance;
    }

    public void setEndPointDistance(double endPointDistance) {
        this.endPointDistance = endPointDistance;
    }

    public void setStartIntersectionPoint(IntersectionPoint startIntersectionPoint) {
        this.startIntersectionPoint = startIntersectionPoint;
    }

    public void setEndIntersectionPoint(IntersectionPoint endIntersectionPoint) {
        this.endIntersectionPoint = endIntersectionPoint;
    }

    public IntersectionPoint getStartIntersectionPoint() {
        return startIntersectionPoint;
    }

    public IntersectionPoint getEndIntersectionPoint() {
        return endIntersectionPoint;
    }
}
