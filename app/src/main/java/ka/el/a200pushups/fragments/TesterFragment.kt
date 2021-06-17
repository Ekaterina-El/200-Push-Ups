package ka.el.a200pushups.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ka.el.a200pushups.R
import ka.el.a200pushups.databinding.FragmentTesterBinding
import ka.el.a200pushups.viewModel.TesterViewModel

class TesterFragment : Fragment() {

    private lateinit var binding: FragmentTesterBinding
    private val viewModel: TesterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tester, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            testerViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
            testerFragment = this@TesterFragment
        }

        binding.valueCounter.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString() == "") {
                        viewModel.setCounterValue(0)
                    } else {
                        viewModel.setCounterValue(s.toString().toInt())
                    }
                }

                override fun afterTextChanged(s: Editable?) {}

            }
        )
    }


    fun ready() {
        Log.d("TAGGGG", "READYYY!!!")
        // Save max push ups
        saveToSharedPreferencesMaxPushUps(viewModel.counterValue.value!!)
        viewModel.setCounterValue(0)

        findNavController().navigate(R.id.action_testerFragment_to_trainWeekFragment) // Go train week
    }

    private fun saveToSharedPreferencesMaxPushUps(maxPushUps: Int) {
        context
            ?.getSharedPreferences(
                getString(R.string.app_shared_preferences_file_name),
                Context.MODE_PRIVATE
            )
            ?.edit()
            ?.putInt(getString(R.string.max_push_ups_setting), maxPushUps)
            ?.putInt(getString(R.string.sp_current_day), 1)
            ?.apply()
    }
}