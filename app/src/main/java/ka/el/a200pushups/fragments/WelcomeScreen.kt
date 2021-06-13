package ka.el.a200pushups.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import ka.el.a200pushups.R
import ka.el.a200pushups.TesterActivity
import ka.el.a200pushups.databinding.FragmentWelcomeScreenBinding
import ka.el.a200pushups.viewModel.PushUpsViewModel


class WelcomeScreen : Fragment() {
    private lateinit var binding: FragmentWelcomeScreenBinding
    private val viewModel: PushUpsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome_screen, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            welcomeScreen = this@WelcomeScreen
        }
    }

    fun startTest() {
        Log.d("TAG", "START TEST")
        startActivityForResult(Intent(requireContext(), TesterActivity::class.java), REQUEST_CODE_TESTING_ACTIVITY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_TESTING_ACTIVITY) {
            val maxPushUps = data?.extras?.getInt(TESTING_PUSH_UPS)
            if (maxPushUps != null) {
                Log.d("TAG", "END TEST | ${maxPushUps}")

                viewModel.setCurrentMaxPushUps(maxPushUps)
            }
        }
    }

    companion object {
        const val REQUEST_CODE_TESTING_ACTIVITY = 12
        const val TESTING_PUSH_UPS = "TESTING_PUSH_UPS"
    }
}