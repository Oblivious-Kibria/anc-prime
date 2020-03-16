package com.anc.ancprime.data.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;




public class RetrofitClient {


    private static Retrofit retrofitMain = null;
    private static Retrofit retrofitLogin = null;




    public static synchronized ApiInterface getApiInterface(String apiToken) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        Interceptor headerAuthorizationInterceptor = (Interceptor.Chain chain) -> {
            okhttp3.Request request = chain.request();

            Headers headers = request.headers().newBuilder()
                    .add("Connection", "close")
                    .add("Cache-Control", "no-cache")
                    .add("Cache-Control", "no-store")
                    .add("Authorization", "Bearer " + apiToken).build();
            request = request.newBuilder().headers(headers).build();
            return chain.proceed(request);
        };


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(false)
                .addInterceptor(headerAuthorizationInterceptor)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        if (retrofitMain == null) {

            retrofitMain = new Retrofit.Builder()
                    .baseUrl(HttpParams.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofitMain.create(ApiInterface.class);
    }




    public static synchronized ApiInterface getLoginApiInterface() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .retryOnConnectionFailure(false)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        if (retrofitLogin == null) {

            retrofitLogin = new Retrofit.Builder()
                    .baseUrl(HttpParams.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofitLogin.create(ApiInterface.class);
    }




    public static void invalidateRetrofitInstances() {
        retrofitMain = null;
        retrofitLogin = null;
    }


}
