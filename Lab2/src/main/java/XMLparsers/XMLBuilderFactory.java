package XMLparsers;

import XMLparsers.sax.SAXBuilder;
import XMLparsers.dom.DOMBuilder;
//import XMLparsers.stax.TariffsStAXBuilder;

public class XMLBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    public  static <T extends Comparable<T>> XMLBuilder<T> newInstance(String typeParser, BaseHandler handler) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case SAX:
                return new SAXBuilder<>(handler);
            case DOM:
                return new DOMBuilder<>(handler);
//            case STAX:
//                return new TariffsStAXBuilder();
        }
        throw new EnumConstantNotPresentException(type.getClass(), type.name());
    }
}
