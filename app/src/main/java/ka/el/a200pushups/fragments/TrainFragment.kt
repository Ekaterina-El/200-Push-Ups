package ka.el.a200pushups.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ka.el.a200pushups.R
import ka.el.a200pushups.databinding.FragmentTrainBinding
import ka.el.a200pushups.viewModel.PushUpsViewModel
import ka.el.a200pushups.viewModel.TrainViewModel

class TrainFragment : Fragment() {
    private var binding: FragmentTrainBinding? = null

    private val pushUpsViewModel: PushUpsViewModel by activityViewModels()
    private val trainViewModel: TrainViewModel by viewModels()

    private lateinit var timer: CountDownTimer

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
            trainFragment = this@TrainFragment
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

    fun completeCurrentSet() {
        if (trainViewModel.currentSet.value == 4) {
            MaterialAlertDialogBuilder(requireContext()).setTitle("End!").show()
        // TODO("Show alert dialog with custom layout and going to TranWeekFragment | Save new current day value in SPref ")
        } else {
            if (trainViewModel.isStartedTimer.value == true) {
                timer.cancel()
                trainViewModel.skipRes()
            } else {
                val timerValue = trainViewModel.thisTrainBreak

                timer = object : CountDownTimer((timerValue * 1000).toLong(), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        trainViewModel.downTimer()
                    }
                    override fun onFinish() {
                        trainViewModel.skipRes()
                    }
                }

                trainViewModel.onTimer()
                timer.start()
            }
        }
    }
}