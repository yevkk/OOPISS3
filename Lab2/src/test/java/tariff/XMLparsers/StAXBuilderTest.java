package tariff.XMLparsers;
import org.junit.jupiter.api.Test;

class StAXBuilderTest {
    @Test
    public void empty() {
        BuilderTestHelper.checkEmpty("stax");
    }

    @Test
    public void single() {
        BuilderTestHelper.checkSingleElements("stax");
    }

    @Test
    public void some() {
        BuilderTestHelper.checkSomeElements("stax");
    }
}