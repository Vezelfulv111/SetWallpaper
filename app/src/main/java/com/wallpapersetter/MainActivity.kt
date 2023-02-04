package com.wallpapersetter
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


        var fragment = MainScreenFragment()
        //спустя задержку delayMilis переходим на 1й экрран
        Handler(Looper.getMainLooper()).postDelayed({
            setContentView(R.layout.activity_main)
            val fm: FragmentManager = supportFragmentManager
            val ft: FragmentTransaction = fm.beginTransaction()
            ft.add(android.R.id.content, fragment as MainScreenFragment, "MainScreenFragment")
            ft.commit()
        }, 300)

    }

    fun switchToCategoryFragment(categoryNum: Int) {
        val manager = supportFragmentManager
        val fragment = CategoryScreenFragment()
        val transaction = manager.beginTransaction()
        val args = Bundle()
        args.putInt("categoryNum", categoryNum)
        fragment.arguments = args
        transaction.replace(android.R.id.content, fragment).addToBackStack("CategoryScreenFragment")
        transaction.commit()
    }


}
