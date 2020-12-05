package Trading;

import Items.CoffeeItem;
import Trading.Exceptions.EmptyCoffeBox;

public class CoffeeBox {
    private final CoffeeItem item;
    private int number;

    public CoffeeBox(CoffeeItem item, int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("number should have positive value");
        }
        this.item = item;
        this.number = number;
    }

    public CoffeeItem getItem() {
        return item;
    }

    public int getNumber() {
        return number;
    }

    public double cost() {
        return number * item.price();
    }

    public int volume() {
        return number * item.getVolume();
    }

    public void consumeItem() throws EmptyCoffeBox {
        if (number > 0) {
            number--;
        } else {
            throw new EmptyCoffeBox();
        }
    }
}
