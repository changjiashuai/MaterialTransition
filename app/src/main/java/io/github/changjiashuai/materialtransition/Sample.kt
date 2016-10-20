package io.github.changjiashuai.materialtransition

import android.databinding.BindingAdapter
import android.support.annotation.ColorRes
import android.support.v4.graphics.drawable.DrawableCompat
import android.widget.ImageView
import java.io.Serializable

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2016/10/19 11:44.
 */
class Sample(@ColorRes color: Int, name: String) : Serializable {
//    val color = color
//        get
//    val name = name
//        get
//
//    @BindingAdapter("bind:colorTint")
//    fun setColorTint(view: ImageView, @ColorRes color: Int): Unit {
//        DrawableCompat.setTint(view.drawable, color)
//    }

    var color: Int = 0
        internal set
    var name: String
        internal set

    init {
        this.color = color
        this.name = name
    }

    companion object {

        @JvmStatic
        @BindingAdapter("bind:colorTint")
        fun setColorTint(view: ImageView, @ColorRes color: Int) {
            DrawableCompat.setTint(view.drawable, color)
            //view.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
    }
}