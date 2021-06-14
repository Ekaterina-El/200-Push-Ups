package ka.el.a200pushups

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ka.el.a200pushups.databinding.ActivityTesterBinding
import ka.el.a200pushups.fragments.WelcomeScreen
import ka.el.a200pushups.viewModel.TesterViewModel

class TesterActivity : AppCompatActivity() {
    private val viewModel: TesterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        val binder: ActivityTesterBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_tester)
        binder.lifecycleOwner = this
        binder.testerActivity = this
        binder.testerViewModel = viewModel

        binder.valueCounter.addTextChangedListener(
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
        val intent = Intent()
        intent.putExtra(WelcomeScreen.TESTING_PUSH_UPS, viewModel.counterValue.value)
        saveToSharedPreferencesMaxPushUps(viewModel.counterValue.value!!)
        viewModel.setCounterValue(0)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun saveToSharedPreferencesMaxPushUps(maxPushUps: Int) {
        val sharedPreferences = getSharedPreferences(
            getString(R.string.app_shared_preferences_file_name),
            Context.MODE_PRIVATE
        )

        val spEdit = sharedPreferences.edit()
        spEdit.putInt(getString(R.string.max_push_ups_setting), maxPushUps)
        spEdit.apply()

        Log.d("saveToSharedPreferences", "maxPushUps: ${sharedPreferences.getInt(getString(R.string.max_push_ups_setting), -1)}")

    }
}