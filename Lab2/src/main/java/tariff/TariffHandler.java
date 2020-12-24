package tariff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import XMLparsers.BaseHandler;
import org.xml.sax.Attributes;

public class TariffHandler extends BaseHandler {
    private final List<Tariff> tariffs;
    private Tariff current = null;
    private TariffEnum currentEnum = null;
    private final EnumSet<TariffEnum> withText;

    @Override
    public String mainElementName() {
        return "tariff";
    }

    public TariffHandler() {
        super();
        tariffs = new ArrayList<>();

        withText = EnumSet.range(TariffEnum.PAYROLL, TariffEnum.JOINPRICE);

        attrs = new ArrayList<>(Arrays.asList("name", "operatorName"));
        complexElements = new ArrayList<>(Arrays.asList("tariffParameters"));
    }

    @Override
    public List<Tariff> getList() {
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (localName.equals("tariff")) {
            createMainElement();
            current.setName(attributes.getValue(0));
            current.setOperatorName(attributes.getValue(1));
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
            saveMainElement();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String str = new String(ch, start, length).trim();
        if (currentEnum != null) {
            proceedElement(currentEnum.getValue(), str);
        }
        currentEnum = null;
    }

    @Override
    public void proceedElement(String elemLocalName, String elemValue) {
        TariffEnum element = TariffEnum.valueOf(elemLocalName.toUpperCase());
        switch (element) {
            case NAME:
                current.setName(elemValue);
                break;
            case OPERATORNAME:
                current.setOperatorName(elemValue);
                break;
            case PAYROLL:
                current.setPayroll(Integer.parseInt(elemValue));
                break;
            case CALLPRICE:
                current.getCallPrice().add(Integer.parseInt(elemValue));
                break;
            case SMSPRICE:
                current.setSmsPrice(Integer.parseInt(elemValue));
                break;
            case FAVNUMBER:
                current.getTariffParameters().setFavNumber(elemValue);
                break;
            case TARIFFICATION:
                current.getTariffParameters().setTariffication(elemValue);
                break;
            case JOINPRICE:
                current.getTariffParameters().setJoinPrice(Integer.parseInt(elemValue));
                break;
            default:
                throw new EnumConstantNotPresentException(currentEnum.getClass(), currentEnum.name());

        }
    }

    @Override
    public void createMainElement() {
        current = new Tariff();
        current.setTariffParameters(new TariffParameters());
    }

    @Override
    public void saveMainElement() {
        tariffs.add(current);
    }
}
