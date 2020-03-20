package com.anc.ancprime.data.model.salesFlow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesData {
    @SerializedName("total_sale_count")
    @Expose
    private Integer totalSaleCount;
    @SerializedName("total_amount")
    @Expose
    private float totalAmount;
    @SerializedName("new_date")
    @Expose
    private String newDate;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("month")
    @Expose
    private Integer month;

    public Integer getTotalSaleCount() {
        return totalSaleCount;
    }

    public void setTotalSaleCount(Integer totalSaleCount) {
        this.totalSaleCount = totalSaleCount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
