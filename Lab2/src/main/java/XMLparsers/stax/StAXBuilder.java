package XMLparsers.stax;

import XMLparsers.BaseHandler;
import XMLparsers.XMLBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StAXBuilder<T extends Comparable<T>> extends XMLBuilder<T> {
    private final BaseHandler handler;
    private final XMLInputFactory inputFactory;

    public StAXBuilder(BaseHandler handler) {
        super();
        this.handler = handler;
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildList(String filename) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String elementName;
        try {
            inputStream = new FileInputStream(new File(filename));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    elementName = reader.getLocalName();
                    if (elementName.equals("tariff")) {
                        handler.createMainElement();
                        for (String str : handler.attrs) {
                            handler.proceedElement(str, reader.getAttributeValue(null, str));
                        }
                        buildElement(reader);
                        handler.saveMainElement();
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
                System.err.println("Impossible to close file " + filename + " " + e);
            }
        }
        list = (List<T>) handler.getList();
    }

    private void buildElement(XMLStreamReader reader) throws XMLStreamException {
        String elementName = reader.getLocalName();
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    String localName = reader.getLocalName();
                    if (handler.complexElements.contains(localName)) {
                        buildElement(reader);
                    } else {
                        handler.proceedElement(localName, getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (reader.getLocalName().equals(elementName)) {
                        return;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tariff");
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
