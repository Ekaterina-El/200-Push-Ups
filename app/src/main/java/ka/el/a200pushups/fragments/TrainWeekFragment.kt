package ka.el.a200pushups.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ka.el.a200pushups.R
import ka.el.a200pushups.databinding.FragmentTrainWeekBinding

class TrainWeekFragment : Fragment() {
    private lateinit var binding: FragmentTrainWeekBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_train_week, container, false)
        return binding.root
    }
}