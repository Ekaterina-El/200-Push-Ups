package ka.el.a200pushups.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import ka.el.a200pushups.R
import ka.el.a200pushups.utils.Time

private val time = Time()

class DaysOfTrainWeekAdapter(
    var context: Context,
    var breaks: List<Int>,
    var days: List<List<Int>>,
    var currentDay: Int,
    var navController: NavController
) : RecyclerView.Adapter<DaysOfTrainWeekAdapter.ViewHolder>() {

    @JvmName("setCurrentDay1")
    public final fun setCurrentDay(day: Int) {
        currentDay = day
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numberDayOfWeek: TextView
        val trainSet: TextView
        val timeBreak: TextView
        val breakDay: TextView
        val day: TextView

        val trainIcon: ImageView
        val statusImg: ImageView

        init {
            numberDayOfWeek = itemView.findViewById(R.id.numberDayOfWeek)
            trainSet = itemView.findViewById(R.id.trainSet)
            timeBreak = itemView.findViewById(R.id.timeBreak)
            statusImg = itemView.findViewById(R.id.status_img)
            breakDay = itemView.findViewById(R.id.break_day)
            day = itemView.findViewById(R.id.day)
            trainIcon = itemView.findViewById(R.id.train_icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.train_week_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = position + 1

        val isTrainDay = days[position][0] != 0

        if (isTrainDay) {
            // If it`s simple train day //
            holder.numberDayOfWeek.text = day.toString()
            holder.trainSet.text = days[position].joinToString(" ")
            holder.timeBreak.text =
                context.resources.getString(R.string.break_time, time.secondsToMinute(breaks[position]))
        } else {
            // If it`s day for tasting //

            holder.numberDayOfWeek.visibility = View.GONE

            holder.trainSet.text = context.getString(R.string.tester_description)
            holder.trainSet.textSize = 16f

            holder.timeBreak.visibility = View.GONE
            holder.breakDay.visibility = View.GONE
            holder.trainIcon.visibility = View.VISIBLE
            holder.day.text = context.getString(R.string.test_title)
        }

        when {
            day == currentDay -> {
                holder.statusImg.setImageResource(R.drawable.ic_arrow)
                if (isTrainDay) {
                    holder.itemView.setOnClickListener { view -> goTrainScreen() }
                } else {
                    holder.itemView.setOnClickListener { view -> goTesterScreen() }
                }
            }
            currentDay > day  -> {
                holder.statusImg.setImageResource(R.drawable.ic_done)
            }
            else -> {
                holder.statusImg.visibility = View.INVISIBLE
            }
        }

    }

    private fun goTesterScreen() {
        navController.navigate(R.id.action_trainWeekFragment_to_testerFragment)
    }

    private fun goTrainScreen() {
        navController.navigate(R.id.action_trainWeekFragment_to_trainFragment)
    }


    override fun getItemCount() = days.size

}