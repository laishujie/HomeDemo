package com.lai.myapplication.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.lai.myapplication.R

/**
 *
 * @author  Lai
 *
 * @time 2019/9/16 0:14
 * @describe describe
 *
 */
object GlideUtils {
    fun loadImage(
        context: Context,
        url: String?,
        view: ImageView,
        width: Int = Target.SIZE_ORIGINAL,
        height: Int = Target.SIZE_ORIGINAL
    ) {
        val roundedCorners = RoundedCorners(DisplayUtils.dp2px(8f))
        val options = RequestOptions()
            .transform(CenterCrop(), roundedCorners)
            .placeholder(R.color.color_F6F5F7)
            .error(R.color.color_F6F5F7)
            .override(width, height)
        Glide.with(context).load(url).apply(options)
            .into(view)
    }
}