package tariff.XMLparsers.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TariffsXMLValidatorTest {
    private final String schemeFilename = "src/main/resources/Tariff.xsd";

    @Test
    public void validEmpty() {
        Assertions.assertTrue(
                TariffsXMLValidator.validate("src/test/resources/emptyTariffs.xml", schemeFilename)
        );
    }

    @Test
    public void validSingle() {
        Assertions.assertTrue(
                TariffsXMLValidator.validate("src/test/resources/singleTariffs.xml", schemeFilename)
        );
    }

    @Test
    public void validSome() {
        Assertions.assertTrue(
                TariffsXMLValidator.validate("src/test/resources/someTariffs.xml", schemeFilename)
        );
    }

    @Test
    public void invalidElements() {
        Assertions.assertFalse(
                TariffsXMLValidator.validate("src/test/resources/invalidTariffsElementsName.xml", schemeFilename)
        );
    }

    @Test
    public void invalidElementsNumber() {
        Assertions.assertFalse(
                TariffsXMLValidator.validate("src/test/resources/invalidTariffsElementsNumber.xml", schemeFilename)
        );
    }

    @Test
    public void invalidFacets() {
        Assertions.assertFalse(
                TariffsXMLValidator.validate("src/test/resources/invalidTariffsFacets.xml", schemeFilename)
        );
    }

}