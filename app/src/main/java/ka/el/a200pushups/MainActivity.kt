package ka.el.a200pushups

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ka.el.a200pushups.databinding.ActivityMainBinding
import ka.el.a200pushups.viewModel.PushUpsViewModel

class MainActivity : AppCompatActivity() {
    private val myViewModel: PushUpsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide() // hide action bar

        var binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
}
