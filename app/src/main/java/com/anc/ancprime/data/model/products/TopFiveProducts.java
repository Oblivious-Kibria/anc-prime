package com.anc.ancprime.data.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




/**
 * Created by User on 3/18/2020.
 */
public class TopFiveProducts {

    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("unit_price")
    @Expose
    private float unitPrice;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("pro_name")
    @Expose
    private String proName;




    public String getQuantity() {
        return quantity;
    }




    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }




    public float getUnitPrice() {
        return unitPrice;
    }




    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }




    public Integer getProductId() {
        return productId;
    }




    public void setProductId(Integer productId) {
        this.productId = productId;
    }




    public String getProName() {
        return proName;
    }




    public void setProName(String proName) {
        this.proName = proName;
    }

}
