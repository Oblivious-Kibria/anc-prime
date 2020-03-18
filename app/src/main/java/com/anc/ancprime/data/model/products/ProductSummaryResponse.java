package com.anc.ancprime.data.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;




/**
 * Created by User on 3/18/2020.
 */
public class ProductSummaryResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private ProductData data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errors")
    @Expose
    private List<Object> errors = null;




    public Boolean getStatus() {
        return status;
    }




    public void setStatus(Boolean status) {
        this.status = status;
    }




    public ProductData getData() {
        return data;
    }




    public void setData(ProductData data) {
        this.data = data;
    }




    public String getMessage() {
        return message;
    }




    public void setMessage(String message) {
        this.message = message;
    }




    public List<Object> getErrors() {
        return errors;
    }




    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

}
