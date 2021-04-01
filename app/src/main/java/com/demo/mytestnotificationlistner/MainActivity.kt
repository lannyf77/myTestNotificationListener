package com.demo.mytestnotificationlistner

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat


class MainActivity : AppCompatActivity(), MyListener {
    private var txtView: TextView? = null
    val NOTIFICATION_CHANNEL_ID = "10001"
    private val default_notification_channel_id = "default"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NotificationService().setListener(this)
        txtView = findViewById(R.id.textView)
        val btnCreateNotification: Button = findViewById(R.id.btnCreateNotification)
        btnCreateNotification.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val mNotificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                val mBuilder =
                    NotificationCompat.Builder(this@MainActivity, default_notification_channel_id)
                mBuilder.setContentTitle("My Notification")
                mBuilder.setContentText("Notification Listener Service Example")
                mBuilder.setTicker("Notification Listener Service Example")
                mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
                mBuilder.setAutoCancel(true)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val importance = NotificationManager.IMPORTANCE_HIGH
                    val notificationChannel = NotificationChannel(
                        NOTIFICATION_CHANNEL_ID,
                        "NOTIFICATION_CHANNEL_NAME",
                        importance
                    )
                    mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
                    assert(mNotificationManager != null)
                    mNotificationManager.createNotificationChannel(notificationChannel)
                }
                assert(mNotificationManager != null)
                mNotificationManager.notify(System.currentTimeMillis().toInt(), mBuilder.build())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu) //Menu Resource, Menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.action_settings -> {
                val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setValue(packageName: String) {
        txtView?.append(" \n " + packageName)
    }
}
