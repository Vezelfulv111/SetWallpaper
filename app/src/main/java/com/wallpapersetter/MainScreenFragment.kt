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

//Фрагмент выбора категории картинок
class MainScreenFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_main_screen, container, false)
        val urlList: ArrayList<String> = ArrayList(6)
        val categoryList: ArrayList<String> = ArrayList(6)
        for (id in 0 until 6) {
            updateBin(id, urlList, categoryList)
        }
        return view
    }

    private fun updateBin(id: Int, UrlList: ArrayList<String>, CategoryList: ArrayList<String>) {
        val client = OkHttpClient()
        val  key = "33106230-b104905cd7ff74ed17e2229af"
        val categoryArray = resources.getStringArray(R.array.Categories)
        val category = categoryArray[id]
        val requestUrl ="https://pixabay.com/api/?key=$key&category=$category&safesearch=true&per_page=3"
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
                    }
                    else {
                        val str = response.body!!.string()
                        val jsonData = JSONObject(str)//получили json массив
                        val hits = jsonData.get("hits") as JSONArray
                        val imageObject = hits[0] as JSONObject//одно изображение
                        val imageUrl = imageObject.get("webformatURL") as String//ссылка на изображение
                        UrlList.add(imageUrl)
                        CategoryList.add(category)

                        (activity as MainActivity).runOnUiThread {
                            val simpleGrid = view?.findViewById(R.id.mainGridView) as GridView
                            val customAdapter = ChooseAdapter(
                                view!!.context,
                                UrlList,
                                CategoryList,
                                activity as MainActivity)
                            simpleGrid.adapter = customAdapter
                        }
                    }
                }
            }
        })
    }

}