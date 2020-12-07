package tariff.XMLparsers;

import tariff.Tariff;

import java.util.Set;

public abstract class TariffsBuilder {
    protected Set<Tariff> tariffs;

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    public abstract void buildTariffsSet(String filename);
}
