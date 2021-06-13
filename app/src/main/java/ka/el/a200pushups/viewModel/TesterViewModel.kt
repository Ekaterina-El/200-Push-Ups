package ka.el.a200pushups.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TesterViewModel: ViewModel() {
    private val _counterValue = MutableLiveData<Int>()
    val counterValue: LiveData<Int> get() = _counterValue

    fun setCounterValue(value: Int) {
        if (value < 0 || value > 1000) {
            _counterValue.value = 0
            return
        }
        _counterValue.value = value
    }

    fun increment() {
        _counterValue.value = _counterValue.value?.plus(1)
    }

    fun decrement() {
        val value = _counterValue.value
        if (value!! > 0) _counterValue.value = value - 1
    }


    init {
        _counterValue.value = 0
    }
}