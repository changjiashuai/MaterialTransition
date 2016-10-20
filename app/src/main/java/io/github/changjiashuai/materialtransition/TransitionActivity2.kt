package io.github.changjiashuai.materialtransition

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.transition.Explode
import android.transition.Transition
import android.transition.TransitionInflater
import io.github.changjiashuai.materialtransition.databinding.ActivityTransition2Binding

class TransitionActivity2 : BaseActivity() {
    private var type: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
        setupWindowAnimations()
        setupLayout()
        setupToolbar()
    }

    fun bindData() {
        val binding = DataBindingUtil.setContentView<ActivityTransition2Binding>(this, R.layout.activity_transition2)
        val sample:Sample = intent.extras.getSerializable(EXTRA_SAMPLE) as Sample
        type = intent.extras.getInt(EXTRA_TYPE)
        binding.transition2Sample = sample
    }

    fun setupWindowAnimations() {
        var transition: Transition
        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition()
        } else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.explode)
        }
        window.enterTransition = transition
    }

    fun buildEnterTransition(): Transition {
        val enterTransition = Explode()
        enterTransition.duration = 500
        return enterTransition
    }

    fun setupLayout(){
        findViewById(R.id.exit_button).setOnClickListener {
            finishAfterTransition()
        }
    }
}
