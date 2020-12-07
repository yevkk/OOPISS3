package tariff.XMLparsers.sax;

import tariff.Tariff;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class TariffsSAXBuilder {
    private Set<Tariff> tariffs;
    private final TariffHandler handler;
    private XMLReader reader;

    public TariffsSAXBuilder() {
        handler = new TariffHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            System.err.print("SAX parser error");
        }
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    public void buildTariffsSet (String filename) {
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
