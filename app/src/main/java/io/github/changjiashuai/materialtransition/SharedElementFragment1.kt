package io.github.changjiashuai.materialtransition

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.graphics.drawable.DrawableCompat
import android.transition.ChangeBounds
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2016/10/20 15:33.
 */
class SharedElementFragment1 : Fragment() {
    companion object {
        val EXTRA_SAMPLE = "sample"

        fun newInstance(sample: Sample): SharedElementFragment1 {
            val args = Bundle()
            args.putSerializable(EXTRA_SAMPLE, sample)
            val fragment = SharedElementFragment1()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.activity_sharedelement_fragment1, container, false)
        val sample: Sample = arguments.getSerializable(EXTRA_SAMPLE) as Sample
        val squareBlue: ImageView = view?.findViewById(R.id.square_blue) as ImageView
        DrawableCompat.setTint(squareBlue.drawable, sample.color)
        view?.findViewById(R.id.sample2_button1)?.setOnClickListener {
            addNextFragment(sample, squareBlue, false)
        }
        view?.findViewById(R.id.sample2_button2)?.setOnClickListener {
            addNextFragment(sample, squareBlue, true)
        }
        return view
    }

    private fun addNextFragment(sample: Sample, squareBlue: ImageView, overlap: Boolean) {
        val sharedElementFragment2 = SharedElementFragment2.newInstance(sample)
        val slideTransition = Slide(Gravity.RIGHT)
        slideTransition.duration = 300
        val changeBoundTransition = ChangeBounds()
        changeBoundTransition.duration = 300
        sharedElementFragment2.enterTransition = slideTransition
        sharedElementFragment2.allowEnterTransitionOverlap = overlap
        sharedElementFragment2.allowReturnTransitionOverlap = overlap
        sharedElementFragment2.sharedElementEnterTransition = changeBoundTransition
        fragmentManager.beginTransaction()
                .replace(R.id.sample2_content, sharedElementFragment2)
                .addToBackStack(null)
                .addSharedElement(squareBlue, getString(R.string.square_blue_name))
                .commit()
    }
}