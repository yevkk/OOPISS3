package XMLparsers.sax;

import XMLparsers.BaseHandler;
import XMLparsers.XMLBuilder;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SAXBuilder<T extends Comparable<T>> extends XMLBuilder<T> {
    private final BaseHandler handler;
    private XMLReader reader;

    public SAXBuilder(BaseHandler handler) {
        super();
        this.handler = handler;
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            System.err.print("SAX parser error");
        }
    }

    @Override
    public void buildList(String filename) {
        try {
            reader.parse(filename);
        } catch (SAXException e) {
            System.err.print("SAX parser error");
        } catch (IOException e) {
            System.err.print("I/O stream error");
        }
        list = (List<T>) handler.getList();
    }
}
