package tariff.XMLparsers;

import XMLparsers.XMLBuilderFactory;
import XMLparsers.XMLBuilder;
import org.junit.jupiter.api.Assertions;
import tariff.Tariff;

import java.util.List;

public class BuilderTestHelper {
    static XMLBuilder builder;
    static List<Tariff> tariffs;
    static void checkEmpty(String typeParser) {
        builder = XMLBuilderFactory.newInstance(typeParser);
        builder.buildList("src/test/resources/emptyTariffs.xml");
        tariffs = builder.getList();

        Assertions.assertEquals(0, tariffs.size());
    }

    static void checkSingleElements(String typeParser) {
        builder = XMLBuilderFactory.newInstance(typeParser);
        builder.buildList("src/test/resources/singleTariffs.xml");
        tariffs = builder.getList();

        Assertions.assertEquals(1, tariffs.size());

        Tariff tariff = tariffs.get(0);
        Assertions.assertEquals("L", tariff.getName());
        Assertions.assertEquals("O2", tariff.getOperatorName());
        Assertions.assertEquals(150, tariff.getPayroll());
        Assertions.assertEquals(0, tariff.getCallPrice().get(0));
        Assertions.assertEquals(1, tariff.getCallPrice().get(1));
        Assertions.assertEquals(4, tariff.getCallPrice().get(2));
        Assertions.assertEquals(2, tariff.getSmsPrice());
        Assertions.assertEquals("0991114433", tariff.getTariffParameters().getFavNumber());
        Assertions.assertEquals("min", tariff.getTariffParameters().getTariffication());
        Assertions.assertEquals(75, tariff.getTariffParameters().getJoinPrice());
    }

    static void checkSomeElements(String typeParser) {
        builder = XMLBuilderFactory.newInstance(typeParser);
        builder.buildList("src/test/resources/someTariffs.xml");
        tariffs = builder.getList();

        Assertions.assertEquals(3, tariffs.size());
        Tariff tariff;

        tariff = tariffs.get(0);
        Assertions.assertEquals("S", tariff.getName());
        Assertions.assertEquals("O2", tariff.getOperatorName());
        Assertions.assertEquals(85, tariff.getPayroll());
        Assertions.assertEquals(1, tariff.getCallPrice().get(0));
        Assertions.assertEquals(2, tariff.getCallPrice().get(1));
        Assertions.assertEquals(5, tariff.getCallPrice().get(2));
        Assertions.assertEquals(1, tariff.getSmsPrice());
        Assertions.assertEquals("0778881122", tariff.getTariffParameters().getFavNumber());
        Assertions.assertEquals("min", tariff.getTariffParameters().getTariffication());
        Assertions.assertEquals(50, tariff.getTariffParameters().getJoinPrice());

        tariff = tariffs.get(1);
        Assertions.assertEquals("L", tariff.getName());
        Assertions.assertEquals("O2", tariff.getOperatorName());
        Assertions.assertEquals(150, tariff.getPayroll());
        Assertions.assertEquals(0, tariff.getCallPrice().get(0));
        Assertions.assertEquals(1, tariff.getCallPrice().get(1));
        Assertions.assertEquals(4, tariff.getCallPrice().get(2));
        Assertions.assertEquals(2, tariff.getSmsPrice());
        Assertions.assertEquals("0991114433", tariff.getTariffParameters().getFavNumber());
        Assertions.assertEquals("min", tariff.getTariffParameters().getTariffication());
        Assertions.assertEquals(75, tariff.getTariffParameters().getJoinPrice());

        tariff = tariffs.get(2);
        Assertions.assertEquals("Plush", tariff.getName());
        Assertions.assertEquals("Plus", tariff.getOperatorName());
        Assertions.assertEquals(50, tariff.getPayroll());
        Assertions.assertEquals(1, tariff.getCallPrice().get(0));
        Assertions.assertEquals(2, tariff.getCallPrice().get(1));
        Assertions.assertEquals(2, tariff.getCallPrice().get(2));
        Assertions.assertEquals(1, tariff.getSmsPrice());
        Assertions.assertEquals("0331231212", tariff.getTariffParameters().getFavNumber());
        Assertions.assertEquals("12sec", tariff.getTariffParameters().getTariffication());
        Assertions.assertEquals(15, tariff.getTariffParameters().getJoinPrice());
    }
}
