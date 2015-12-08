package fr.enseeiht.ubee;

import com.google.common.collect.Lists;
import org.apache.batik.dom.svg.SVGOMLineElement;
import org.w3c.dom.NodeList;

import java.util.List;

public class LinePairFinder {
    public List<LinePair> findLinesIntersections(NodeList svgLines) {
        List<Line> lines = convertToLines(svgLines);

        return makeLinePairs(lines);
    }

    private List<LinePair> makeLinePairs(List<Line> lines) {
        List<LinePair> linePairs = Lists.newArrayList();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                LinePair linePair = new LinePair(lines.get(i), lines.get(j));
                linePairs.add(linePair);
            }
        }

        return linePairs;
    }

    private List<Line> convertToLines(NodeList svgLines) {
        int linesCount = svgLines.getLength();
        List<Line> lines = Lists.newArrayList();
        for (int lineIndex = 0; lineIndex < linesCount; lineIndex++) {
            SVGOMLineElement svgLine = (SVGOMLineElement) svgLines.item(lineIndex);
            Line line = new Line(svgLine.getX1().getBaseVal().getValue(), svgLine.getX2().getBaseVal().getValue(),
                    svgLine.getY1().getBaseVal().getValue(), svgLine.getY2().getBaseVal().getValue(), lineIndex);
            lines.add(line);
        }

        return lines;
    }
}
