package fr.enseeiht.ubee;

public class LinePair {
    private Line firstLine;
    private Line secondLine;

    public LinePair(Line firstLine, Line secondLine) {
        this.firstLine = firstLine;
        this.secondLine = secondLine;
    }

    public boolean linesIntersect() {
        float a, b, numerator1, numerator2, denominator;

        denominator = ((secondLine.getYDistance()) * (firstLine.getXDistance())) -
                ((secondLine.getXDistance()) * (firstLine.getYDistance()));
        if (denominator == 0) {
            return false; // lines are parraler
        }

        a = firstLine.getY1() - secondLine.getY1();
        b = firstLine.getX1() - secondLine.getX1();
        numerator1 = (secondLine.getXDistance() * a) - (secondLine.getYDistance() * b);
        numerator2 = (firstLine.getXDistance() * a) - (firstLine.getYDistance() * b);

        a = numerator1 / denominator;
        b = numerator2 / denominator;

        IntersectionPoint intersectionPoint = new IntersectionPoint();
        intersectionPoint.setX(firstLine.getX1() + (a * firstLine.getXDistance()));
        intersectionPoint.setY(firstLine.getY1() + (a * firstLine.getYDistance()));
        if (a > 0 && a < 1) {
            intersectionPoint.setOnFirstLine(true);
        }

        if (b > 0 && b < 1) {
            intersectionPoint.setOnSecondLine(true);
        }

        return false;
    }
}
