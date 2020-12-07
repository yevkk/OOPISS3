package tariff.XMLparsers;

import tariff.XMLparsers.dom.TariffsDOMBuilder;
import tariff.XMLparsers.sax.TariffsSAXBuilder;
import tariff.XMLparsers.stax.TariffsStAXBuilder;

public class TariffsBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    public static TariffsBuilder newInstance(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case SAX:
                return new TariffsSAXBuilder();
            case DOM:
                return new TariffsDOMBuilder();
            case STAX:
                return new TariffsStAXBuilder();
        }
        throw new EnumConstantNotPresentException(type.getClass(), type.name());
    }
}
