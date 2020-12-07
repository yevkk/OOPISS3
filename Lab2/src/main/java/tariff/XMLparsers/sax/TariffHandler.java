package tariff.XMLparsers.sax;

import tariff.*;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class TariffHandler extends DefaultHandler {
    private final Set<Tariff> tariffs;
    private Tariff current = null;
    private TariffEnum currentEnum = null;
    private final EnumSet<TariffEnum> withText;

    public TariffHandler() {
        tariffs = new HashSet<>();
        withText = EnumSet.range(TariffEnum.PAYROLL, TariffEnum.JOINPRICE);
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (localName.equals("tariff")) {
            current = new Tariff();
            current.setName(attributes.getValue(0));
            current.setOperatorName(attributes.getValue(1));
            current.setTariffParameters(new TariffParameters());
        } else {
            TariffEnum tmp = TariffEnum.valueOf(localName.toUpperCase());
            if (withText.contains(tmp)) {
                currentEnum = tmp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (localName.equals("tariff")) {
            tariffs.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String str = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case PAYROLL:
                    current.setName(str);
                    break;
                case CALLPRICE:
                    current.getCallPrice().add(Integer.parseInt(str));
                    break;
                case SMSPRICE:
                    current.setSmsPrice(Integer.parseInt(str));
                    break;
                case FAVNUMBER:
                    current.getTariffParameters().setFavNumber(str);
                    break;
                case TARIFFICATION:
                    current.getTariffParameters().setTariffication(str);
                    break;
                case JOINPRICE:
                    current.getTariffParameters().setJoinPrice(Integer.parseInt(str));
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentEnum.getClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
