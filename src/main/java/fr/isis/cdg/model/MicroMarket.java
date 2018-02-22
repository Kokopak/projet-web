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
public class MicroMarket {

    private String zipCode;
    private double radius;
    private double areaLength;
    private double areaWidth;

    public MicroMarket(String zipCode, double radius, double areaLength, double areaWidth) {
        this.zipCode = zipCode;
        this.radius = radius;
        this.areaLength = areaLength;
        this.areaWidth = areaWidth;
    }

    public String getZipCode() {
        return zipCode;
    }

    public double getRadius() {
        return radius;
    }

    public double getAreaLength() {
        return areaLength;
    }

    public double getAreaWidth() {
        return areaWidth;
    }

}
