package ra.entity;

public class FruitTea extends Drink {

    public FruitTea() {
    }

    public FruitTea(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public void prepare() {
        System.out.println("Lắc với đá và trái cây tươi");
    }
}