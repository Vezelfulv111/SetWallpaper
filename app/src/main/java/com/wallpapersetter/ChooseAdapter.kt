package com.wallpapersetter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class  ChooseAdapter(
    var context: Context,
    var items: ArrayList<String>,
    var category: ArrayList<String>,
    var MainActivity: MainActivity
) : BaseAdapter() {


    override fun getCount(): Int {
        return items.size
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_choose_adapterr, parent, false)
        }

        val image: ImageView = convertView?.findViewById(R.id.CategoryImage) as ImageView
        val categoryName: TextView = convertView?.findViewById(R.id.categoryName) as TextView
        Picasso.get().load(items[position]).into(image)
        categoryName.text = category[position]


        image.setOnClickListener() {
            MainActivity.switchToCategoryFragment(category[position])
        }


        return convertView
    }


}