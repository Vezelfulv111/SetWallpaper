package com.wallpapersetter.fragments

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.wallpapersetter.R


//Фрагмент, отвечающий за установку обоев на рабочий экран
class SetupWallpaperFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_setup_wallaper, container, false)
        //получим ссылку на изображение, полученное при переходе с предыдущего фрагмента
        val imageURL = arguments?.getString("ImageUrl", "")!!//получили выбранную категорию при запуске фрагмента
        val image: ImageView = view.findViewById(R.id.icon) as ImageView
        Picasso.get()
            .load(imageURL)
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.imageerror)
            .into(image)//загрузим изображение с сайта в imageview

        val setupWallpaper: Button = view.findViewById(R.id.reloadButton) as Button
        setupWallpaper.setOnClickListener(){
            //для установки изображения на обои необходимо получить bitmap изображения
            Picasso.get().load(imageURL).into(object : com.squareup.picasso.Target {
                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    val toast = Toast.makeText(context, "Ошибка загрузки!", Toast.LENGTH_SHORT)
                    toast.show()
                }
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    val wallpaperManager = WallpaperManager.getInstance(context)
                    wallpaperManager.setBitmap(bitmap)
                    val toast = Toast.makeText(context, "Обои установлены!", Toast.LENGTH_SHORT)
                    toast.show()
                }
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
            })
        }
        return view
    }
}