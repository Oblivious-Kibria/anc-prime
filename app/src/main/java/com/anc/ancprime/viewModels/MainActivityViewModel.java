package com.anc.ancprime.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.anc.ancprime.R;
import com.anc.ancprime.data.model.ApiResponse;
import com.anc.ancprime.data.networking.Repository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;




/**
 * Created by User on 3/15/2020.
 */
public class MainActivityViewModel  extends AndroidViewModel {


    private Repository repository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private String apiToken;
    private int espId;
    private MutableLiveData<ApiResponse> mutableLiveData = new MutableLiveData<>();



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
                .doOnSubscribe(disposable1 -> mutableLiveData.setValue(ApiResponse.loading(null)))
                .subscribe(
                        response -> {
                            Log.d("ApiTesting", "onSuccess " + response.getMessage());
                            mutableLiveData.setValue(ApiResponse.success(response, null));
                        },
                        throwable -> {
                            Log.d("ApiTesting", "onError " + throwable.toString());
                            mutableLiveData.setValue(ApiResponse.error(throwable, null));
                        }
                ));
    }




    public MutableLiveData<ApiResponse> getApiResponse() {
        return mutableLiveData;
    }




    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }


}