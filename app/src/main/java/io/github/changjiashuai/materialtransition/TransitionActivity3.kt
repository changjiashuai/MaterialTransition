package io.github.changjiashuai.materialtransition

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.Visibility
import android.view.Gravity
import io.github.changjiashuai.materialtransition.databinding.ActivityTransition3Binding

class TransitionActivity3 : BaseActivity() {

    private var type: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
        setupWindowAnimations()
        setupLayout()
        setupToolbar()
    }

    private fun bindData() {
        val binding = DataBindingUtil.setContentView<ActivityTransition3Binding>(this, R.layout.activity_transition3)
        val sample: Sample = intent.extras.getSerializable(EXTRA_SAMPLE) as Sample
        type = intent.extras.getInt(EXTRA_TYPE)
        binding.transition3Sample = sample
    }

    private fun setupWindowAnimations() {
        val transition: Transition
        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition()
        } else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom)
        }
        window.enterTransition = transition
    }

    private fun buildEnterTransition(): Visibility {
        val slide = Slide()
        slide.duration = 500
        slide.slideEdge = Gravity.RIGHT
        return slide
    }

    private fun setupLayout() {
        findViewById(R.id.exit_button).setOnClickListener {
            finishAfterTransition()
        }
    }
}
