package ka.el.a200pushups.data

class TrainWeekData {
    private val trainWeeks = listOf(
        TrainWeek(
            1,
            "Новичек",
            0,
            listOf(
                listOf(1, 1, 1, 1, 1),
                listOf(1, 1, 1, 1, 1),
                listOf(1, 1, 1, 1, 1)
            ),
            listOf(60, 60, 60)
        ),

        TrainWeek(
            2,
            "Аматор",
            10,
            listOf(
                listOf(6, 5, 4, 4, 3),
                listOf(6, 5, 5, 4, 4),
                listOf(7, 6, 5, 5, 4)
            ),
            listOf(60, 90, 120)
        ),

        TrainWeek(
            3,
            "В хорошей форме",
            20,
            listOf(
                listOf(12, 10, 9, 8, 7),
                listOf(13, 11, 10, 9, 8),
                listOf(14, 12, 11, 10, 9)
            ),
            listOf(90, 120, 150)
        ),

        TrainWeek(
            4,
            "Красавчик",
            30,
            listOf(
                listOf(18, 15, 13, 12, 10),
                listOf(19, 16, 15, 13, 12),
                listOf(21, 18, 16, 15, 13),
            ),
            listOf(90, 120, 150)
        ),

        TrainWeek(
            5,
            "Спортсмен",
            40,
            listOf(
                listOf(24, 20, 18, 16, 14),
                listOf(26, 22, 20, 18, 16),
                listOf(28, 24, 22, 20, 18),
            ),
            listOf(120, 150, 180)
        ),
    )

    fun getAllTrains(): List<TrainWeek> {
        return trainWeeks
    }

    fun getTrainByLevel(level: Int): TrainWeek {
        return trainWeeks[level - 1]
    }

    fun getTrainByMaxPushUps(maxPushUps: Int): TrainWeek {
        var findTrainWeek: TrainWeek = trainWeeks[0]

        if (maxPushUps > 10) {
            for (trainWeek in trainWeeks) if (trainWeek.minPushUps <= maxPushUps) findTrainWeek = trainWeek
        }

        return findTrainWeek
    }
}