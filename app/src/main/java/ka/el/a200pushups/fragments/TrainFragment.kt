package ka.el.a200pushups.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import ka.el.a200pushups.R
import ka.el.a200pushups.databinding.FragmentTrainBinding
import ka.el.a200pushups.viewModel.PushUpsViewModel
import ka.el.a200pushups.viewModel.TrainViewModel

class TrainFragment : Fragment() {
    private var binding: FragmentTrainBinding? = null

    private val pushUpsViewModel: PushUpsViewModel by activityViewModels()
    private val trainViewModel: TrainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_train, container, false)

        trainViewModel.setCurrentTrainWeek(
            pushUpsViewModel.currentTrainWeek.value!!,
            pushUpsViewModel.currentDay.value!!
        )

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = this@TrainFragment
            trainVM = trainViewModel
        }

        binding?.valueCounter?.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    trainViewModel.setCounterValue(s.toString().toInt())
                }

                override fun afterTextChanged(s: Editable?) {}

            }
        )
    }
}