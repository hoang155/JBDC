package entity;

public class Invoice {
    private int id;
    private String invoiceCode;
    private double amount;

    public Invoice() {
    }

    public Invoice(int id, String invoiceCode, double amount) {
        this.id = id;
        this.invoiceCode = invoiceCode;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ID : " + id + " , Mã hóa đơn: " + invoiceCode + ", Số tiền: " + amount;
    }
}