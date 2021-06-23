package com.arimbimega.chatapp.Notification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("send")
    @Headers({
            "Content-Type: application/json",
            "Authorization: key=AAAAR021UZ0:APA91bEAZglAOUQ2Ffjmek--XNB2zpHlV5uoUPdT--1_EyaIJ53rR2nFStc53fzX2RmOG5Kee6kB2Ib_-oCNdctWRzNjSGu0JueJxUohBtYn82SCv-72J-saBzzyKpOQd-QA_NjZ20u0"
    })
    Call<MyResponse> sendNotificationMessage(@Body MyRequest myRequest);
}
