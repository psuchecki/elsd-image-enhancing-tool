package fr.enseeiht.ubee;

public class LineConnectionLeg {
    private double startPointDistance;
    private double endPointDistance;
    private IntersectionPoint startIntersectionPoint;
    private IntersectionPoint endIntersectionPoint;
    private boolean drawFromStartToIntersection = true;
    private boolean drawFromEndToIntersection = true;
    private int lineId;

    public LineConnectionLeg(int lineId, double startPointDistance, double endPointDistance,
                             IntersectionPoint intersectionPoint) {
        this.lineId = lineId;
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

    public boolean drawLineToStartIntersection() {
        return hasStartIntersectionPoint() && drawFromStartToIntersection;
    }

    public boolean drawLineToEndIntersection() {
        return hasEndIntersectionPoint() && drawFromEndToIntersection;
    }

    public void setDrawFromStartToIntersection(boolean drawFromStartToIntersection) {
        this.drawFromStartToIntersection = drawFromStartToIntersection;
    }

    public void setDrawFromEndToIntersection(boolean drawFromEndToIntersection) {
        this.drawFromEndToIntersection = drawFromEndToIntersection;
    }

    public int getLineId() {
        return lineId;
    }

    public boolean shouldDrawLineEnd(LineConnectionHolder.LineEnd lineEnd) {
        boolean shouldDrawLineEnd;
        if (LineConnectionHolder.LineEnd.START.equals(lineEnd)) {
            shouldDrawLineEnd = hasStartIntersectionPoint() && drawFromStartToIntersection;
        } else {
            shouldDrawLineEnd = hasEndIntersectionPoint() && drawFromEndToIntersection;
        }

        return shouldDrawLineEnd;
    }
}
