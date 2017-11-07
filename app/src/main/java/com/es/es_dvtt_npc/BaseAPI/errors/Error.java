package com.es.es_dvtt_npc.BaseAPI.errors;

import com.google.gson.annotations.SerializedName;

/**
 * Created by My_PC on 9/5/2017.
 */

public class Error {
    @SerializedName("error")
    String error;
    @SerializedName("error_description")
    String errorDescription;
    @SerializedName("message")
    String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
