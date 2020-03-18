package com.anc.ancprime.data.model.products;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




/**
 * Created by User on 3/18/2020.
 */
public class ProductData {
    @SerializedName("lastDay")
    @Expose
    private LastDay lastDay;
    @SerializedName("lastWeek")
    @Expose
    private LastWeek lastWeek;
    @SerializedName("lastMonth")
    @Expose
    private LastMonth lastMonth;

    public LastDay getLastDay() {
        return lastDay;
    }

    public void setLastDay(LastDay lastDay) {
        this.lastDay = lastDay;
    }

    public LastWeek getLastWeek() {
        return lastWeek;
    }

    public void setLastWeek(LastWeek lastWeek) {
        this.lastWeek = lastWeek;
    }

    public LastMonth getLastMonth() {
        return lastMonth;
    }

    public void setLastMonth(LastMonth lastMonth) {
        this.lastMonth = lastMonth;
    }
}
