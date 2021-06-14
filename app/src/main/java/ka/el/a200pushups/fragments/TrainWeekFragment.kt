package ka.el.a200pushups.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ka.el.a200pushups.R
import ka.el.a200pushups.adapter.DaysOfTrainWeekAdapter
import ka.el.a200pushups.databinding.FragmentTrainWeekBinding
import ka.el.a200pushups.viewModel.PushUpsViewModel

class TrainWeekFragment : Fragment() {
    private var binding: FragmentTrainWeekBinding? = null
    private val pushUpsViewModel: PushUpsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pushUpsViewModel.setCurrentMaxPushUps(getMaxPushUps())
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_train_week, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = pushUpsViewModel
            lifecycleOwner = lifecycleOwner
        }

        var trainWeek = pushUpsViewModel.currentTrainWeek.value

        var adapter = DaysOfTrainWeekAdapter(
            requireContext(),
            trainWeek!!.breaks,
            trainWeek.days,
            pushUpsViewModel.currentDay.value!!
        )

        binding!!.recyclerView.adapter = adapter
    }

    private fun getMaxPushUps(): Int {
        val sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.app_shared_preferences_file_name),
            Context.MODE_PRIVATE
        )

        return sharedPreferences!!.getInt("MAX_PUSH_UPS_SETTING", 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}