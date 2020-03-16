package com.anc.ancprime.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.anc.ancprime.data.model.Status.ERROR;
import static com.anc.ancprime.data.model.Status.LOADING;
import static com.anc.ancprime.data.model.Status.SUCCESS;




/**
 * Created by User on 7/24/2019.
 */
public class ApiResponse {

    public final Status status;

    @Nullable
    public final Object data;

    @Nullable
    public final Throwable error;


    public String requestType;



    private ApiResponse(Status status, @Nullable Object data, @Nullable Throwable error, String requestType) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.requestType = requestType;
    }



    public static ApiResponse loading(String requestType) {
        return new ApiResponse(LOADING, null, null, requestType);
    }



    public static ApiResponse success(@NonNull Object data, String requestType) {
        return new ApiResponse(SUCCESS, data, null, requestType);
    }



    public static ApiResponse error(@NonNull Throwable error, String requestType) {
        return new ApiResponse(ERROR, null, error, requestType);
    }

}
