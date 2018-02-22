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
public class DiscountCode {

    private char discountCode;
    private float rate;

    public DiscountCode(char discountCode, float rate) {
        this.discountCode = discountCode;
        this.rate = rate;
    }

    public char getDiscountCode() {
        return discountCode;
    }

    public float getRate() {
        return rate;
    }

}
