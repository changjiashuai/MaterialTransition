package io.github.changjiashuai.materialtransition

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.transition.Slide
import android.view.Gravity

class MainActivity : AppCompatActivity() {

    private lateinit var samples:List<Sample>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupWindowAnimations()
        setupSamples()
        setupToolbar()
        setupLayout()
    }

    fun setupWindowAnimations(): Unit {
        val slideTransition = Slide()
        slideTransition.slideEdge = Gravity.LEFT
        slideTransition.duration = 500
        window.reenterTransition = slideTransition
        window.exitTransition = slideTransition
    }

    fun setupSamples(): Unit {
        samples = listOf(Sample(ContextCompat.getColor(this, R.color.sample_red), "Transitions"),
                Sample(ContextCompat.getColor(this, R.color.sample_blue), "Shared Elements"),
                Sample(ContextCompat.getColor(this, R.color.sample_green), "View Animations"),
                Sample(ContextCompat.getColor(this, R.color.sample_yellow), "Circular Reveal Animation"))
    }

    fun setupToolbar(): Unit {
        val toolbar:Toolbar? = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun setupLayout(): Unit {
        val recyclerView:RecyclerView = findViewById(R.id.sample_list) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = SamplesRecyclerAdapter(this, samples)
        recyclerView.adapter = adapter
    }
}
