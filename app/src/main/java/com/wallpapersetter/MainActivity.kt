package com.wallpapersetter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.squareup.picasso.Picasso
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.Locale.Category
import kotlin.concurrent.fixedRateTimer


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        var fragment = MainScreenFragment()
        //спустя задержку переходим на 1й экрран
        Handler(Looper.getMainLooper()).postDelayed({
            setContentView(R.layout.activity_main)
            val fm: FragmentManager = supportFragmentManager
            val ft: FragmentTransaction = fm.beginTransaction()
            ft.add(android.R.id.content, fragment as MainScreenFragment, "MainScreenFragment")
            ft.commit()
        }, 4000)

    }


}
