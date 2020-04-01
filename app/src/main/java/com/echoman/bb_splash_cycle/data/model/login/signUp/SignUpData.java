
package com.echoman.bb_splash_cycle.data.model.login.signUp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("client")
    @Expose
    private Client client;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}