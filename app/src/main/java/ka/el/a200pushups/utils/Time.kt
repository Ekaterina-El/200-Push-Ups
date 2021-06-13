package ka.el.a200pushups.utils

class Time {
    fun secondsToMinute(seconds: Int): String {
        val minutes = (seconds / 60)
        val r_seconds = seconds - minutes * 60
        return listOf(addZero(minutes), addZero(r_seconds)).joinToString(":")
    }

    private fun addZero(num: Int): String {
        return if (num < 10) "0${num}" else num.toString()
    }
}