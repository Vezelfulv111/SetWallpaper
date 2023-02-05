package com.wallpapersetter.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.wallpapersetter.MainActivity
import com.wallpapersetter.R
import com.wallpapersetter.adapters.ChooseAdapter
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.InetAddress
import java.util.concurrent.TimeUnit


//Фрагмент выбора категории картинок. 1й экран после splashScreen
class MainScreenFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_main_screen, container, false)
        val urlList: ArrayList<String> = ArrayList(6)
        val categoryList: ArrayList<String> = ArrayList(6)
        //for (id in 0 until 6) {
            var id =0
            loadCategories(id, urlList, categoryList)
        //}
        return view
    }

    //функция API запроса 1й картинки из каждой категории.
    //Функция вызывается n раз - где n - количество категорий.
    //С каждой категории выбирается 1 картинка и отображается в GridView
    private fun loadCategories(id: Int, UrlList: ArrayList<String>, CategoryList: ArrayList<String>) {
        var client = OkHttpClient()
        val key = resources.getString(R.string.requestKey)
        val categoryArray = resources.getStringArray(R.array.Categories)
        val category = categoryArray[id]
        val requestUrl ="http://pixabay.com/api/?key=$key&category=$category&safesearch=true&per_page=3"
        val request: Request = Request.Builder()
            .url(requestUrl)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                (activity as MainActivity).switchToErrorFragment("MainScreenFragment")//в случае ошибки переходим на экран ошибки
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        throw IOException("Unexpected code $response")
                    }
                    else {
                        val str = response.body!!.string()
                        val jsonData = JSONObject(str)//получили json массив
                        val hits = jsonData.get("hits") as JSONArray//выделим массив изображений
                        val imageObject = hits[0] as JSONObject//одно изображение
                        val imageUrl = imageObject.get("webformatURL") as String//ссылка на изображение
                        UrlList.add(imageUrl)//добавим в список ссылку на выбранную картинку
                        CategoryList.add(category)
                        (activity as MainActivity).runOnUiThread {//для работы с интерфейсом необходимо перейти на основной поток
                            val simpleGrid = view?.findViewById(R.id.mainGridView) as GridView
                            val customAdapter = ChooseAdapter(
                                view!!.context,
                                UrlList,
                                CategoryList,
                                activity as MainActivity
                            )
                            simpleGrid.adapter = customAdapter
                        }
                    }
                }
            }
        })
    }

}