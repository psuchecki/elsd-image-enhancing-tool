package fr.enseeiht.ubee;

import com.google.common.io.Resources;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGOMLineElement;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

import java.io.IOException;
import java.net.URL;

public class SvgImageParser {

    public static void main(String[] args) throws IOException {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        URL url = Resources.getResource("mire5.svg");
        SVGDocument doc = f.createSVGDocument(url.toString());
        NodeList lines = doc.getDocumentElement().getElementsByTagName("line");
        int linesCount = lines.getLength();

        for (int lineIndex = 0; lineIndex < linesCount; lineIndex++) {
            SVGOMLineElement line = (SVGOMLineElement) lines.item(lineIndex);
            System.out.println(line.getX1().getBaseVal().getValue());
        }
    }
}
