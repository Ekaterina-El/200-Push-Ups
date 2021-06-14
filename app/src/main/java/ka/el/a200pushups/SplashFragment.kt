package ka.el.a200pushups

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SplashFragment : Fragment() {
    private val nullPushUp = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tread = Thread(Runnable {
            Thread.sleep(getString(R.string.loading_time).toLong())
            activity?.runOnUiThread(
                Runnable { goNextScreen() }
            )
        })
        tread.start()

        getMaxPushUps()
    }

    private fun getMaxPushUps(): Int {
        val sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.app_shared_preferences_file_name),
            Context.MODE_PRIVATE
        )

        return sharedPreferences!!.getInt("MAX_PUSH_UPS_SETTING", nullPushUp)
    }

    private fun goNextScreen() {
        val maxPushUps = getMaxPushUps()

        if (maxPushUps == nullPushUp) {
            findNavController().navigate(R.id.action_splashFragment_to_welcomeScreen)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_trainWeekFragment)

        }
    }
}