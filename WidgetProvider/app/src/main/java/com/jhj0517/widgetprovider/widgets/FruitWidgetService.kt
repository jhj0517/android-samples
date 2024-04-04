package com.jhj0517.widgetprovider.widgets

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
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

    override fun onCreate() {
        super.onCreate()

        lifecycleScope.launch {
            fruitsRepository.latestFruit.collectLatest {
                Log.d("FruitWidgetService", "${it.name}")
                updateWidget(it)
            }
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
        stopService(Intent(this, FruitWidgetService::class.java))
        super.onDestroy()
    }
}