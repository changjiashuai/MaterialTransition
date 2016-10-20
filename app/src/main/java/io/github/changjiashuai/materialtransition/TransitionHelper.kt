package io.github.changjiashuai.materialtransition

import android.app.Activity
import android.support.annotation.Nullable
import android.support.v4.util.Pair
import android.view.View

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2016/10/19 16:08.
 */
object TransitionHelper {
    fun createSafeTransitionParticipants(@Nullable activity: Activity, includeStatusBar: Boolean, @Nullable vararg otherParticipants: Pair<View, String>): Array<Pair<View, String>> {
        val decor = activity.window.decorView
        var statusBar: View? = null
        if (includeStatusBar) {
            statusBar = decor.findViewById(android.R.id.statusBarBackground)
        }
        val navBar = decor.findViewById(android.R.id.navigationBarBackground)
        val participants = mutableListOf<Pair<View, String>>()
        addNonNullViewToTransitionParticipants(statusBar, participants)
        addNonNullViewToTransitionParticipants(navBar, participants)
        participants.addAll(otherParticipants)
        return participants.toTypedArray()
    }

    private fun addNonNullViewToTransitionParticipants(view: View?, participants: MutableList<Pair<View, String>>): Unit {
        if (view == null) {
            return
        }
        participants.add(Pair(view, view.transitionName))
    }
}