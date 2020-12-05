package Trading.Exceptions;

public class EmptyCoffeBox extends Exception {
    public EmptyCoffeBox() {
        super("Coffee box is empty");
    }
}
