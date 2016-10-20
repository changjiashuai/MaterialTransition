package io.github.changjiashuai.materialtransition

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.Visibility
import io.github.changjiashuai.materialtransition.databinding.ActivityTransition1Binding

class TransitionActivity1 : BaseActivity() {

    private var sample: Sample? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
        setupWindowAnimations()
        setupLayout()
        setupToolbar()
    }

    fun bindData() {
        val binding = DataBindingUtil.setContentView<ActivityTransition1Binding>(this, R.layout.activity_transition1)
        sample = intent.extras.getSerializable(EXTRA_SAMPLE) as Sample
        binding.transitionSample = sample
    }

    fun setupWindowAnimations() {
        val enterTransition = buildEnterTransition()
        window.enterTransition = enterTransition
    }

    fun buildEnterTransition(): Visibility {
        val enterTransition = Fade()
        enterTransition.duration = 500
        return enterTransition
    }

    fun setupLayout() {
        findViewById(R.id.sample1_button1).setOnClickListener {
            val intent = Intent(this, TransitionActivity2::class.java)
            intent.putExtra(EXTRA_SAMPLE, sample)
            intent.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY)
            transitionTo(intent)
        }
        findViewById(R.id.sample1_button2).setOnClickListener {
            val intent = Intent(this, TransitionActivity2::class.java)
            intent.putExtra(EXTRA_SAMPLE, sample)
            intent.putExtra(EXTRA_TYPE, TYPE_XML)
            transitionTo(intent)
        }
        findViewById(R.id.sample1_button3).setOnClickListener {
            val intent = Intent(this, TransitionActivity3::class.java)
            intent.putExtra(EXTRA_SAMPLE, sample)
            intent.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY)
            transitionTo(intent)
        }
        findViewById(R.id.sample1_button4).setOnClickListener {
            val intent = Intent(this, TransitionActivity3::class.java)
            intent.putExtra(EXTRA_SAMPLE, sample)
            intent.putExtra(EXTRA_TYPE, TYPE_XML)
            transitionTo(intent)
        }
        findViewById(R.id.sample1_button5).setOnClickListener {
            window.returnTransition = buildReturnTransition()
            finishAfterTransition()
        }
        findViewById(R.id.sample1_button6).setOnClickListener {
            finishAfterTransition()
        }
    }

    fun buildReturnTransition(): Visibility {
        val slide = Slide()
        slide.duration = 500
        return slide
    }
}
