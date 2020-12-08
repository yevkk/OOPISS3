package tariff.XMLparsers;

import tariff.Tariff;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class TariffsBuilder {
    protected List<Tariff> tariffs;

    public TariffsBuilder() {
        tariffs = new ArrayList<>();
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public List<Tariff> getTariffsSorted() {
        Comparator<Tariff> cmp = new Comparator<Tariff>() {
            public int compare(Tariff lhs, Tariff rhs) {
                return Integer.compare(lhs.getPayroll(), rhs.getPayroll());
            }
        };

        tariffs.sort(cmp);

        return tariffs;
    }

    public abstract void buildTariffsList(String filename);
}
