package com.anc.ancprime.data.networking;


import com.anc.ancprime.data.model.summary.SalesSummaryResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;




public interface ApiInterface {




    @POST(HttpParams.SALES_SUMMARY_API)
    Observable<SalesSummaryResponse> requestSalesSummary();




}
