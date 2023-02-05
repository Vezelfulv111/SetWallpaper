package com.wallpapersetter

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.wallpapersetter.fragments.CategoryScreenFragment
import com.wallpapersetter.fragments.ErrorFragment
import com.wallpapersetter.fragments.MainScreenFragment
import com.wallpapersetter.fragments.SetupWallpaperFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        //спустя задержку delayMilis переходим на 1й экран
        Handler(Looper.getMainLooper()).postDelayed({
            setContentView(R.layout.activity_main)
            switchToMainScreenFragment()
        }, 300)
    }

    //фунция для перехода на 1й фрагмент
    fun switchToMainScreenFragment() {
        val fragment = MainScreenFragment()
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(android.R.id.content, fragment, "MainScreenFragment")
        ft.commit()
    }

    //фукнция для перехода на 2й фрагмент
    fun switchToCategoryFragment(categoryNum: String) {
        val fragment = CategoryScreenFragment()
        val transaction = supportFragmentManager.beginTransaction()
        val args = Bundle()
        args.putString("categoryNum", categoryNum)//передадим в CategoryScreenFragment выбранную категорию в качестве аргумента
        fragment.arguments = args
        transaction.replace(android.R.id.content, fragment).addToBackStack("CategoryScreenFragment")
        transaction.commit()
    }

    //фукнция для перехода на 3й фрагмент с установкой обоев
    fun switchToSetupWallpaperFragment(ImageUrl: String) {
        val fragment = SetupWallpaperFragment()
        val transaction = supportFragmentManager.beginTransaction()
        val args = Bundle()
        args.putString("ImageUrl", ImageUrl)
        fragment.arguments = args
        transaction.replace(android.R.id.content, fragment).addToBackStack("SetupWallpaperFragment")
        transaction.commit()
    }

    fun switchToErrorFragment(ScreenType: String, categoryName: String ="") {
        val fragment = ErrorFragment()
        val transaction = supportFragmentManager.beginTransaction()
        val args = Bundle()
        args.putString("ScreenType", ScreenType)
        if (categoryName.isNotEmpty()) {
            args.putString("categoryName", categoryName)
        }
        fragment.arguments = args
        transaction.replace(android.R.id.content, fragment).addToBackStack("ErrorFragment")
        transaction.commit()
    }



}
