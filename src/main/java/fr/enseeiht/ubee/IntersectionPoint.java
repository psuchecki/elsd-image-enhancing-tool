package fr.enseeiht.ubee;

public class IntersectionPoint extends Point {

    private boolean onFirstLine;
    private boolean onSecondLine;
    private boolean isEmpty;

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

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean isNotEmpty() {
        return !isEmpty;
    }
}
