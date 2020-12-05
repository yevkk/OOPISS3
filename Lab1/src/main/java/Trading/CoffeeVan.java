package Trading;

import Trading.Exceptions.VanOverflow;
import Trading.Exceptions.VanTotalCostOverflow;
import Trading.Exceptions.VanTotalVolumeOverflow;

import java.util.ArrayList;
import java.util.Comparator;

public class CoffeeVan {
    private ArrayList<CoffeeBox> products;
    private int maxVolume;
    private double maxCost;

    public CoffeeVan(int maxVolume, double maxCost) {
        if (maxVolume < 0 || maxCost < 0) {
            throw new IllegalArgumentException("max cost and max volume values should be non-negative");
        }
        this.maxCost = maxCost;
        this.maxVolume = maxVolume;
        products = new ArrayList<>();
    }

    public CoffeeVan(int maxVolume, double maxCost, CoffeeBox... products) throws VanOverflow {
        this(maxVolume, maxCost);
        this.fill(products);
    }

    public ArrayList<CoffeeBox> getProducts() {
        return products;
    }

    public int getMaxVolume() {
        return maxVolume;
    }

    public double getMaxCost() {
        return maxCost;
    }

    public void fill(CoffeeBox... products) throws VanOverflow {
        for (var item : products) {
            addProduct(item);
        }
    }

    private void addProduct(CoffeeBox box) throws VanOverflow {
        double cost = 0;
        int volume = 0;
        for (var item : products) {
            cost += item.cost();
            volume += item.volume();
        }
        if (cost + box.cost() > maxCost) {
            throw new VanTotalCostOverflow(maxCost,cost + box.cost());
        }
        if (volume + box.volume() > maxVolume) {
            throw new VanTotalVolumeOverflow(maxVolume, volume + box.volume());
        }
        products.add(box);
    }

    public void sortProducts() {
        var comp = new Comparator<CoffeeBox>() {
            public int compare(CoffeeBox lhs, CoffeeBox rhs) {
                return Double.compare(lhs.cost() / lhs.volume(), rhs.cost() / rhs.volume());
            }
        };

        products.sort(comp);
    }

    public CoffeeSearcher searchItems() {
        return new CoffeeSearcher(products.toArray(new CoffeeBox[0]));
    }
}
