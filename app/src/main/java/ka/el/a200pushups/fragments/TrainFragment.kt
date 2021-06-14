package ka.el.a200pushups.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ka.el.a200pushups.R
import ka.el.a200pushups.databinding.FragmentTrainWeekBinding
import ka.el.a200pushups.viewModel.PushUpsViewModel

class TrainFragment : Fragment() {
    private var binding: FragmentTrainWeekBinding? = null
    private val pushUpsViewModel: PushUpsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_train, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = this@TrainFragment
            viewModel = pushUpsViewModel
        }
    }
}