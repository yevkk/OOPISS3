package XMLparsers.validator;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.transform.stream.StreamSource;

public class XMLValidator {
    public static boolean validate(String sourceFilename, String schemaFilename) {
        try {
            Schema schema = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new File(schemaFilename));
            schema.newValidator().validate(new StreamSource(new File(sourceFilename)));
        } catch (SAXException e) {
//            System.out.println("SAX exception " + e);
            return false;
        } catch (IOException e) {
//            System.out.println("I/O exception " + e);
            return false;
        }
        return true;
    }
}
