package com.anc.ancprime.views.adapters;

/**
 * Created by User on 3/15/2020.
 */
public class Customer {

    String name;
    String image;
    String address;
    float purchaseValue;




    public Customer(String name, String image, String address, float purchaseValue) {
        this.name = name;
        this.image = image;
        this.address = address;
        this.purchaseValue = purchaseValue;
    }




    public String getName() {
        return name;
    }




    public Customer setName(String name) {
        this.name = name;
        return this;
    }




    public String getImage() {
        return image;
    }




    public Customer setImage(String image) {
        this.image = image;
        return this;
    }




    public String getAddress() {
        return address;
    }




    public Customer setAddress(String address) {
        this.address = address;
        return this;
    }




    public float getPurchaseValue() {
        return purchaseValue;
    }




    public Customer setPurchaseValue(float purchaseValue) {
        this.purchaseValue = purchaseValue;
        return this;
    }

}
