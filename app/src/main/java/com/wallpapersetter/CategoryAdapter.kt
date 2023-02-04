package com.wallpapersetter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject


class  CategoryAdapter(var context: Context, var items: JSONArray) : BaseAdapter() {


    override fun getCount(): Int {
        return items.length()
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, View: View?, parent: ViewGroup?): View? {
        var convertView: View? = View
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_category_adapter, parent, false)
        }

        val image: ImageView = convertView?.findViewById(R.id.image) as ImageView
        val imageObject = items[position] as JSONObject//одно изображение
        val url = imageObject.get("webformatURL") as String//ссылка на изображение
        Picasso.get().load(url).resize(300, 300).centerCrop().into(image)


        return convertView
    }


}