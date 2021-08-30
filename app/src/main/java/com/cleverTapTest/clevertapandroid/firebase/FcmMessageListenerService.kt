package com.cleverTapTest.clevertapandroid.firebase

import android.os.Bundle
import android.util.Log
import com.clevertap.android.sdk.CleverTapAPI
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson


class FcmMessageListenerService : FirebaseMessagingService() {



    override fun onMessageReceived(message: RemoteMessage) {
        try
        {
            if (message.data.isNotEmpty())
            {
                val extras = Bundle()
                for (entry in message.data.entries)
                {
                    extras.putString(entry.key, entry.value)
                }
                val info = CleverTapAPI.getNotificationInfo(extras)
                if (info.fromCleverTap)
                {
                    CleverTapAPI.createNotification(applicationContext, extras)
                }
            } else{
                Log.e("FCM", "empty " + message.data)
            }
        }
        catch (t: Throwable) {
            Log.e("FCM", "Error parsing FCM message", t)
        }
    }
    override fun onNewToken(token: String) {
        Log.e("FCM Token", token)
       CleverTapAPI.getDefaultInstance(this)?.pushFcmRegistrationId(token, true)
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(token);
    }
}