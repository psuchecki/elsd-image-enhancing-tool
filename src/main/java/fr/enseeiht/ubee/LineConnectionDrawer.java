package fr.enseeiht.ubee;

import org.apache.batik.dom.svg.SVGOMLineElement;
import org.w3c.dom.NodeList;

import java.util.List;

public class LineConnectionDrawer {
    private LineConnectionHolder lineConnectionHolder = new LineConnectionHolder();

    public void drawConnectionsForPairs(List<LinePair> linePairs, NodeList svgLines) {
        for (LinePair linePair : linePairs) {
            boolean shouldConnectLines = linePair.shouldConnectLines();
            if (shouldConnectLines) {
                IntersectionPoint intersectionPoint = linePair.getIntersectionPoint();
                LineConnection lineConnection = linePair.getLineConnection();
                lineConnectionHolder.addLineConnection(lineConnection);

                computeLongestDistances(linePair.getLine1Index(), lineConnection.getConnectionLeg1(), intersectionPoint,
                        !intersectionPoint.isOnFirstLine());
                computeLongestDistances(linePair.getLine2Index(), lineConnection.getConnectionLeg2(), intersectionPoint,
                        !intersectionPoint.isOnSecondLine());
            }
        }

        List<LineConnection> lineConnections = lineConnectionHolder.getLineConnections();
        for (LineConnection lineConnection : lineConnections) {
            drawConnectionForLeg(svgLines, lineConnection.getConnectionLeg1());
            drawConnectionForLeg(svgLines, lineConnection.getConnectionLeg2());
        }
    }

    private void drawConnectionForLeg(NodeList svgLines, LineConnectionLeg lineConnectionLeg) {
        SVGOMLineElement svgLine = (SVGOMLineElement) svgLines.item(lineConnectionLeg.getLineId());
        if (lineConnectionLeg.drawLineToStartIntersection()) {
            IntersectionPoint intersectionPoint = lineConnectionLeg.getStartIntersectionPoint();
            svgLine.getX1().getBaseVal().setValue(intersectionPoint.getX());
            svgLine.getY1().getBaseVal().setValue(intersectionPoint.getY());
        }

        if (lineConnectionLeg.drawLineToEndIntersection()) {
            IntersectionPoint intersectionPoint = lineConnectionLeg.getEndIntersectionPoint();
            svgLine.getX2().getBaseVal().setValue(intersectionPoint.getX());
            svgLine.getY2().getBaseVal().setValue(intersectionPoint.getY());
        }
    }

    private void computeLongestDistances(int lineIndex, LineConnectionLeg lineConnectionLeg,
                                         IntersectionPoint intersectionPoint, boolean drawToIntersection) {
        LineConnectionLeg minStartLineConnectionLeg = lineConnectionHolder.findMinStartLineConnectionLeg(lineIndex);
        LineConnectionLeg minEndLineConnectionLeg = lineConnectionHolder.findMinEndLineConnectionLeg(lineIndex);

        double startPointDistance = minStartLineConnectionLeg.getStartPointDistance();
        if (lineConnectionLeg.hasStartIntersectionPoint() &&
                startPointDistance >= lineConnectionLeg.getStartPointDistance()) {
            minStartLineConnectionLeg.setStartPointDistance(startPointDistance);
            minStartLineConnectionLeg.setStartIntersectionPoint(intersectionPoint);
            minStartLineConnectionLeg.setDrawFromStartToIntersection(drawToIntersection);
            lineConnectionHolder.updatStartConnectionsForLine(lineIndex);
        } else if (lineConnectionLeg.hasEndIntersectionPoint() &&
                minEndLineConnectionLeg.getEndPointDistance() >= lineConnectionLeg.getEndPointDistance()) {
            minEndLineConnectionLeg.setEndPointDistance(lineConnectionLeg.getEndPointDistance());
            minEndLineConnectionLeg.setEndIntersectionPoint(intersectionPoint);
            minEndLineConnectionLeg.setDrawFromEndToIntersection(drawToIntersection);
            lineConnectionHolder.updateEndConnectionsForLine(lineIndex);
        } else {
            lineConnectionLeg.setDrawFromStartToIntersection(false);
            lineConnectionLeg.setDrawFromEndToIntersection(false);
        }
    }


}
