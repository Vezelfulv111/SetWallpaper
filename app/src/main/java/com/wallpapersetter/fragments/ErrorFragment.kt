package com.wallpapersetter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.wallpapersetter.MainActivity
import com.wallpapersetter.R


//экран ошибки загрузки
class ErrorFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_error, container, false)

        var screenType = ""
        screenType = arguments?.getString("ScreenType", "MainScreenFragment")!!//значение по умолчанию 1й -экран
        var categoryname = ""
        categoryname = arguments?.getString("categoryName", "")!!

        val reloadButton: Button = view.findViewById(R.id.reloadButton)
        reloadButton.setOnClickListener() {
            if (screenType == "CategoryScreenFragment" && categoryname.isNotEmpty()) {
                (activity as MainActivity).switchToCategoryFragment(categoryname)
            }
            else  {
                (activity as MainActivity).switchToMainScreenFragment()
            }

        }
        return view
    }
}