package XMLparsers.dom;

import XMLparsers.BaseHandler;
import XMLparsers.XMLBuilder;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOMBuilder<T extends Comparable<T>> extends XMLBuilder<T> {
    private final BaseHandler handler;
    private DocumentBuilder documentBuilder;

    public DOMBuilder(BaseHandler handler) {
        super();
        this.handler = handler;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.print("DOM parser config error");
        }
    }

    @Override
    public void buildList(String filename) {
        Document document;
        try {
            document = documentBuilder.parse(filename);
            Element root = document.getDocumentElement();
            NodeList mainElementsList = root.getElementsByTagName(handler.mainElementName());
            for (int i = 0; i < mainElementsList.getLength(); i++) {
                handler.createMainElement();
                Element element = (Element) mainElementsList.item(i);
                for (String str : handler.attrs) {
                    handler.proceedElement(str, element.getAttribute(str));
                }
                buildElement(element);
                handler.saveMainElement();
            }
        } catch (SAXException e) {
            System.err.print("SAX parser error");
        } catch (IOException e) {
            System.err.print("I/O stream error");
        }
        list = (List<T>) handler.getList();
    }

    private void buildElement(Element element) {
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            String childName = childNodes.item(i).getNodeName();
            if (handler.complexElements.contains(childName)) {
                buildElement((Element) childNodes.item(i));
            } else {
                for (String str : getElementsTextContent(element, childName)){
                    handler.proceedElement(childName, str);
                }
            }
        }
    }

    private static List<String> getElementsTextContent(Element parent, String elementName) {
        NodeList nodeList = parent.getElementsByTagName(elementName);
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            strList.add(nodeList.item(i).getTextContent());
        }
        return strList;
    }
}
