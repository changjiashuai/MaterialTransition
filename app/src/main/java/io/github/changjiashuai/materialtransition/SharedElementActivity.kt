package io.github.changjiashuai.materialtransition

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Slide
import android.view.Gravity
import io.github.changjiashuai.materialtransition.databinding.ActivitySharedElementBinding

class SharedElementActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sample: Sample = intent.extras.getSerializable(EXTRA_SAMPLE) as Sample
        bindData(sample)
        setupWindowAnimations()
        setupLayout(sample)
        setupToolbar()
    }

    private fun bindData(sample: Sample) {
        val binding = DataBindingUtil.setContentView<ActivitySharedElementBinding>(this, R.layout.activity_shared_element)
        binding.sharedSample = sample
    }

    private fun setupWindowAnimations() {
        window.enterTransition.duration = 500
    }

    private fun setupLayout(sample: Sample) {
        val slideTransition = Slide(Gravity.LEFT)
        slideTransition.duration = 500
        val shareElementFragment1 = SharedElementFragment1.newInstance(sample)
        shareElementFragment1.reenterTransition = slideTransition
        shareElementFragment1.exitTransition = slideTransition
        shareElementFragment1.sharedElementEnterTransition = ChangeBounds()
        supportFragmentManager.beginTransaction()
                .replace(R.id.sample2_content, shareElementFragment1).commit()
    }
}
