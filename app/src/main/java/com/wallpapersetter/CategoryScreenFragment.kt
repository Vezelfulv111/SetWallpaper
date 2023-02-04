package com.wallpapersetter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class CategoryScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_category_screen, container, false)

        var categoryNum = 0
        categoryNum = arguments?.getInt("categoryNum", 0)!!//получили выбранную категорию при запуске фрагмента
        setImages(categoryNum)

        return view
    }

    private fun setImages(id : Int) {
        val client = OkHttpClient()
        val key = "33106230-b104905cd7ff74ed17e2229af"
        val categoryArray = resources.getStringArray(R.array.Categories)
        val category = categoryArray[id-1]
        val requestUrl ="https://pixabay.com/api/?key=$key&category=$category&safesearch=true&per_page=30"
        val request: Request = Request.Builder()
            .url(requestUrl)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        throw IOException("Unexpected code $response")

                    } else {
                        val str = response.body()!!.string()
                        val jsonData = JSONObject(str)//получили json массив
                        val hits = jsonData.get("hits") as JSONArray
                        (activity as MainActivity).runOnUiThread {
                           val simpleGrid = view?.findViewById(R.id.simpleGridView) as GridView
                           val customAdapter = CategoryAdapter(view!!.context, hits)
                           simpleGrid.adapter = customAdapter
                        }

                        }

                    }
                }
        })
    }


}