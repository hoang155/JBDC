package ra.entity;

public class Coffee extends Drink {

    public Coffee() {
    }

    public Coffee(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public void prepare() {
        System.out.println("Pha bằng máy");
    }
}