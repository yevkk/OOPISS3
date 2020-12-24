package tariff.XMLparsers;
import org.junit.jupiter.api.Test;

class TariffsSAXBuilderTest {
    @Test
    public void empty() {
        BuilderTestHelper.checkEmpty("sax");
    }

    @Test
    public void single() {
        BuilderTestHelper.checkSingleElements("sax");
    }

    @Test
    public void some() {
        BuilderTestHelper.checkSomeElements("sax");
    }
}