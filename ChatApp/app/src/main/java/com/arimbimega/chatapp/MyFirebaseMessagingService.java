package com.arimbimega.chatapp;


import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static String TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onNewToken( String token) {
        super.onNewToken(token);

        //Log.d(TAG, "Get Token : " + token);
    }
}