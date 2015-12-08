package fr.enseeiht.ubee;

import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import java.io.*;

public class SvgFileExporter {
    public void exportToSvg(SVGDocument document) throws IOException {
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        Element documentElement = svgGenerator.getDOMFactory().getDocumentElement();
        OutputStream outputStream = new FileOutputStream(new File("output.svg"));
        Writer out = new OutputStreamWriter(outputStream, "UTF-8");
        svgGenerator.stream(documentElement, out);
        outputStream.flush();
        outputStream.close();
    }
}
