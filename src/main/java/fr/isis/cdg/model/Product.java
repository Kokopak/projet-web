/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isis.cdg.model;

/**
 *
 * @author corentin
 */
public class Product {

    private int productId;
    private int manufacturerId;
    private int productCode;
    private float purchaseCost;
    private int quantityOnHand;
    private float markup;
    private String available;
    private String description;

    public Product(int productId, int manufacturerId, int productCode, float purchaseCost, int quantityOnHand, float markup, String available, String description) {
        this.productId = productId;
        this.manufacturerId = manufacturerId;
        this.productCode = productCode;
        this.purchaseCost = purchaseCost;
        this.quantityOnHand = quantityOnHand;
        this.markup = markup;
        this.available = available;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public int getProductCode() {
        return productCode;
    }

    public float getPurchaseCost() {
        return purchaseCost;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public float getMarkup() {
        return markup;
    }

    public String getAvailable() {
        return available;
    }

    public String getDescription() {
        return description;
    }

}
