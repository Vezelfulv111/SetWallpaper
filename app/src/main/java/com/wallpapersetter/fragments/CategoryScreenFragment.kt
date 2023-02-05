package com.wallpapersetter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.wallpapersetter.MainActivity
import com.wallpapersetter.R
import com.wallpapersetter.adapters.CategoryAdapter
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


//Фрагмент отображения картинок из выбранной категории
class CategoryScreenFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_category_screen, container, false)
        val categoryName = arguments?.getString("categoryNum", "backgrounds")!!//получили выбранную категорию при запуске фрагмента
        val chosenCategory = view.findViewById(R.id.chosenCategory) as TextView
        chosenCategory.text = "Выберите обои:"
        setImages(categoryName)
        return view
    }

    //функция API запроса картинок выбранной категории с их последущей установкой в GridView
    private fun setImages(id: String) {
        val client = OkHttpClient()
        val key = resources.getString(R.string.requestKey)
        val requestUrl ="https://pixabay.com/api/?key=$key&category=$id&safesearch=true&per_page=30"
        val request: Request = Request.Builder()
            .url(requestUrl)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                (activity as MainActivity).switchToErrorFragment("CategoryScreenFragment",id)
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                        if (!response.isSuccessful) {
                            throw IOException("Unexpected code $response")
                        }
                        else {
                            val str = response.body!!.string()
                            val jsonData = JSONObject(str)//получили json массив
                            val hits = jsonData.get("hits") as JSONArray
                            (activity as MainActivity).runOnUiThread {
                               //прогресс бар убирается в случае успешного запроса
                               val progressBar = view?.findViewById(R.id.progressBar) as ProgressBar
                               progressBar.visibility = View.GONE
                               val simpleGrid = view?.findViewById(R.id.simpleGridView) as GridView
                               val customAdapter = CategoryAdapter(view!!.context, hits, activity as MainActivity)
                               simpleGrid.adapter = customAdapter
                            }
                        }
                    }
                }
        })
    }
}