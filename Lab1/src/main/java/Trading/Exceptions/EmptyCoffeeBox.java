package Trading.Exceptions;

public class EmptyCoffeeBox extends Exception {
    public EmptyCoffeeBox() {
        super("Coffee box is empty");
    }
}
