package fr.enseeiht.ubee;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LineConnectionHolder {

    private List<LineConnection> lineConnections = Lists.newArrayList();

    public void addLineConnection(LineConnection lineConnection) {
        lineConnections.add(lineConnection);
    }

    public LineConnectionLeg findMinStartLineConnectionLeg(int lineIndex) {
        return findMinConnectionLeg(lineIndex, new StartDistanceComparator(lineIndex));
    }

    public LineConnectionLeg findMinEndLineConnectionLeg(int lineIndex) {
        return findMinConnectionLeg(lineIndex, new EndDistanceComparator(lineIndex));
    }

    private LineConnectionLeg findMinConnectionLeg(int lineIndex, Comparator<LineConnection> startDistanceComparator) {
        if (Iterables.isEmpty(lineConnections)) {
            return null;
        }

        List<LineConnection> lineConnectionsById = getSortedLineConnectionsById(lineIndex, startDistanceComparator);
        LineConnection minLineConnection = lineConnectionsById.get(0);

        return minLineConnection.getConnectionLegByLineId(lineIndex);
    }

    private List<LineConnection> getSortedLineConnectionsById(int lineIndex,
                                                              Comparator<LineConnection> startDistanceComparator) {
        List<LineConnection> lineConnectionsById = getLineConnectionsByLineId(lineIndex);

        Collections.sort(lineConnectionsById, startDistanceComparator);
        return lineConnectionsById;
    }

    private List<LineConnection> getLineConnectionsByLineId(int lineIndex) {
        return this.lineConnections.stream()
                .filter(connection -> connection.getConnectionLeg1().getLineId() == lineIndex ||
                        connection.getConnectionLeg2().getLineId() == lineIndex).collect(Collectors.toList());
    }

    public void updatStartConnectionsForLine(int lineIndex) {
        List<LineConnection> lineConnectionsById =
                getSortedLineConnectionsById(lineIndex, new StartDistanceComparator(lineIndex));

        for (int i = 1; i < lineConnectionsById.size(); i++) {
            LineConnection lineConnection = lineConnectionsById.get(i);
            lineConnection.getConnectionLegByLineId(lineIndex).setDrawFromStartToIntersection(false);
        }
    }

    public void updateEndConnectionsForLine(int lineIndex) {
        List<LineConnection> lineConnectionsById =
                getSortedLineConnectionsById(lineIndex, new EndDistanceComparator(lineIndex));

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
