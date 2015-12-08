package fr.enseeiht.ubee;

import com.google.common.collect.Maps;
import org.apache.batik.dom.svg.SVGOMLineElement;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.Map;

public class LineConnectionDrawer {
    private Map<Integer, LineDistanceInfo> lineIdToDistanceInfoMap = Maps.newHashMap();

    public void drawConnectionsForPairs(List<LinePair> linePairs, NodeList svgLines) {
        for (LinePair linePair : linePairs) {
            boolean shouldConnectLines = linePair.shouldConnectLines();
            IntersectionPoint intersectionPoint = linePair.getIntersectionPoint();
            if (shouldConnectLines && !intersectionPoint.isOnFirstLine()) {
                computeLongestDistances(linePair.getLine1Index(), linePair.getLine1DistanceInfo(), intersectionPoint);
            }
            if (shouldConnectLines && !intersectionPoint.isOnSecondLine()) {
                computeLongestDistances(linePair.getLine2Index(), linePair.getLine2DistanceInfo(), intersectionPoint);
            }
        }

        for (int lineIndex = 0; lineIndex < svgLines.getLength(); lineIndex++) {
            SVGOMLineElement svgLine = (SVGOMLineElement) svgLines.item(lineIndex);
            LineDistanceInfo lineDistanceInfo = lineIdToDistanceInfoMap.get(lineIndex);
            if (lineDistanceInfo != null && lineDistanceInfo.hasStartIntersectionPoint()) {
                IntersectionPoint intersectionPoint = lineDistanceInfo.getStartIntersectionPoint();
                svgLine.getX1().getBaseVal().setValue(intersectionPoint.getX());
                svgLine.getY1().getBaseVal().setValue(intersectionPoint.getY());
            } else if (lineDistanceInfo != null && lineDistanceInfo.hasEndIntersectionPoint()) {
                IntersectionPoint intersectionPoint = lineDistanceInfo.getEndIntersectionPoint();
                svgLine.getX2().getBaseVal().setValue(intersectionPoint.getX());
                svgLine.getY2().getBaseVal().setValue(intersectionPoint.getY());
            }
        }
    }

    private void computeLongestDistances(int lineIndex, LineDistanceInfo lineDistanceInfo,
                                         IntersectionPoint intersectionPoint) {
        LineDistanceInfo maxLineDistance = lineIdToDistanceInfoMap.get(lineIndex);

        if (maxLineDistance == null) {
            LineDistanceInfo shorterOnlyDistanceInfo = lineDistanceInfo.toShorterOnlyDistanceInfo();
            lineIdToDistanceInfoMap.put(lineIndex, shorterOnlyDistanceInfo);
        } else if (lineDistanceInfo.hasStartIntersectionPoint() &&
                maxLineDistance.getStartPointDistance() < lineDistanceInfo.getStartPointDistance()) {
            maxLineDistance.setStartPointDistance(lineDistanceInfo.getStartPointDistance());
            maxLineDistance.setStartIntersectionPoint(intersectionPoint);
        } else if (lineDistanceInfo.hasEndIntersectionPoint() &&
                maxLineDistance.getEndPointDistance() < lineDistanceInfo.getEndPointDistance()) {
            maxLineDistance.setEndPointDistance(lineDistanceInfo.getEndPointDistance());
            maxLineDistance.setEndIntersectionPoint(intersectionPoint);
        }
    }


}
