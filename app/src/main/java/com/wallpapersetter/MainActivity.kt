package com.wallpapersetter

import android.content.Intent
import android.graphics.Insets.add
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            setContentView(R.layout.activity_main)
                val fm: FragmentManager = supportFragmentManager
                var fragment = fm.findFragmentByTag("MainScreenFragment")
            if (fragment == null) {
                val ft: FragmentTransaction = fm.beginTransaction()
                fragment = MainScreenFragment()
                ft.add(android.R.id.content, fragment as MainScreenFragment, "MainScreenFragment")
                ft.commit()
            }
        }, 1000)
    }
}
