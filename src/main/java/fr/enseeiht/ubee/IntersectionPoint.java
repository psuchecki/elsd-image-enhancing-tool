package fr.enseeiht.ubee;

public class IntersectionPoint extends Point {

    private boolean onFirstLine;
    private boolean onSecondLine;
    private boolean drawLine = true;

    public IntersectionPoint() {
        super(0, 0);
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

    public boolean shouldDrawLine() {
        return drawLine;
    }

    public void setDrawLine(boolean drawLine) {
        this.drawLine = drawLine;
    }
}
