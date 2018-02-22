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
public class ProductCode {

    private String productCode;
    private char discountCode;
    private String description;

    public ProductCode(String productCode, char discountCode, String description) {
        this.productCode = productCode;
        this.discountCode = discountCode;
        this.description = description;
    }

    public String getProductCode() {
        return productCode;
    }

    public char getDiscountCode() {
        return discountCode;
    }

    public String getDescription() {
        return description;
    }

}
