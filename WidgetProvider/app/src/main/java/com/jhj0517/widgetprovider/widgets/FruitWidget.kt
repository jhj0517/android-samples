package com.jhj0517.widgetprovider.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.RemoteViews
import com.jhj0517.widgetprovider.MainActivity
import com.jhj0517.widgetprovider.R
import com.jhj0517.widgetprovider.models.ExampleData
import javax.inject.Inject

const val widgetUpdateInterval = 2000L // 2 seconds
class FruitWidget : AppWidgetProvider(){

    private val handler: Handler = Handler(Looper.getMainLooper())
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        // There might be many widgets, so update each of them
        appWidgetIds!!.forEach { appWidgetId ->

            val views = RemoteViews(
                context.packageName,
                R.layout.widget_fruit
            )

            // Schedule the next update
            handler.postDelayed(
                { onUpdate(context, appWidgetManager, appWidgetIds) },
                widgetUpdateInterval
            )
        }
    }

    private fun updateView(views: RemoteViews, data: ExampleData): RemoteViews {
        views.setTextViewText(R.id.tv_name, data.name)
        views.setImageViewResource(R.id.iv_image, data.image)
        return views
    }
}