package org.carefer.football.ui.home.presentation.items

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.ModelAbstractItem
import org.carefer.football.R
import org.carefer.football.ui.home.data.model.DateModel

class DateItem(
    val dateModel: DateModel
) : ModelAbstractItem<DateModel, DateItem.DateViewHolder>(dateModel) {

    override val layoutRes: Int
        get() = R.layout.item_date
    override val type: Int
        get() = R.id.FastAdapterDateItem

    override fun getViewHolder(v: View): DateViewHolder {
        return DateViewHolder(v)
    }

    override fun bindView(holder: DateViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.tvDate.text = "${dateModel.dateOfMonth} ${dateModel.dayOfWeek}"
    }

    override fun unbindView(holder: DateViewHolder) {
        super.unbindView(holder)
        holder.tvDate.text = ""
    }

    class DateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDate: TextView = view.findViewById(R.id.tv_date)
    }

}