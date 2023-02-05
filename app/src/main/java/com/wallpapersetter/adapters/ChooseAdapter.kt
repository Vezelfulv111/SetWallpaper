package com.wallpapersetter.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.wallpapersetter.MainActivity
import com.wallpapersetter.R


//адаптер для списка картинок, разделенных по категориям - 1й экран
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

    override fun getView(position: Int, View: View?, parent: ViewGroup?): View {
        var convertView: View? = View
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_choose_adapterr, parent, false)
        }

        val image: ImageView = convertView?.findViewById(R.id.CategoryImage) as ImageView
        val categoryName: TextView = convertView.findViewById(R.id.categoryName) as TextView
        Picasso.get()
            .load(items[position])
            .resize(300, 300).centerCrop()
            .placeholder(R.drawable.progress_animation)//прогресс бар отображается пока изображение не загрузится
            .error(R.drawable.imageerror)//изображение в случаее ошибки
            .into(image)
        categoryName.text = category[position]


        image.setOnClickListener {
            MainActivity.switchToCategoryFragment(category[position])//переход на экран с картинками из 1й категории
        }


        return convertView
    }


}