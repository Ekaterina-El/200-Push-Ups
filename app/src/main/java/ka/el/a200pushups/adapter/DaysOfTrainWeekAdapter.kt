package ka.el.a200pushups.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ka.el.a200pushups.R
import ka.el.a200pushups.utils.Time

private val time = Time()

class DaysOfTrainWeekAdapter(
    var context: Context,
    var breaks: List<Int>,
    var days: List<List<Int>>,
    var currentDay: Int
) : RecyclerView.Adapter<DaysOfTrainWeekAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numberDayOfWeek: TextView
        val trainSet: TextView
        val timeBreak: TextView
        val statusImg: ImageView

        init {
            numberDayOfWeek = itemView.findViewById(R.id.numberDayOfWeek)
            trainSet = itemView.findViewById(R.id.trainSet)
            timeBreak = itemView.findViewById(R.id.timeBreak)
            statusImg = itemView.findViewById(R.id.status_img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.train_week_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = position + 1

        holder.numberDayOfWeek.text = day.toString()
        holder.trainSet.text = days[position].joinToString(" ") // [1, 2, 3, 4, 5]
        holder.timeBreak.text =
            context.resources.getString(R.string.break_time, time.secondsToMinute(breaks[position]))

        when {
            day == currentDay -> {
                holder.statusImg.setImageResource(R.drawable.ic_arrow)
            }
            currentDay > day  -> {
                holder.statusImg.setImageResource(R.drawable.ic_done)
            }
            else -> {
                holder.statusImg.visibility = View.INVISIBLE
            }
        }

    }

    override fun getItemCount() = days.size

}