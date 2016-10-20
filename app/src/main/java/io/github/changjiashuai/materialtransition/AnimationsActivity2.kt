package io.github.changjiashuai.materialtransition

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.transition.*
import android.view.View
import android.view.ViewGroup
import io.github.changjiashuai.materialtransition.databinding.ActivityAnimations2Binding
import java.util.*

class AnimationsActivity2 : BaseActivity() {

    private val DELAY = 100
    private var sceneRoot: ViewGroup? = null
    private var sample: Sample? = null
    private var scene0: Scene? = null
    private var scene1: Scene? = null
    private var scene2: Scene? = null
    private var scene3: Scene? = null
    private var scene4: Scene? = null
    private val viewsToAnimate = ArrayList<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
        setupWindowAnimations()
        setupLayout()
        setupToolbar()
    }

    private fun bindData() {
        val binding = DataBindingUtil.setContentView<ActivityAnimations2Binding>(this, R.layout.activity_animations2)
        sample = intent.extras.getSerializable(EXTRA_SAMPLE) as Sample
        binding.setAnimationsSample(sample)
    }

    private fun setupWindowAnimations() {
        window.enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom)
        window.enterTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition?) {

            }

            override fun onTransitionCancel(transition: Transition?) {


            }

            override fun onTransitionPause(transition: Transition?) {


            }

            override fun onTransitionResume(transition: Transition?) {


            }

            override fun onTransitionEnd(transition: Transition?) {
                window.enterTransition.removeListener(this)
                TransitionManager.go(scene0)
            }
        })
    }

    private fun setupLayout() {
        val activityRoot: ViewGroup = findViewById(R.id.buttons_group) as ViewGroup
        sceneRoot = findViewById(R.id.scene_root) as ViewGroup?
        scene0 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene0, this)
        scene0?.setEnterAction {
            val title = scene0?.sceneRoot?.findViewById(R.id.scene0_title)
            for (i in viewsToAnimate.indices) {
                val child = viewsToAnimate[i]
                child.animate().setStartDelay((i * DELAY).toLong()).scaleX(1f).scaleY(1f)
            }
        }
        scene0?.setExitAction {
            TransitionManager.beginDelayedTransition(activityRoot)
            val title = scene0?.sceneRoot?.findViewById(R.id.scene0_title)
            title?.scaleX = 0f
            title?.scaleY = 0f
        }

        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene1, this)
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene2, this)
        scene3 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene3, this)
        scene4 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene4, this)

        val button1 = findViewById(R.id.sample3_button1)
        button1.setOnClickListener { TransitionManager.go(scene1, ChangeBounds()) }
        val button2 = findViewById(R.id.sample3_button2)
        button2.setOnClickListener { TransitionManager.go(scene2, TransitionInflater.from(this@AnimationsActivity2).inflateTransition(R.transition.slide_and_changebounds)) }

        val button3 = findViewById(R.id.sample3_button3)
        button3.setOnClickListener { TransitionManager.go(scene3, TransitionInflater.from(this@AnimationsActivity2).inflateTransition(R.transition.slide_and_changebounds_sequential)) }

        val button4 = findViewById(R.id.sample3_button4)
        button4.setOnClickListener { TransitionManager.go(scene4, TransitionInflater.from(this@AnimationsActivity2).inflateTransition(R.transition.slide_and_changebounds_sequential_with_interpolators)) }

        viewsToAnimate.add(button1)
        viewsToAnimate.add(button2)
        viewsToAnimate.add(button3)
        viewsToAnimate.add(button4)
    }
}
