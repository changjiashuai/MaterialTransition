package io.github.changjiashuai.materialtransition

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import io.github.changjiashuai.materialtransition.databinding.ActivityAnimations1Binding

class AnimationsActivity1 : BaseActivity() {

    private var sample: Sample? = null
    private var sizeChanged: Boolean = false
    private var savedWidth: Int = 0
    private var positionChanged: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
        setupWindowAnimations()
        setupLayout()
        setupToolbar()
    }

    private fun bindData() {
        val binding = DataBindingUtil.setContentView<ActivityAnimations1Binding>(this, R.layout.activity_animations1)
        sample = intent.extras.getSerializable(EXTRA_SAMPLE) as Sample
        binding.animationsSample = sample
    }

    private fun setupWindowAnimations(){
        window.reenterTransition = Fade()
    }

    private fun setupLayout(){
        val square:ImageView = findViewById(R.id.square_green) as ImageView
        val viewRoot:ViewGroup = findViewById(R.id.sample3_root) as ViewGroup
        findViewById(R.id.sample3_button1).setOnClickListener {
            changeLayout(viewRoot, square)
        }
        findViewById(R.id.sample3_button2).setOnClickListener {
            changePosition(viewRoot, square)
        }
        findViewById(R.id.sample3_button3).setOnClickListener {
            val intent = Intent(this, AnimationsActivity2::class.java)
            intent.putExtra(EXTRA_SAMPLE, sample)
            transitionTo(intent)
        }
    }

    private fun changeLayout(viewRoot:ViewGroup, square:ImageView){
        TransitionManager.beginDelayedTransition(viewRoot)
        val params:ViewGroup.LayoutParams = square.layoutParams
        if (sizeChanged){
            params.width = savedWidth
        }else{
            savedWidth = params.width
            params.width = 200
        }
        sizeChanged = !sizeChanged
        square.layoutParams = params
    }

    private fun changePosition(viewRoot:ViewGroup, square:ImageView){
        TransitionManager.beginDelayedTransition(viewRoot)
        val params:LinearLayout.LayoutParams = square.layoutParams as LinearLayout.LayoutParams
        if (positionChanged){
            params.gravity = Gravity.CENTER
        }else{
            params.gravity = Gravity.LEFT
        }
        positionChanged = !positionChanged
        square.layoutParams = params
    }
}
