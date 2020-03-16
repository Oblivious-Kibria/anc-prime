package com.anc.ancprime.data.model.summary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




/**
 * Created by User on 3/15/2020.
 */
public class LastMonth implements MyPieChartData{
    @SerializedName("total_sub_total")
    @Expose
    private float totalSubTotal;
    @SerializedName("total_vat")
    @Expose
    private float totalVat;
    @SerializedName("total_discount")
    @Expose
    private float totalDiscount;
    @SerializedName("total_payable")
    @Expose
    private float totalPayable;
    @SerializedName("total_due")
    @Expose
    private float totalDue;

    public float getTotalSubTotal() {
        return totalSubTotal;
    }

    public void setTotalSubTotal(float totalSubTotal) {
        this.totalSubTotal = totalSubTotal;
    }

    public float getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(float totalVat) {
        this.totalVat = totalVat;
    }

    public float getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(float totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public float getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(float totalPayable) {
        this.totalPayable = totalPayable;
    }

    public float getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(float totalDue) {
        this.totalDue = totalDue;
    }
}
