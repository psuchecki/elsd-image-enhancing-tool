package fr.enseeiht.ubee;

public class LineConnection {
    private LineConnectionLeg connectionLeg1;
    private LineConnectionLeg connectionLeg2;

    public LineConnection(LineConnectionLeg connectionLeg1, LineConnectionLeg connectionLeg2) {
        this.connectionLeg1 = connectionLeg1;
        this.connectionLeg2 = connectionLeg2;
    }

    public LineConnectionLeg getConnectionLeg1() {
        return connectionLeg1;
    }

    public LineConnectionLeg getConnectionLegByLineId(int lineId) {
        return connectionLeg1.getLineId() == lineId ? connectionLeg1 : connectionLeg2;
    }

    public LineConnectionLeg getConnectionLeg2() {
        return connectionLeg2;
    }
}
