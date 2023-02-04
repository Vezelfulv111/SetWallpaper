package com.wallpapersetter

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var fragment = MainScreenFragment()
        //спустя задержку delayMilis переходим на 1й экран
        Handler(Looper.getMainLooper()).postDelayed({
            setContentView(R.layout.activity_main)
            val fm: FragmentManager = supportFragmentManager
            val ft: FragmentTransaction = fm.beginTransaction()
            ft.add(android.R.id.content, fragment as MainScreenFragment, "MainScreenFragment")
            ft.commit()
        }, 300)
    }

    fun switchToCategoryFragment(categoryNum: String) {
        val manager = supportFragmentManager
        val fragment = CategoryScreenFragment()
        val transaction = manager.beginTransaction()
        val args = Bundle()
        args.putString("categoryNum", categoryNum)
        fragment.arguments = args
        transaction.replace(android.R.id.content, fragment).addToBackStack("CategoryScreenFragment")
        transaction.commit()
    }

    fun switchToSetupWallaperFragment(ImageUrl: String) {
        val manager = supportFragmentManager
        val fragment = SetupWallpaperFragment()
        val transaction = manager.beginTransaction()
        val args = Bundle()
        args.putString("ImageUrl", ImageUrl)
        fragment.arguments = args
        transaction.replace(android.R.id.content, fragment).addToBackStack("SetupWallpaperFragment")
        transaction.commit()
    }


}
