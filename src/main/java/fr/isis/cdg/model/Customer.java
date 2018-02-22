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
public class Customer {

    private int customerId;
    private char discountCode;
    private String zipCode;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String fax;
    private String email;
    private int creditLimit;

    public Customer(int customerId, char discountCode, String zipCode, String name, String addressLine1, String addressLine2, String city, String state, String fax, String email, int creditLimit) {
        this.customerId = customerId;
        this.discountCode = discountCode;
        this.zipCode = zipCode;
        this.name = name;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.fax = fax;
        this.email = email;
        this.creditLimit = creditLimit;
    }

    public int getCustomerId() {
        return customerId;
    }

    public char getDiscountCode() {
        return discountCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getName() {
        return name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

}
