package mainPak;

public class PurchaseOrder {
    private int poID;
    private int invoiceNo;
    private int sku;
    private int quantity;
    private int unitCost;

    public PurchaseOrder(int poID, int invoiceNo, int sku, int quantity, int unitCost) {
        this.poID = poID;
        this.invoiceNo = invoiceNo;
        this.sku = sku;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

    public int getPoID() {
        return poID;
    }

    public void setPoID(int poID) {
        this.poID = poID;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(int unitCost) {
        this.unitCost = unitCost;
    }
}
