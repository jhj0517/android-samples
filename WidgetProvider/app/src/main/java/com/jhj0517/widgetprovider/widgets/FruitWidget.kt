package com.jhj0517.widgetprovider.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.RemoteViews
import com.jhj0517.widgetprovider.R
import com.jhj0517.widgetprovider.models.ExampleData

class FruitWidget : AppWidgetProvider(){

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {

    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }

}