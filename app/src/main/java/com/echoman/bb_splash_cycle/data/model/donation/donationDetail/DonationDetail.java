
package com.echoman.bb_splash_cycle.data.model.donation.donationDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonationDetail {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private DonationDetailsData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DonationDetailsData getData() {
        return data;
    }

    public void setData(DonationDetailsData data) {
        this.data = data;
    }

}
