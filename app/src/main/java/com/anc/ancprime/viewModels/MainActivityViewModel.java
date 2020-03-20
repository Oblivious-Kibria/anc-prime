package com.anc.ancprime.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.anc.ancprime.R;
import com.anc.ancprime.data.constants.AppConstants;
import com.anc.ancprime.data.model.ApiResponse;
import com.anc.ancprime.data.networking.Repository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by User on 3/15/2020.
 */
public class MainActivityViewModel extends AndroidViewModel {


    private Repository repository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private String apiToken;
    private MutableLiveData<ApiResponse> salesSummaryMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ApiResponse> productSummaryMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ApiResponse> salesFlowSummaryMutableLiveData = new MutableLiveData<>();


    public MainActivityViewModel(Application application) {
        super(application);
        repository = Repository.getRepositoryInstance();
        apiToken = application.getString(R.string.api_token);
    }


    public void loadSalesSummary() {
        Log.d("ApiTesting", " ApiToken " + apiToken);
        disposable.add(repository.executeSalesSummaryRequest(apiToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> salesSummaryMutableLiveData.setValue(ApiResponse.loading(AppConstants.REQUEST_TYPE_SALES_SUMMARY)))
                .subscribe(
                        response -> {
                            Log.d("ApiTesting", "onSuccess " + response.getMessage());
                            salesSummaryMutableLiveData.setValue(ApiResponse.success(response, AppConstants.REQUEST_TYPE_SALES_SUMMARY));
                        },
                        throwable -> {
                            Log.d("ApiTesting", "onError " + throwable.toString());
                            salesSummaryMutableLiveData.setValue(ApiResponse.error(throwable, AppConstants.REQUEST_TYPE_SALES_SUMMARY));
                        }
                ));
    }


    public void loadProductSummary() {
        Log.d("ApiTesting", " ApiToken " + apiToken);
        disposable.add(repository.executeTopAndLeastSellingProductRequest(apiToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> productSummaryMutableLiveData.setValue(ApiResponse.loading(AppConstants.REQUEST_TYPE_PRODUCT_SUMMARY)))
                .subscribe(
                        response -> {
                            Log.d("ApiTesting", "onSuccess " + response.getMessage());
                            productSummaryMutableLiveData.setValue(ApiResponse.success(response, AppConstants.REQUEST_TYPE_PRODUCT_SUMMARY));
                        },
                        throwable -> {
                            Log.d("ApiTesting", "onError " + throwable.toString());
                            productSummaryMutableLiveData.setValue(ApiResponse.error(throwable, AppConstants.REQUEST_TYPE_PRODUCT_SUMMARY));
                        }
                ));
    }


    public void loadSalesFlowSummary() {
        Log.d("ApiTesting", " ApiToken " + apiToken);
        disposable.add(repository.executeSalesFlowSummaryRequest(apiToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> salesFlowSummaryMutableLiveData.setValue(ApiResponse.loading(AppConstants.REQUEST_TYPE_SALES_FLOW_SUMMARY)))
                .subscribe(
                        response -> {
                            Log.d("ApiTesting", "onSuccess " + response.getMessage());
                            salesFlowSummaryMutableLiveData.setValue(ApiResponse.success(response, AppConstants.REQUEST_TYPE_SALES_FLOW_SUMMARY));
                        },
                        throwable -> {
                            Log.d("ApiTesting", "onError " + throwable.toString());
                            salesFlowSummaryMutableLiveData.setValue(ApiResponse.error(throwable, AppConstants.REQUEST_TYPE_SALES_FLOW_SUMMARY));
                        }
                ));
    }


    public MutableLiveData<ApiResponse> getSalesSummaryResponse() {
        return salesSummaryMutableLiveData;
    }


    public MutableLiveData<ApiResponse> getProductSummaryResponse() {
        return productSummaryMutableLiveData;
    }


    public MutableLiveData<ApiResponse> getSalesFlowSummaryResponse() {
        return salesFlowSummaryMutableLiveData;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }


}