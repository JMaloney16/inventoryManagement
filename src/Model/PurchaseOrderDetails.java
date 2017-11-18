package Model;

import java.sql.Date;

public class PurchaseOrderDetails {
    private int invoiceNo;
    private int supplierID;
    private int cost;
    private Date shippingDate;

    public PurchaseOrderDetails(int invoiceNo, int supplierID, int cost, Date shippingDate) {
        this.invoiceNo = invoiceNo;
        this.supplierID = supplierID;
        this.cost = cost;
        this.shippingDate = shippingDate;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }
}
