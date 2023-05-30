package org.carefer.football.ui.home.presentation.items

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.items.ModelAbstractItem
import org.carefer.football.R
import org.carefer.football.ui.home.data.model.MatchModel

class MatchItem(
    private val match: MatchModel,
    private val context: Context
) : ModelAbstractItem<MatchModel, MatchItem.MatchViewHolder>(match) {

    var isFlagLoaded = false
    override val layoutRes: Int
        get() = R.layout.item_match
    override val type: Int
        get() = R.id.FastAdapterMatchItem

    override fun getViewHolder(v: View): MatchViewHolder {
        return MatchViewHolder(v)
    }

    override fun bindView(holder: MatchViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

        if (match.status == "SCHEDULED") {
            holder.lytMatchSchedule.visibility = View.VISIBLE
        } else {
            holder.lytMatchSchedule.visibility = View.GONE
        }

//        if (!isFlagLoaded)
//            onTeamInfo.getTeamsFlag(match.homeTeam.id, match.awayTeam.id, object : OnResult {
//                override fun onSuccess() {
//                    isFlagLoaded = true
//                }
//            })

        Glide.with(context)
            .load(match.homeTeam.teamFlag)
            .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground))
            .into(holder.ivHomeTeam)
        Glide.with(context)
            .load(match.awayTeam.teamFlag)
            .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground))
            .into(holder.ivAwayTeam)

        holder.tvHomeTeam.text = match.homeTeam.name
        holder.lytMatchSchedule.text = match.shortTime
        holder.tvAwayTeam.text = match.awayTeam.name
        holder.tvMatchStatus.text = match.status.toLowerCase()
        if (match.homeTeamScore != null) {
            holder.tvHomeTeamScore.text = match.homeTeamScore.toString()
        } else {
            holder.tvHomeTeamScore.text = "-"
        }
        if (match.awayTeamScore != null) {
            holder.tvAwayTeamScore.text = match.awayTeamScore.toString()
        } else {
            holder.tvAwayTeamScore.text = "-"
        }

    }

    override fun unbindView(holder: MatchViewHolder) {
        super.unbindView(holder)

        holder.tvHomeTeam.text = ""
        holder.tvAwayTeam.text = ""
        holder.tvMatchStatus.text = ""
        holder.tvHomeTeamScore.text = ""
        holder.tvAwayTeamScore.text = ""
        holder.lytMatchSchedule.visibility = View.GONE
        holder.lytMatchSchedule.visibility = View.GONE
        holder.ivAwayTeam.setImageDrawable(null)
        holder.ivHomeTeam.setImageDrawable(null)
    }

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvHomeTeam: TextView = view.findViewById(R.id.tv_home_team)
        var tvAwayTeam: TextView = view.findViewById(R.id.tv_away_team)
        var lytMatchSchedule: TextView = view.findViewById(R.id.tv_match_time)
        var tvHomeTeamScore: TextView = view.findViewById(R.id.tv_score_team_home)
        var tvAwayTeamScore: TextView = view.findViewById(R.id.tv_score_team_away)
        var tvMatchStatus: TextView = view.findViewById(R.id.tv_status)
        var ivHomeTeam: ImageView = view.findViewById(R.id.iv_home_flag)
        var ivAwayTeam: ImageView = view.findViewById(R.id.iv_away_flag)

    }
}

interface OnTeamInfo {
    fun getTeamsFlag(homeTeamId: Int, awayTeamId: Int, onResult: OnResult)
}

interface OnResult {
    fun onSuccess()
}