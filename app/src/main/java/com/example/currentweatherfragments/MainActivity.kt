package com.example.currentweatherfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.currentweatherfragments.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.io.InputStream
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var iconView: ImageView
    lateinit var chooseFragment: Button
    lateinit var editText: EditText
    lateinit var weatherText: TextView

    lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction
    lateinit var IconFragment: Fragment
    lateinit var TextFragment: Fragment

    lateinit var cityName: String
    lateinit var weatherMain: String
    lateinit var mainTemp: String
    lateinit var icon: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.weather = Weather("City name", "Weather", "Temperature")

        fm = supportFragmentManager
        ft = fm.beginTransaction()
        IconFragment = ImageFragment()
        TextFragment = TextFragment()

        iconView = findViewById(R.id.icon_view)
        chooseFragment = findViewById(R.id.choose_fragment)
        editText = findViewById(R.id.edit_text)
        weatherText = findViewById(R.id.weather_textview)

        chooseFragment.setOnClickListener {
            val dialogFragment = AlertDialogFragments()
            val manager = supportFragmentManager
            dialogFragment.show(manager, "dialog") }

    }
    fun onTextFragment(){
        ft.replace(R.id.container_fragm, TextFragment)
        ft.commit()
    }
    fun onIconFragment(){
        ft.replace(R.id.container_fragm, IconFragment)
        ft.commit()
    }

    fun loadWeather() {
        val city = editText.text.toString().filter { !it.isWhitespace() }
        val apiKey: String = getString(R.string.apikey)
        val weatherURL =
            "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$apiKey&units=metric"
        Log.d("mytag", weatherURL)

        try{
            val stream = URL(weatherURL).content as InputStream
            val data = Scanner(stream).nextLine()
            val jsondata = data.trimIndent()
            val weatherData: WeatherJSON = Gson().fromJson<WeatherJSON>(jsondata, WeatherJSON::class.java)
            cityName = weatherData.name
            weatherMain = weatherData.weather[0].main
            mainTemp = weatherData.main.temp
            icon = weatherData.weather[0].icon
            binding.weather = Weather(cityName, weatherMain, mainTemp)
            this@MainActivity.runOnUiThread {
                when(icon){
                    "01d" -> iconView.setImageResource(R.drawable.icon01d)
                    "02d" -> iconView.setImageResource(R.drawable.icon02d)
                    "03d" -> iconView.setImageResource(R.drawable.icon03d)
                    "04d" -> iconView.setImageResource(R.drawable.icon04d)
                    "09d" -> iconView.setImageResource(R.drawable.icon09d)
                    "10d" -> iconView.setImageResource(R.drawable.icon10d)
                    "11d" -> iconView.setImageResource(R.drawable.icon11d)
                    "13d" -> iconView.setImageResource(R.drawable.icon13d)
                    "50d" -> iconView.setImageResource(R.drawable.icon50d)
                    "01n" -> iconView.setImageResource(R.drawable.icon01n)
                    "02n" -> iconView.setImageResource(R.drawable.icon02n)
                    "03n" -> iconView.setImageResource(R.drawable.icon03n)
                    "04n" -> iconView.setImageResource(R.drawable.icon04n)
                    "09n" -> iconView.setImageResource(R.drawable.icon09n)
                    "10n" -> iconView.setImageResource(R.drawable.icon10n)
                    "11n" -> iconView.setImageResource(R.drawable.icon11n)
                    "13n" -> iconView.setImageResource(R.drawable.icon13n)
                    "50n" -> iconView.setImageResource(R.drawable.icon50n)
                }
            }
        } catch (e: FileNotFoundException) {
            this@MainActivity.runOnUiThread {
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
            }
            cityName = "City name"
            weatherMain = "Weather"
            mainTemp = "Temperature"
            icon = "01n"
        }
    }


    @DelicateCoroutinesApi
    fun onClick(view: android.view.View) {
        GlobalScope.launch (Dispatchers.IO) {
            loadWeather()

            val bundleText = Bundle()
            bundleText.putString("main", weatherMain)
            bundleText.putString("temperature", mainTemp)
            TextFragment.arguments = bundleText

            val bundleIcon = Bundle()
            bundleIcon.putString("textIcon", icon)
            IconFragment.arguments = bundleIcon

        }
    }

}



data class Weather(val city_name: String, val weather_main: String, var main_temp: String)

data class WeatherJSON(val coord: Coord, val weather: Array<WeatherArray>, val base: String,
                       val main: WeatherMain, val visibility: Long, val wind: WeatherWind,
                       val clouds: WeatherClouds, val dt: Long, val sys: WeatherSys,
                       val timezone: Long, val id: Long, val name: String, val cod: Int)

data class Coord(val lon: Double, val lat: Double)
data class WeatherArray(val id: Int, val main: String, val description: String, val icon: String)
data class WeatherMain(val temp: String, val feels_like: Double,
                       val temp_min: Double, val temp_max: Double,
                       val pressure: Int, val humidity: Int,
                       val gust: Double = 0.0, val sea_level: Int = 0, val grnd_level:Int = 0)
data class WeatherWind(val speed: String, val deg: Int,
                       val gust: Double = 0.0)
data class WeatherClouds(val all: Int)
data class WeatherSys(val type: Int, val id: Int, val country: String, val sunrise: Long, val sunset: Long)