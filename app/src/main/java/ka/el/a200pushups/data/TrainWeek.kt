package ka.el.a200pushups.data

data class TrainWeek(
    var numberOfLevels: Int,
    var nameOfLevel: String,
    var minPushUps: Int,
    var days: List<List<Int>>,
    var breaks: List<Int>
) {}
