/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package leaning;

import com.google.gson.*;

import json.*;
import json.gson.GsonTester;

import java.util.*;

import leaning.xml.parser.dom.*;
import leaning.xml.parser.sax.*;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class App {

    public static void main(String[] args) {
        ClassLoader classLoader = App.class.getClassLoader();

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
