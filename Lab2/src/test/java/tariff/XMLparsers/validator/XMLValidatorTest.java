package tariff.XMLparsers.validator;

import XMLparsers.validator.XMLValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class XMLValidatorTest {
    private final String schemeFilename = "src/main/resources/Tariff.xsd";

    @Test
    public void validEmpty() {
        Assertions.assertTrue(
                XMLValidator.validate("src/test/resources/emptyTariffs.xml", schemeFilename)
        );
    }

    @Test
    public void validSingle() {
        Assertions.assertTrue(
                XMLValidator.validate("src/test/resources/singleTariffs.xml", schemeFilename)
        );
    }

    @Test
    public void validSome() {
        Assertions.assertTrue(
                XMLValidator.validate("src/test/resources/someTariffs.xml", schemeFilename)
        );
    }

    @Test
    public void invalidElements() {
        Assertions.assertFalse(
                XMLValidator.validate("src/test/resources/invalidTariffsElementsName.xml", schemeFilename)
        );
    }

    @Test
    public void invalidElementsNumber() {
        Assertions.assertFalse(
                XMLValidator.validate("src/test/resources/invalidTariffsElementsNumber.xml", schemeFilename)
        );
    }

    @Test
    public void invalidFacets() {
        Assertions.assertFalse(
                XMLValidator.validate("src/test/resources/invalidTariffsFacets.xml", schemeFilename)
        );
    }

}