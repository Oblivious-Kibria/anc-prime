package com.anc.ancprime.data.model.customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerData {
    @SerializedName("amount")
    @Expose
    private float amount;
    @SerializedName("cust_id")
    @Expose
    private Integer custId;
    @SerializedName("cust_name")
    @Expose
    private String custName;
    @SerializedName("cust_code")
    @Expose
    private String custCode;
    @SerializedName("cust_owner_profile_image")
    @Expose
    private Object custOwnerProfileImage;
    @SerializedName("cust_address")
    @Expose
    private String address;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public Object getCustOwnerProfileImage() {
        return custOwnerProfileImage;
    }

    public void setCustOwnerProfileImage(Object custOwnerProfileImage) {
        this.custOwnerProfileImage = custOwnerProfileImage;
    }

    public String getCustAddress() {
        return address;
    }

    public void setCustAddress(String address) {
        this.address = address;
    }
}
