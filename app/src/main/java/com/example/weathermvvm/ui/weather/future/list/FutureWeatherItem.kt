package com.example.weathermvvm.ui.weather.future.list

import com.example.weathermvvm.R
import com.example.weathermvvm.data.db.unitlocalized.future.list.MetricSimpleFutureWeatherEntry
import com.example.weathermvvm.data.db.unitlocalized.future.list.UnitSpecificSimpleFutureWeatherEntry
import com.example.weathermvvm.internal.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_future_weather.*
import kotlinx.android.synthetic.main.item_future_weather.textView_condition
import kotlinx.android.synthetic.main.item_future_weather.textView_temperature
import org.threeten.bp.format.DateTimeFormatter



class FutureWeatherItem(
    val weatherEntry: UnitSpecificSimpleFutureWeatherEntry
) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_condition.text = weatherEntry.conditionText
            textView_date.text = weatherEntry.date.format(DateTimeFormatter.ISO_LOCAL_DATE)
            updateTemperature()
            updateConditionImage()
        }
    }

    override fun getLayout() = R.layout.item_future_weather

    private fun ViewHolder.updateTemperature(){
        val unitAbbreviation = if (weatherEntry is MetricSimpleFutureWeatherEntry) "°C"
        else "°F"
        textView_temperature.text = "${weatherEntry.avgTemperature}$unitAbbreviation"
    }

    private fun ViewHolder.updateConditionImage(){
        GlideApp.with(this.containerView)
            .load("https:"+ weatherEntry.conditionIconUrl)
            .into(imageView_condition_icon)
    }
}