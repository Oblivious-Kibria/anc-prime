package com.anc.ancprime.data.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;




/**
 * Created by User on 3/18/2020.
 */
public class LastDay {
    @SerializedName("top5Product")
    @Expose
    private List<TopFiveProducts> top5Product = null;
    @SerializedName("low5Product")
    @Expose
    private List<LeastFiveProducts> low5Product = null;

    public List<TopFiveProducts> getTop5Product() {
        return top5Product;
    }

    public void setTop5Product(List<TopFiveProducts> top5Product) {
        this.top5Product = top5Product;
    }

    public List<LeastFiveProducts> getLow5Product() {
        return low5Product;
    }

    public void setLow5Product(List<LeastFiveProducts> low5Product) {
        this.low5Product = low5Product;
    }
}
