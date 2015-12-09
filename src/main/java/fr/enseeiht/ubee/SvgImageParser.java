package fr.enseeiht.ubee;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGOMLineElement;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SvgImageParser {

    public static void main(String[] args) throws IOException {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        URL url = Resources.getResource("mire7.svg");
        SVGDocument doc = f.createSVGDocument(url.toString());
        NodeList svgLines = doc.getDocumentElement().getElementsByTagName("line");
        int linesCount = svgLines.getLength();
        List<Line> lines = Lists.newArrayList();

        for (int lineIndex = 0; lineIndex < linesCount; lineIndex++) {
            SVGOMLineElement svgLine = (SVGOMLineElement) svgLines.item(lineIndex);
            Line line = new Line(svgLine.getX1().getBaseVal().getValue(), svgLine.getX2().getBaseVal().getValue(),
                    svgLine.getY1().getBaseVal().getValue(), svgLine.getY2().getBaseVal().getValue(), lineIndex);
            lines.add(line);
        }

        List<LinePair> linePairs = new LinePairFinder().findLinesIntersections(svgLines);
        new LineConnectionDrawer().drawConnectionsForPairs(linePairs, svgLines);
        new SvgFileExporter().exportToSvg(doc);
    }
}
