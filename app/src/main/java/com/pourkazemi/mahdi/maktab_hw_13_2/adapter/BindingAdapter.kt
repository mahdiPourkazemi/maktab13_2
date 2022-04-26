package com.pourkazemi.mahdi.maktab_hw_13_2.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pourkazemi.mahdi.maktab_hw_13_2.R
import com.pourkazemi.mahdi.maktab_hw_13_2.data.model.Photo

@BindingAdapter("list")
fun RecyclerView.bindView(data: List<Photo>?){
    val adapter = this.adapter as RecycleAdapter
    adapter.submitList(data)
}
@BindingAdapter("imageUrl")
fun ImageView.BindImage( imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().build()
        Glide.with(context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(this)
    }
}

/*
<data>
<variable
name="viewModel"
type="com.pourkazemi.mahdi.maktab_hw_13_2.MyViewModel" />
</data>
*/