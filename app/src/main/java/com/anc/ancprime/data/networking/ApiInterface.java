package com.anc.ancprime.data.networking;


import com.anc.ancprime.data.model.products.ProductSummaryResponse;
import com.anc.ancprime.data.model.salesFlow.SalesFlowSummary;
import com.anc.ancprime.data.model.summary.SalesSummaryResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;




public interface ApiInterface {


    @POST(HttpParams.SALES_SUMMARY_API)
    Observable<SalesSummaryResponse> requestSalesSummary();


    @POST(HttpParams.TOP_SELLING_AND_LEAST_SELLING_PRODUCT_API)
    Observable<ProductSummaryResponse> requestTopSellingAndLeastSellingProducts();


    @POST(HttpParams.SALES_FLOW_SUMMARY_API)
    Observable<SalesFlowSummary> requestSalesFlowSummary();


    @POST(HttpParams.TOP_CUSTOMERS_API)
    Observable<SalesFlowSummary> requestTopCustomers();

}
