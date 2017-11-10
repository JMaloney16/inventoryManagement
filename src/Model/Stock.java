package Model;

public class Stock {
    private int sku;
    private String name;
    private int quantity;
    private String category;

    public Stock(int sku, String name, int quantity, String category) {
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
