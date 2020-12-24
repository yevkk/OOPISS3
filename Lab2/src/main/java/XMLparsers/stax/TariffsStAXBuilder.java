package XMLparsers.stax;

import tariff.*;
import XMLparsers.XMLBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class TariffsStAXBuilder extends XMLBuilder {
    private XMLInputFactory inputFactory;

    public TariffsStAXBuilder() {
        super();
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildTariffsList(String filename) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String elementName;
        try {
            inputStream = new FileInputStream(new File(filename));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    elementName = reader.getLocalName();
                    if (elementName.equals("tariff")) {
                        tariffs.add(buildTariff(reader));
                    }
                }
            }
        } catch (XMLStreamException e) {
            System.err.println("StAX parser error " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("File " + filename + " not found " + e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file " + filename + " " + e);
            }
        }
    }

    private Tariff buildTariff(XMLStreamReader reader) throws XMLStreamException {
        Tariff tariff = new Tariff();
        tariff.setName(reader.getAttributeValue(null, "name"));
        tariff.setOperatorName(reader.getAttributeValue(null, "operatorName"));

        String elementName;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = reader.getLocalName();
                    switch (TariffEnum.valueOf(elementName.toUpperCase())) {
                        case PAYROLL:
                            tariff.setPayroll(Integer.parseInt(getXMLText(reader)));
                            break;
                        case CALLPRICE:
                            tariff.getCallPrice().add(Integer.parseInt(getXMLText(reader)));
                            break;
                        case SMSPRICE:
                            tariff.setSmsPrice(Integer.parseInt(getXMLText(reader)));
                            break;
                        case TARIFFPARAMETERS:
                            tariff.setTariffParameters(getXMLTariffParameters(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = reader.getLocalName();
                    if (elementName.equals("tariff")) {
                        return tariff;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tariff");
    }

    private TariffParameters getXMLTariffParameters(XMLStreamReader reader) throws XMLStreamException {
        TariffParameters tariffParams = new TariffParameters();
        int type;
        String elementName;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = reader.getLocalName();
                    switch (TariffEnum.valueOf(elementName.toUpperCase())) {
                        case FAVNUMBER:
                            tariffParams.setFavNumber(getXMLText(reader));
                            break;
                        case TARIFFICATION:
                            tariffParams.setTariffication(getXMLText(reader));
                            break;
                        case JOINPRICE:
                            tariffParams.setJoinPrice(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = reader.getLocalName();
                    if (elementName.equals("tariffParameters")) {
                        return tariffParams;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tariffParameters");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
