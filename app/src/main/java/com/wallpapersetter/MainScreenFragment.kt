package com.wallpapersetter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class MainScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_main_screen, container, false)
        val BUTTON_IDS = intArrayOf(
            R.id.imageButton1,
            R.id.imageButton2,
            R.id.imageButton3,
            R.id.imageButton4,
            R.id.imageButton5,
            R.id.imageButton6,
        )
        val buttonList: ArrayList<ImageButton> = ArrayList(6)
        for (id in BUTTON_IDS.indices) {
            val button = view.findViewById<View>(BUTTON_IDS[id]) as ImageButton
            button.setOnClickListener(){
                when ( it.id) {
                    R.id.imageButton1 -> {}
                    R.id.imageButton2 -> {}
                    R.id.imageButton3 -> {}
                    R.id.imageButton4 -> {}
                    R.id.imageButton5 -> {}
                    R.id.imageButton6 -> {}
                }


            }
            buttonList.add(button)
            updateBin(id, BUTTON_IDS)
        }
        return view
}

    private fun updateBin(id : Int, array: IntArray) {
        val client = OkHttpClient()
        val  key = "33106230-b104905cd7ff74ed17e2229af"
        val categoryArray = resources.getStringArray(R.array.Categories)
        val category = categoryArray[id]
        val requestUrl ="https://pixabay.com/api/?key=$key&category=$category&safesearch=true&per_page=3"
        var imageUrl: String
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
                        val imageObject = hits[0] as JSONObject//одно изображение
                        imageUrl = imageObject.get("webformatURL") as String//ссылка на изображение

                        if (imageUrl.isNotEmpty()) {
                            val imageButton = view?.findViewById<ImageButton>(array[id])
                            activity?.runOnUiThread {
                                Picasso.get().load(imageUrl).resize(300, 300).centerCrop()
                                    .into(imageButton)
                            }
                        }

                    }
                }
            }
        })
    }

}