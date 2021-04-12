package com.demo.mytestnotificationlistner

import android.content.Context
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationService : NotificationListenerService() {
    private val TAG = this.javaClass.simpleName
    var context: Context? = null
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    /**
     * if no android:permission = "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE"
     * then the NotificationListenerService will not be called
     */

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        Log.i(TAG, "*+++ onNotificationPosted")
        Log.i(
            TAG,
            "+++ ID :" + sbn.id + " \t " + sbn.notification.tickerText + " \t " + sbn.packageName
        )
        myListener!!.setValue("Post: " + sbn.packageName)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        Log.i(TAG, "+++ onNotificationRemoved")
        Log.i(
            TAG,
            "+++ ID :" + sbn.id + " \t " + sbn.notification.tickerText + " \t " + sbn.packageName
        )
        myListener!!.setValue("Remove: " + sbn.packageName)
    }

    fun setListener(myListener: MyListener?) {
        Companion.myListener = myListener
    }

    companion object {
        var myListener: MyListener? = null
    }
}
