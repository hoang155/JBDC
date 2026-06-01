package entity;
import java.sql.Date;

public class Product {
    private int id;
    private String name;
    private double price;
    private String title;
    private Date created;
    private String catalog;
    private String status; // Lưu "0" hoặc "1" dưới dạng chuỗi đại diện cho trạng thái Bit

    public Product(String name, double price, String title, Date created, String catalog, String status) {
        this.name = name;
        this.price = price;
        this.title = title;
        this.created = created;
        this.catalog = catalog;
        this.status = status;
    }

    public Product(int id, String name, double price, String title, Date created, String catalog, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.title = title;
        this.created = created;
        this.catalog = catalog;
        this.status = status;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getTitle() { return title; }
    public Date getCreated() { return created; }
    public String getCatalog() { return catalog; }
    public String getStatus() { return status; }
}