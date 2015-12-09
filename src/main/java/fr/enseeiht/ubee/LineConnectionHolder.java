package fr.enseeiht.ubee;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//TODO: refactor this class to use LineEnd properly
public class LineConnectionHolder {
    public enum LineEnd {START, END}

    private List<LineConnection> lineConnections = Lists.newArrayList();

    public void addLineConnection(LineConnection lineConnection) {
        lineConnections.add(lineConnection);
    }

    public LineConnectionLeg findMinStartLineConnectionLeg(int lineIndex) {
        return findMinConnectionLeg(lineIndex, LineEnd.START);
    }

    public LineConnectionLeg findMinEndLineConnectionLeg(int lineIndex) {
        return findMinConnectionLeg(lineIndex, LineEnd.END);
    }

    private LineConnectionLeg findMinConnectionLeg(int lineIndex, LineEnd lineEnd) {
        List<LineConnection> lineConnectionsById = getSortedLineConnectionsById(lineIndex, lineEnd);
        if (Iterables.isEmpty(lineConnectionsById)) {
            return null;
        }
        LineConnection minLineConnection = lineConnectionsById.get(0);

        return minLineConnection.getConnectionLegByLineId(lineIndex);
    }

    private List<LineConnection> getSortedLineConnectionsById(int lineIndex, LineEnd lineEnd) {

        List<LineConnection> lineConnectionsById = getLineConnectionsByLineIdAndLineType(lineIndex, lineEnd);

        Comparator<LineConnection> lineConnectionComparator =
                LineEnd.START.equals(lineEnd) ? new StartDistanceComparator(lineIndex) :
                        new EndDistanceComparator(lineIndex);
        Collections.sort(lineConnectionsById, lineConnectionComparator);
        return lineConnectionsById;
    }

    private List<LineConnection> getLineConnectionsByLineIdAndLineType(int lineIndex, LineEnd lineEnd) {
        return this.lineConnections.stream().filter(connection -> {
            LineConnectionLeg connectionLeg1 = connection.getConnectionLeg1();
            LineConnectionLeg connectionLeg2 = connection.getConnectionLeg2();
            return (connectionLeg1.getLineId() == lineIndex && connectionLeg1.shouldDrawLineEnd(lineEnd)) ||
                    (connectionLeg2.getLineId() == lineIndex && connectionLeg2.shouldDrawLineEnd(lineEnd));
        }).collect(Collectors.toList());
    }

    public void updatStartConnectionsForLine(int lineIndex) {
        List<LineConnection> lineConnectionsById = getSortedLineConnectionsById(lineIndex, LineEnd.START);

        for (int i = 1; i < lineConnectionsById.size(); i++) {
            LineConnection lineConnection = lineConnectionsById.get(i);
            lineConnection.getConnectionLegByLineId(lineIndex).setDrawFromStartToIntersection(false);
        }
    }

    public void updateEndConnectionsForLine(int lineIndex) {
        List<LineConnection> lineConnectionsById = getSortedLineConnectionsById(lineIndex, LineEnd.END);

        for (int i = 1; i < lineConnectionsById.size(); i++) {
            LineConnection lineConnection = lineConnectionsById.get(i);
            lineConnection.getConnectionLegByLineId(lineIndex).setDrawFromEndToIntersection(false);
        }
    }

    public List<LineConnection> getLineConnections() {
        return lineConnections;
    }

    private class StartDistanceComparator implements Comparator<LineConnection> {
        private int lineId;

        public StartDistanceComparator(int lineId) {
            this.lineId = lineId;
        }

        @Override
        public int compare(LineConnection lineConnection1, LineConnection lineConnection2) {
            LineConnectionLeg legToCompare1 = lineConnection1.getConnectionLegByLineId(lineId);
            LineConnectionLeg legToCompare2 = lineConnection2.getConnectionLegByLineId(lineId);

            return Double.compare(legToCompare1.getStartPointDistance(), legToCompare2.getStartPointDistance());
        }
    }

    private class EndDistanceComparator implements Comparator<LineConnection> {
        private int lineId;

        public EndDistanceComparator(int lineId) {
            this.lineId = lineId;
        }

        @Override
        public int compare(LineConnection lineConnection1, LineConnection lineConnection2) {
            LineConnectionLeg legToCompare1 = lineConnection1.getConnectionLegByLineId(lineId);
            LineConnectionLeg legToCompare2 = lineConnection2.getConnectionLegByLineId(lineId);

            return Double.compare(legToCompare1.getEndPointDistance(), legToCompare2.getEndPointDistance());
        }
    }
}
