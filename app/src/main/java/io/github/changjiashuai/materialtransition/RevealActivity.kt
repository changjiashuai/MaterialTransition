package io.github.changjiashuai.materialtransition

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.MotionEvent
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.widget.RelativeLayout
import android.widget.TextView
import io.github.changjiashuai.materialtransition.databinding.ActivityRevealBinding

class RevealActivity : BaseActivity(), View.OnTouchListener {

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (v.id == R.id.square_yellow) {
                revealYellow(event.rawX, event.rawY)
            }
        }
        return false
    }

    private val DELAY = 100
    private var bgViewGroup: RelativeLayout? = null
    private var toolbar: Toolbar? = null
    private var interpolator: Interpolator? = null
    private var body: TextView? = null
    private var btnGreen: View? = null
    private var btnRed: View? = null
    private var btnBlue: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
        setupLayout()
        setupWindowAnimations()
        setupToolbar()
    }

    private fun bindData() {
        val binding = DataBindingUtil.setContentView<ActivityRevealBinding>(this, R.layout.activity_reveal)
        val sample = intent.extras.getSerializable(EXTRA_SAMPLE) as Sample
        binding.setReveal1Sample(sample)
    }

    private fun setupLayout() {
        bgViewGroup = findViewById(R.id.reveal_root) as RelativeLayout
        toolbar = findViewById(R.id.toolbar) as Toolbar
        body = findViewById(R.id.sample_body) as TextView
        btnGreen = findViewById(R.id.square_green)
        btnGreen?.setOnClickListener { revealGreen() }
        btnRed = findViewById(R.id.square_red)
        btnRed?.setOnClickListener { revealRed() }
        btnBlue = findViewById(R.id.square_blue)
        btnBlue?.setOnClickListener { revealBlue() }
        findViewById(R.id.square_yellow).setOnTouchListener(this)
    }

    private fun setupWindowAnimations() {
        interpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in)
        setupEnterAnimations()
        setupExitAnimations()
    }

    private fun setupEnterAnimations() {
        val transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion)
        window.sharedElementEnterTransition = transition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {
            }

            override fun onTransitionEnd(transition: Transition) {
                // Removing listener here is very important because shared element transition is executed again backwards on exit. If we don't remove the listener this code will be triggered again.
                transition.removeListener(this)
                hideTarget()
                animateRevealShow(toolbar!!)
                animateButtonsIn()
            }

            override fun onTransitionCancel(transition: Transition) {
            }

            override fun onTransitionPause(transition: Transition) {
            }

            override fun onTransitionResume(transition: Transition) {
            }
        })
    }

    private fun setupExitAnimations() {
        val returnTransition = Fade()
        window.returnTransition = returnTransition
        returnTransition.duration = resources.getInteger(R.integer.anim_duration_medium).toLong()
        returnTransition.startDelay = resources.getInteger(R.integer.anim_duration_medium).toLong()
        returnTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {
                transition.removeListener(this)
                animateButtonsOut()
                animateRevealHide(bgViewGroup!!)
            }

            override fun onTransitionEnd(transition: Transition) {
            }

            override fun onTransitionCancel(transition: Transition) {
            }

            override fun onTransitionPause(transition: Transition) {
            }

            override fun onTransitionResume(transition: Transition) {
            }
        })
    }


    private fun hideTarget() {
        findViewById(R.id.shared_target).visibility = View.GONE
    }

    /**
     * Toolbar Reveal Animation
     */
    private fun animateRevealShow(viewRoot: Toolbar) {
        val cx = (viewRoot.left + viewRoot.right) / 2
        val cy = (viewRoot.top + viewRoot.bottom) / 2
        val finalRadius = Math.max(viewRoot.width, viewRoot.height)

        val anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, 0f, finalRadius.toFloat())
        viewRoot.visibility = View.VISIBLE
        anim.duration = resources.getInteger(R.integer.anim_duration_long).toLong()
        anim.interpolator = AccelerateInterpolator()
        anim.start()
    }

    private fun animateRevealHide(viewRoot: RelativeLayout) {
        val cx = (viewRoot.left + viewRoot.right) / 2
        val cy = (viewRoot.top + viewRoot.bottom) / 2
        val initialRadius = viewRoot.width

        val anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, initialRadius.toFloat(), 0f)
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                viewRoot.visibility = View.INVISIBLE
            }
        })
        anim.duration = resources.getInteger(R.integer.anim_duration_medium).toLong()
        anim.start()
    }

    private fun animateButtonsIn() {
        for (i in 0..bgViewGroup?.getChildCount()?.minus(1) as Int) {
            val child: View = bgViewGroup?.getChildAt(i)!!
            child.animate().setStartDelay((100 + i * DELAY).toLong()).setInterpolator(interpolator).alpha(1f)
                    .scaleX(1f).scaleY(1f)
        }
    }

    private fun animateButtonsOut() {
        for (i in 0..bgViewGroup?.getChildCount()?.minus(1) as Int) {
            val child = bgViewGroup?.getChildAt(i)
            child!!.animate().setStartDelay(i.toLong()).setInterpolator(interpolator).alpha(0f).scaleX(0f).scaleY(0f)
        }
    }

    private fun animateRevealColorFromCoordinates(viewRoot: RelativeLayout, @ColorRes color: Int, x: Int, y: Int): Animator {
        val finalRadius = Math.hypot(viewRoot.width.toDouble(), viewRoot.height.toDouble()).toFloat()

        val anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0f, finalRadius)
        viewRoot?.setBackgroundColor(ContextCompat.getColor(this, color))
        anim.duration = resources.getInteger(R.integer.anim_duration_long).toLong()
        anim.interpolator = AccelerateDecelerateInterpolator()
        anim.start()
        return anim
    }

    private fun animateRevealColor(viewRoot: RelativeLayout, @ColorRes color: Int) {
        val cx = (viewRoot.left + viewRoot.right) / 2
        val cy = (viewRoot.top + viewRoot.bottom) / 2
        animateRevealColorFromCoordinates(viewRoot, color, cx, cy)
    }

    private fun revealYellow(x: Float, y: Float) {
        animateRevealColorFromCoordinates(bgViewGroup!!, R.color.sample_yellow, x.toInt(), y.toInt())
        body?.setText(R.string.reveal_body1)
        body?.setTextColor(ContextCompat.getColor(this, R.color.theme_yellow_background))
    }

    private fun revealGreen() {
        animateRevealColor(bgViewGroup!!, R.color.sample_green)
        body?.setText(R.string.reveal_body2)
        body?.setTextColor(ContextCompat.getColor(this, R.color.theme_green_background))
    }

    private fun revealRed() {
        val originalParams = btnRed?.layoutParams
        val transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion)
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {
            }

            override fun onTransitionEnd(transition: Transition) {
                animateRevealColor(bgViewGroup!!, R.color.sample_red)
                body?.setText(R.string.reveal_body3)
                body?.setTextColor(ContextCompat.getColor(this@RevealActivity, R.color.theme_red_background))
                btnRed?.layoutParams = originalParams
            }

            override fun onTransitionCancel(transition: Transition) {
            }

            override fun onTransitionPause(transition: Transition) {

            }

            override fun onTransitionResume(transition: Transition) {

            }
        })
        TransitionManager.beginDelayedTransition(bgViewGroup, transition)
        val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT)
        btnRed?.layoutParams = layoutParams
    }

    private fun revealBlue() {
        animateButtonsOut()
        val anim = animateRevealColorFromCoordinates(bgViewGroup!!, R.color.sample_blue, bgViewGroup!!.getWidth() / 2, 0)
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                animateButtonsIn()
            }
        })
        body?.setText(R.string.reveal_body4)
        body?.setTextColor(ContextCompat.getColor(this, R.color.theme_blue_background))
    }
}
