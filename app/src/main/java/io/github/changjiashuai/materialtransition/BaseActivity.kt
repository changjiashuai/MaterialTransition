package io.github.changjiashuai.materialtransition

import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    companion object{
        val EXTRA_SAMPLE = "sample"
        val EXTRA_TYPE = "type"
        val TYPE_PROGRAMMATICALLY = 0
        val TYPE_XML = 1
    }

    protected fun setupToolbar(){
        val toolbar: Toolbar? = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    protected fun transitionTo(intent: Intent){
        val pairs = TransitionHelper.createSafeTransitionParticipants(this, true)
        val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *pairs)
        startActivity(intent, transitionActivityOptions.toBundle())
    }
}