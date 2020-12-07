package tariff.XMLparsers.dom;

import org.xml.sax.SAXException;
import tariff.*;
import tariff.XMLparsers.TariffsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TariffsDOMBuilder extends TariffsBuilder {
    private DocumentBuilder documentBuilder;

    public TariffsDOMBuilder() {
        super();
        this.tariffs = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.print("DOM parser config error");
        }
    }

    @Override
    public void buildTariffsSet(String filename) {
        Document document = null;
        try {
            document = documentBuilder.parse(filename);
            Element root = document.getDocumentElement();
            NodeList tariffsList = root.getElementsByTagName("tariff");
            for (int i = 0; i < tariffsList.getLength(); i++) {
                tariffs.add(buildTariff((Element) tariffsList.item(i)));
            }
        } catch (SAXException e) {
            System.err.print("SAX parser error");
        } catch (IOException e) {
            System.err.print("I/O stream error");
        }
    }

    private Tariff buildTariff(Element tariffElement) {
        Tariff tariff = new Tariff();

        tariff.setName(tariffElement.getAttribute("name"));
        tariff.setOperatorName(tariffElement.getAttribute("operatorName"));

        tariff.setPayroll(Integer.parseInt(getElementsTextContent(tariffElement, "payroll").get(0)));
        tariff.setSmsPrice(Integer.parseInt(getElementsTextContent(tariffElement, "smsPrice").get(0)));

        List<String> callPriceStr = getElementsTextContent(tariffElement, "callPrice");
        for (String str : callPriceStr) {
            tariff.getCallPrice().add(Integer.parseInt(str));
        }

        TariffParameters tariffParams = new TariffParameters();
        Element tariffParamsElement = (Element) tariffElement.getElementsByTagName("tariffParameters").item(0);
        tariffParams.setFavNumber(getElementsTextContent(tariffParamsElement, "favNumber").get(0));
        tariffParams.setTariffication(getElementsTextContent(tariffParamsElement, "tariffication").get(0));
        tariffParams.setJoinPrice(Integer.parseInt(getElementsTextContent(tariffParamsElement, "joinPrice").get(0)));
        tariff.setTariffParameters(tariffParams);

        return tariff;
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
