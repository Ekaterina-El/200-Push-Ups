package ka.el.a200pushups

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import ka.el.a200pushups.databinding.ActivityTesterBinding
import ka.el.a200pushups.fragments.WelcomeScreen
import ka.el.a200pushups.viewModel.TesterViewModel

class TesterActivity : AppCompatActivity() {
    private val viewModel: TesterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        val binder: ActivityTesterBinding = DataBindingUtil.setContentView(this, R.layout.activity_tester)
        Log.d("TAG", "viewModel ${viewModel.counterValue.value}")
        binder.lifecycleOwner = this
        binder.testerActivity = this
        binder.testerViewModel = viewModel

        binder.valueCounter.addTextChangedListener(
            object: TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString().equals("")) {
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
        viewModel.setCounterValue(0)
        setResult(RESULT_OK, intent)
        finish()
    }
}