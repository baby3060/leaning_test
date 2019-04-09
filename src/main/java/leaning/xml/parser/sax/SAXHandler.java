package leaning.xml.parser.sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

public class SAXHandler extends DefaultHandler {

    // startDocument : 문서를 읽을 때 처음 실행
    // endDocument : 문서를 다 읽은 뒤에 실행
    // startElement : 요소를 읽을 때
    // endElement : 요소를 다 읽은 뒤
    // characters : 문서의 요소를 읽어서 변환해줄 때 사용(startElement ~ endElement 사이)

    @Override
    public void startDocument() {
        System.out.println("Start Document");
    }

    @Override
    public void endDocument() {
        System.out.println("End Document");
    }

    private boolean isName1 = false;
    private boolean isName2 = false;
    private boolean isMath = false;
    private boolean isEnglish = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
       
        if( qName.equals("class") ) {

        } else {
            if( qName.equalsIgnoreCase("student") ) {
                System.out.println("Student Name Is : " + atts.getValue("no"));
            } 

            if( qName.equalsIgnoreCase("name") ) {
                if(isName1) {
                    isName2 = true;
                } else {
                    isName1 = true;
                }

            } else if( qName.equalsIgnoreCase("math") ) {
                isMath = true;
            } else if(qName.equalsIgnoreCase("english")) {
                isEnglish = true;
            }
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("student")) { 
            System.out.println();
        }
    }

    @Override
    public void characters (char ch[], int start, int length) {
        if(isName1) {
            System.out.println("First Name Is : " + new String(ch, start, length));
            isName1 = false;
        } else if(isName2) {
            System.out.println("Second Name Is : " + new String(ch, start, length));
            isName2 = false;
        } else if(isMath) {
            System.out.println("Math Score Is : " + new String(ch, start, length));
            isMath = false;
        } else if(isEnglish) {
            System.out.println("English Score Is : " + new String(ch, start, length));
            isEnglish = false;
        }
    }
}