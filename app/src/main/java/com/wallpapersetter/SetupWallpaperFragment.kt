package com.wallpapersetter

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso


//Фрагмент, отвечающий за установку обоев на рабочий экран
class SetupWallpaperFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_setup_wallaper, container, false)

        //получим ссылку на изображение, полученное при переходе с предыдущего фрагмента
        var imageURL = ""
        imageURL = arguments?.getString("ImageUrl", "")!!//получили выбранную категорию при запуске фрагмента

        val image: ImageView = view.findViewById(R.id.chosenImage) as ImageView
        Picasso.get().load(imageURL).into(image)//загрузим полученное изображение



        val setupWallpaper: Button = view.findViewById(R.id.setupWallpaper) as Button
        setupWallpaper.setOnClickListener(){
            Picasso.get().load(imageURL).into(object : com.squareup.picasso.Target {
                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    val wallpaperManager = WallpaperManager.getInstance(context)
                    wallpaperManager.setBitmap(bitmap)
                }
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
            })



        }



        return view
    }


}