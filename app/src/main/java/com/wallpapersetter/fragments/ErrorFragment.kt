package com.wallpapersetter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.wallpapersetter.MainActivity
import com.wallpapersetter.R


//экран ошибки загрузки
class ErrorFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_error, container, false)

        val screenType = arguments?.getString("ScreenType", "MainScreenFragment")!!//значение по умолчанию 1й -экран
        val categoryname = arguments?.getString("categoryName", "")!!

        val reloadButton: Button = view.findViewById(R.id.reloadButton)
        reloadButton.setOnClickListener() {//переходим на определенный фрагмент в зависимости от категории
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