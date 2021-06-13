package ka.el.a200pushups.viewModel

import android.transition.Transition
import android.util.Log
import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import ka.el.a200pushups.data.TrainWeek
import ka.el.a200pushups.data.TrainWeekData


class PushUpsViewModel: ViewModel() {
    private val _currentMaxPushUps = MutableLiveData<Int>()
    val currentMaxPushUps: LiveData<Int> get() = _currentMaxPushUps

    private val _currentWeek = MutableLiveData<Int>()
    val currentWeek: LiveData<Int> get() = _currentWeek

    private val _currentDay = MutableLiveData<Int>()
    val currentDay: LiveData<Int> get() = _currentDay

    private val _currentLevel = MutableLiveData<Int>()
    val currentLevel: LiveData<Int> get() = _currentLevel

    private val _currentLevelName = MutableLiveData<String>()
    val currentLevelName: LiveData<String> get() = _currentLevelName

    private val _currentGlobalGoal = MutableLiveData<Int>()
    val currentGlobalGoal: LiveData<Int> get() = _currentGlobalGoal


    private var _trainWeekData: TrainWeekData = TrainWeekData()

    private var _currentTrainWeek = MutableLiveData<TrainWeek>()
    val currentTrainWeek: LiveData<TrainWeek> get() = _currentTrainWeek

    init {
        _currentMaxPushUps.value = 0
        _currentWeek.value = 1
        _currentDay.value = 1
        _currentLevel.value = 1
        _currentLevelName.value = "Новичек"
        _currentGlobalGoal.value = 200

        updateCurrentTrainWeek()
    }

    private fun updateCurrentTrainWeek() {
        val currentWeek = _trainWeekData.getTrainByMaxPushUps(_currentMaxPushUps.value!!)
        _currentTrainWeek.value = currentWeek

        _currentDay.value = 1
        _currentLevel.value = currentWeek.numberOfLevels
        _currentLevelName.value = currentWeek.nameOfLevel
    }

    fun setCurrentMaxPushUps(pushUps: Int) {
        _currentMaxPushUps.value = pushUps
        updateCurrentTrainWeek()
    }
}
