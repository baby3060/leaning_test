package leaning.xml.parser.sax;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxRunner {
    public void runningSax() {
        ClassLoader classLoader = getClass().getClassLoader();

        try {
            File inputFile = new File(classLoader.getResource("xml_using.xml").getFile());
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXHandler saxHandler = new SAXHandler();
            saxParser.parse(inputFile, saxHandler);     
         } catch (Exception e) {
            e.printStackTrace();
         }
    }
}