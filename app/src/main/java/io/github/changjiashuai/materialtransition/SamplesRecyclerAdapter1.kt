//package io.github.changjiashuai.materialtransition
//
//import android.app.Activity
//import android.content.Intent
//import android.databinding.DataBindingUtil
//import android.support.v4.app.ActivityOptionsCompat
//import android.support.v4.util.Pair
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//
//import com.lgvalle.material_animations.databinding.RowSampleBinding
//
//class SamplesRecyclerAdapter(private val activity: Activity, private val samples: List<Sample>) : RecyclerView.Adapter<SamplesRecyclerAdapter.SamplesViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, position: Int): SamplesViewHolder {
//        val binding = RowSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return SamplesViewHolder(binding.getRoot())
//    }
//
//    override fun onBindViewHolder(viewHolder: SamplesViewHolder, position: Int) {
//        val sample = samples[position]
//        viewHolder.binding.setSample(sample)
//        viewHolder.binding.sampleLayout.setOnClickListener(View.OnClickListener {
//            when (position) {
//                0 -> transitionToActivity(TransitionActivity1::class.java, sample)
//                1 -> transitionToActivity(SharedElementActivity::class.java, viewHolder, sample)
//                2 -> transitionToActivity(AnimationsActivity1::class.java, sample)
//                3 -> transitionToActivity(RevealActivity::class.java, viewHolder, sample, R.string.transition_reveal1)
//            }
//        })
//    }
//
//    private fun transitionToActivity(target: Class<*>, sample: Sample) {
//        val pairs = TransitionHelper.createSafeTransitionParticipants(activity, true)
//        startActivity(target, pairs, sample)
//    }
//
//
//    private fun transitionToActivity(target: Class<*>, viewHolder: SamplesViewHolder, sample: Sample, transitionName: Int) {
//        val pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
//                Pair<Any, String>(viewHolder.binding.sampleIcon, activity.getString(transitionName)))
//        startActivity(target, pairs, sample)
//    }
//
//    private fun transitionToActivity(target: Class<*>, viewHolder: SamplesViewHolder, sample: Sample) {
//        val pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
//                Pair<Any, String>(viewHolder.binding.sampleIcon, activity.getString(R.string.square_blue_name)),
//                Pair<Any, String>(viewHolder.binding.sampleName, activity.getString(R.string.sample_blue_title)))
//        startActivity(target, pairs, sample)
//    }
//
//    private fun startActivity(target: Class<*>, pairs: Array<Pair<View, String>>, sample: Sample) {
//        val i = Intent(activity, target)
//        val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *pairs)
//        i.putExtra("sample", sample)
//        activity.startActivity(i, transitionActivityOptions.toBundle())
//    }
//
//    override fun getItemCount(): Int {
//        return samples.size
//    }
//
//
//    inner class SamplesViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
//        internal var binding: RowSampleBinding
//
//        init {
//            binding = DataBindingUtil.bind<RowSampleBinding>(rootView)
//
//        }
//    }
//}
