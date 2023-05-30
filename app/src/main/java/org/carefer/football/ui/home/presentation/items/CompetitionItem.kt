package org.carefer.football.ui.home.presentation.items

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.ModelAbstractItem
import org.carefer.football.R
import org.carefer.football.ui.home.data.model.CompetitionModel

class CompetitionItem(
    var competition: CompetitionModel
) : ModelAbstractItem<CompetitionModel, CompetitionItem.CompetitionViewHolder>(competition) {

    override val layoutRes: Int
        get() = R.layout.item_date
    override val type: Int
        get() = R.id.FastAdapterCompetitionItem

    override fun getViewHolder(v: View): CompetitionViewHolder {
        return CompetitionViewHolder(v)
    }

    override fun bindView(holder: CompetitionViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

        holder.tvCompName.text = competition.competitionName
        //holder.tvMatchday.text = "Matchday #" + competition.matchday
    }

    override fun unbindView(holder: CompetitionViewHolder) {
        super.unbindView(holder)
        holder.tvCompName.text = ""
        //holder.tvMatchday.text = ""
    }

    class CompetitionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCompName: TextView = view.findViewById(R.id.tv_date)

    }

}