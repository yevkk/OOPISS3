package XMLparsers.sax;

import XMLparsers.XMLBuilder;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class TariffsSAXBuilder extends XMLBuilder {
    private final TariffHandler handler;
    private XMLReader reader;

    public TariffsSAXBuilder() {
        super();
        handler = new TariffHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            System.err.print("SAX parser error");
        }
    }

    @Override
    public void buildTariffsList (String filename) {
        try {
            reader.parse(filename);
        } catch (SAXException e) {
            System.err.print("SAX parser error");
        } catch (IOException e) {
            System.err.print("I/O stream error");
        }
        tariffs = handler.getTariffs();
    }
}
