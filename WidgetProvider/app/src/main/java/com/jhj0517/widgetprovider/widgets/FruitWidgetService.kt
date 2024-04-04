package com.jhj0517.widgetprovider.widgets

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.jhj0517.widgetprovider.MainActivity
import com.jhj0517.widgetprovider.R
import com.jhj0517.widgetprovider.models.ExampleData
import com.jhj0517.widgetprovider.repositories.FruitsRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FruitWidgetService: LifecycleService() {

    @Inject
    lateinit var fruitsRepository: FruitsRepository

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        lifecycleScope.launch {
            fruitsRepository.latestFruit.collectLatest {
                updateWidget(it)
            }
        }
    }

    private fun startForeground() {
        try {
            val notification = createNotification()
            ServiceCompat.startForeground(
                /* service = */ this,
                /* id = */ 100,
                /* notification = */ notification,
                /* foregroundServiceType = */ FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        } catch (e: Exception) {
            Log.d("FruitWidgetService", "${e}")
        }
    }

    private fun updateWidget(data: ExampleData) {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val widget = ComponentName(this, FruitWidget::class.java)
        val allWidgetIds = appWidgetManager.getAppWidgetIds(widget)

        // There might be many widgets, so update each of them
        for (widgetId in allWidgetIds) {
            val newView = updateView(data)
            appWidgetManager.updateAppWidget(widgetId, newView)
        }
    }

    private fun updateView(newData: ExampleData): RemoteViews{
        val views = RemoteViews(
            this.packageName,
            R.layout.widget_fruit
        )
        views.setTextViewText(R.id.tv_name, newData.name)
        views.setImageViewResource(R.id.iv_image, newData.image)
        return views
    }

    override fun onDestroy() {
        stopSelf()
        super.onDestroy()
    }

    private fun createNotification(): Notification {
        // build Notification by Android API versions
        val channelId = "CHANNEL ID"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                channelId,
                "Fruits Widget Service Channel Name",
                NotificationManager.IMPORTANCE_HIGH
            ).let {
                it.description = "This is fruits widget service channel"
                it
            }
            notificationManager.createNotificationChannel(channel)
        }

        val pendingIntent: PendingIntent = Intent(this, MainActivity::class.java).let { notificationIntent ->
            PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
        }

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Fruits Widget Service")
            .setContentText("Fruits Widget Service is working")
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.strawberry)
            .build()
    }
}