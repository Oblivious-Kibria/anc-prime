package com.anc.ancprime.data.networking;


import com.anc.ancprime.data.model.customer.TopCustomersResponse;
import com.anc.ancprime.data.model.products.ProductSummaryResponse;
import com.anc.ancprime.data.model.salesFlow.SalesFlowSummary;
import com.anc.ancprime.data.model.summary.SalesSummaryResponse;

import io.reactivex.Observable;




public class Repository {


    private static Repository repositoryInstance;




    private Repository() {
    }




    public static Repository getRepositoryInstance() {
        if (repositoryInstance == null) {
            repositoryInstance = new Repository();
        }
        return repositoryInstance;
    }




    public Observable<SalesSummaryResponse> executeSalesSummaryRequest(String apiToken) {
        return RetrofitClient.getApiInterface(apiToken).requestSalesSummary();
    }




    public Observable<ProductSummaryResponse> executeTopAndLeastSellingProductRequest(String apiToken) {
        return RetrofitClient.getApiInterface(apiToken).requestTopSellingAndLeastSellingProducts();
    }




    public Observable<SalesFlowSummary> executeSalesFlowSummaryRequest(String apiToken) {
        return RetrofitClient.getApiInterface(apiToken).requestSalesFlowSummary();
    }




    public Observable<TopCustomersResponse> executeTopCustomersRequest(String apiToken) {
        return RetrofitClient.getApiInterface(apiToken).requestTopCustomers();
    }

}
