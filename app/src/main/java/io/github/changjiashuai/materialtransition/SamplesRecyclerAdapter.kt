package io.github.changjiashuai.materialtransition

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.changjiashuai.materialtransition.databinding.RowSampleBinding

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2016/10/19 11:56.
 */
class SamplesRecyclerAdapter(private val activity: Activity, private val samples: List<Sample>) : RecyclerView.Adapter<SamplesRecyclerAdapter.SamplesViewHolder>() {

    override fun onBindViewHolder(holder: SamplesViewHolder?, position: Int) {
        val sample = samples[position]
//        holder?.binding?.setVariable(BR.sample, sample)
        holder?.binding?.sample = sample
        holder?.binding?.sampleLayout?.setOnClickListener {
            when (position) {
                0 -> transitionToActivity(TransitionActivity1::class.java, sample)
                1 -> transitionToActivity(SharedElementActivity::class.java, holder, sample)
                2 -> ""
                3 -> ""
                else -> ""
            }
        }
    }

    private fun transitionToActivity(target: Class<*>, sample: Sample): Unit {
        val pairs = TransitionHelper.createSafeTransitionParticipants(activity, true)
        startActivity(target, pairs, sample)
    }

    private fun transitionToActivity(target: Class<*>, viewHolder: SamplesViewHolder, sample: Sample){
        val pairs = TransitionHelper.createSafeTransitionParticipants(activity, false, Pair(viewHolder.binding.sampleIcon, activity.getString(R.string.square_blue_name)), Pair(viewHolder.binding.sampleName, activity.getString(R.string.sample_blue_title)))
        startActivity(target, pairs, sample)
    }

    private fun transitionToActivity(target: Class<*>, viewHolder: SamplesViewHolder, sample: Sample, transitionName: Int){
        val pairs = TransitionHelper.createSafeTransitionParticipants(activity, false, Pair(viewHolder.binding.sampleIcon, activity.getString(transitionName)))
        startActivity(target, pairs, sample)
    }

    private fun startActivity(target: Class<*>, pairs: Array<Pair<View, String>>, sample: Sample) {
        val intent = Intent(activity, target)
        val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *pairs)
        intent.putExtra("sample", sample)
        activity.startActivity(intent, transitionActivityOptions.toBundle())
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SamplesViewHolder {
        val binding = RowSampleBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return SamplesViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return samples.size
    }


    class SamplesViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        internal val binding: RowSampleBinding

        init {
            binding = DataBindingUtil.bind(itemView)
        }
    }
}