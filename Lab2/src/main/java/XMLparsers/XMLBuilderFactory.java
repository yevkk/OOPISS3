package XMLparsers;

import XMLparsers.sax.SAXBuilder;
//import XMLparsers.dom.TariffsDOMBuilder;
//import XMLparsers.stax.TariffsStAXBuilder;

public class XMLBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    public  static <T extends Comparable<T>> XMLBuilder<T> newInstance(String typeParser, BaseHandler handler) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case SAX:
                return new SAXBuilder<T>(handler);
//            case DOM:
//                return new TariffsDOMBuilder();
//            case STAX:
//                return new TariffsStAXBuilder();
        }
        throw new EnumConstantNotPresentException(type.getClass(), type.name());
    }
}
