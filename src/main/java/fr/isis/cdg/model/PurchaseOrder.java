/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isis.cdg.model;

import java.util.Date;

/**
 *
 * @author corentin
 */
public class PurchaseOrder {

    private int orderNum;
    private int customerId;
    private int productId;
    private int quantity;
    private float shippingCost;
    private Date salesDate;
    private Date shipDate;
    private String freightCompany;

    public PurchaseOrder(int orderNum, int customerId, int productId, int quantity, float shippingCost, Date salesDate, Date shipDate, String freightCompany) {
        this.orderNum = orderNum;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.shippingCost = shippingCost;
        this.salesDate = salesDate;
        this.shipDate = shipDate;
        this.freightCompany = freightCompany;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getShippingCost() {
        return shippingCost;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public String getFreightCompany() {
        return freightCompany;
    }

}
