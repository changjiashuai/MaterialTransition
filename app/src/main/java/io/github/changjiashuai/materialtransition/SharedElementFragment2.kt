package io.github.changjiashuai.materialtransition

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2016/10/20 15:44.
 */
class SharedElementFragment2:Fragment() {
    companion object {
        val EXTRA_SAMPLE = "sample"

        fun newInstance(sample: Sample): SharedElementFragment2 {
            val args = Bundle()
            args.putSerializable(EXTRA_SAMPLE, sample)
            val fragment = SharedElementFragment2()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.activity_sharedelement_fragment2, container, false)
        val sample:Sample = arguments.getSerializable(SharedElementFragment1.EXTRA_SAMPLE) as Sample
        val squareBlue: ImageView = view?.findViewById(R.id.square_blue) as ImageView
        DrawableCompat.setTint(squareBlue.drawable, sample.color)
        return view
    }
}