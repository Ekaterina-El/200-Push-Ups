package ka.el.a200pushups.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ka.el.a200pushups.data.TrainWeek
import ka.el.a200pushups.data.TrainWeekData

class TrainViewModel : ViewModel() {
    private var _currentSet = MutableLiveData<Int>()
    val currentSet: LiveData<Int> get() = _currentSet

    fun goNextSet() {
        _currentSet.value = _currentSet.value?.plus(1)
        Log.d("goNextSet", "_currentSet.value: ${_currentSet.value}")

        val day = _currentDay.value
        _counterValue.value = _currentTrainWeek.value!!.days[day!!][_currentSet.value!!] - 1
    }

    /* Timer Variables - start */
    private var _isStartedTimer = MutableLiveData<Boolean>()
    val isStartedTimer: LiveData<Boolean> get() = _isStartedTimer

    private var _currentTimerValue = MutableLiveData<Int>()
    val currentTimerValue: LiveData<String> = Transformations.map(_currentTimerValue) {
        ka.el.a200pushups.utils.Time().secondsToMinute(it)
    }
    /* Timer Variables - end */


    private var _currentTrainWeek = MutableLiveData<TrainWeek>()
    val currentTrainWeek: LiveData<TrainWeek> get() = _currentTrainWeek


    fun setCurrentTrainWeek(trainWeek: TrainWeek, day: Int) {
        _currentTrainWeek.value = trainWeek
        _currentDay.value = day

        _counterValue.value = trainWeek.days[day][0] - 1
        thisTrainBreak = trainWeek.breaks[day]
    }

    var thisTrainBreak = 0

    private var _currentDay = MutableLiveData<Int>()
    val currentDay: LiveData<Int> get() = _currentDay

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

    fun offTimer() {
        _isStartedTimer.value = false
        _currentTimerValue.value = 0
    }

    fun onTimer() {
        _isStartedTimer.value = true
        _currentTimerValue.value = thisTrainBreak
    }

    fun downTimer() {
        _currentTimerValue.value = _currentTimerValue.value?.minus(1)
    }

    fun skipRes() {
        goNextSet()
        offTimer()
    }


    init {
        _isStartedTimer.value = false

        _currentSet.value = 0
        _counterValue.value = 0
        _currentTrainWeek.value = TrainWeekData().getTrainByLevel(1)
    }
}