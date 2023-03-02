package br.com.multreaders.model;

import java.math.BigDecimal;

public class Vehicle {

    private String type;
    private int axis;

    private double price;

    private String state;

    public Vehicle() {

    }

    public Vehicle(String type, int axis, double price, String state) {
        this.type = type;
        this.axis = axis;
        this.price = price;
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAxis() {
        return axis;
    }

    public void setAxis(int axis) {
        this.axis = axis;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type='" + type + '\'' +
                ", axis=" + axis +
                ", price=" + price +
                ", state='" + state + '\'' +
                '}';
    }
}
