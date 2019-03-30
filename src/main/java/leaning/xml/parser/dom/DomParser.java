package leaning.xml.parser.dom;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomParser {
    public void parsingTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        Node node = null;
        Node nameNode = null;
        Element element = null;
        Element nameElement = null;
        String nameStr = "";
        try {
            // 클래스패스에서 읽어들임.
            File xmlFile = new File(classLoader.getResource("xml_using.xml").getFile());

            // DocumentBuilderFactory 생성
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // DocumentBuilder 생성
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // Document 생성 => xml 받아서
            Document doc = dBuilder.parse(xmlFile);

            // normalize => Node 클래스로부터 상속받은 메소드
            // 형식 정규화
            doc.getDocumentElement().normalize();
            
            System.out.println("Root 요소명 :" + doc.getDocumentElement().getNodeName());

            // student 태그로 리스트를 만듦.
            NodeList nodeList = doc.getElementsByTagName("student");
            NodeList nameList = null;
            for( int listIndex = 0; listIndex < nodeList.getLength(); listIndex++ ) {
                node = nodeList.item(listIndex);
                nameStr = "이름 : (";

                // 타입이 요소 일 경우
                if( node.getNodeType() == Node.ELEMENT_NODE ) {
                    element = (Element)node;

                    // getAttribute : 해당 요소의 속성을 가져옴. <student no="">
                    // getElementsByTagName : 해당 요소에 저장된 태그를 가져옴
                    // getElementsByTagName의 리턴 타입은 NodeList
                    System.out.println("학번 : " + element.getAttribute("no"));

                    nameList = element.getElementsByTagName("name");
                    
                    for( int nameIndex = 0; nameIndex < nameList.getLength(); nameIndex++ ) {
                        nameNode = nameList.item(nameIndex);

                        if(nameNode.getNodeType() == Node.ELEMENT_NODE) {
                            nameElement = (Element)nameNode;

                            nameStr += nameElement.getTextContent() + ", ";
                        }
                    }

                    nameStr = nameStr.substring(0, nameStr.lastIndexOf(","));

                    nameStr += ")";

                    System.out.println(nameStr);
                    System.out.println("수학 점수 : " + element.getElementsByTagName("math").item(0).getTextContent());
                    System.out.println("영어 점수 : " + element.getElementsByTagName("english").item(0).getTextContent());

                    System.out.println("------------------------------");
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
}