package ka.el.a200pushups.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ka.el.a200pushups.R
import ka.el.a200pushups.databinding.FragmentWelcomeScreenBinding
import ka.el.a200pushups.viewModel.PushUpsViewModel


class WelcomeScreen : Fragment() {
    private var binding: FragmentWelcomeScreenBinding? = null
    private val viewModel: PushUpsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_welcome_screen, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            welcomeScreen = this@WelcomeScreen
        }
    }

    fun startTest() {
        findNavController().navigate(R.id.action_welcomeScreen_to_testerFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}