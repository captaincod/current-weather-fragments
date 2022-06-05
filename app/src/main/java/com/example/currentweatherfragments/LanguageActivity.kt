package com.example.currentweatherfragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import android.os.Build
import android.view.MenuItem
import android.widget.Toast
import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import java.util.*


class LanguageActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.menu_title)
        toolbar.subtitle = getString(R.string.choose_language)
        toolbar.inflateMenu(R.menu.languages)
        toolbar.setOnMenuItemClickListener { item ->
            toolbar.subtitle = item.toString()
            when (item.toString()){
                "English" -> setLocale(this, "en")
                "Italiano" -> setLocale(this, "it")
            }
            Log.d("mytag","item: $item")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            true}
    }

    fun setLocale(activity: Activity, langCode: String) {
        val locale = Locale(langCode)
        val resources: Resources = activity.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        with(resources) {
            config.setLocale(locale)
            updateConfiguration(config, displayMetrics)
        }
    }
}