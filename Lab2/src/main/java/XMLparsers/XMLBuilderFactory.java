package XMLparsers;

import XMLparsers.sax.SAXBuilder;
import XMLparsers.dom.TariffsDOMBuilder;
import XMLparsers.stax.TariffsStAXBuilder;

public class XMLBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    public static XMLBuilder newInstance(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case SAX:
                return new SAXBuilder();
            case DOM:
                return new TariffsDOMBuilder();
            case STAX:
                return new TariffsStAXBuilder();
        }
        throw new EnumConstantNotPresentException(type.getClass(), type.name());
    }
}
