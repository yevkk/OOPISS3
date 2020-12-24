package tariff.XMLparsers;
import org.junit.jupiter.api.Test;

class DOMBuilderTest {
    @Test
    public void empty() {
        BuilderTestHelper.checkEmpty("dom");
    }

    @Test
    public void single() {
        BuilderTestHelper.checkSingleElements("dom");
    }

    @Test
    public void some() {
        BuilderTestHelper.checkSomeElements("dom");
    }
}