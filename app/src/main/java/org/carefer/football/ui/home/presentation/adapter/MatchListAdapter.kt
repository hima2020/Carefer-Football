package org.carefer.football.ui.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.carefer.football.databinding.ItemMatchBinding
import org.carefer.football.ui.home.data.model.Matche

class MatchListAdapter(private val mList: ArrayList<Matche>) :
    RecyclerView.Adapter<MatchListAdapter.ViewHolder>() {

    var onItemClick: ((Matche) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val item = mList[position]

        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(private val itemBinding: ItemMatchBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(match: Matche) {


            itemBinding?.tvAwayTeam?.text = match.homeTeam?.name
            itemBinding?.tvHomeTeam?.text = match.awayTeam?.name
            itemBinding?.tvScoreTeamAway?.text = "1"
            itemBinding?.tvScoreTeamHome?.text = "0"
            itemBinding?.tvMatchDate?.text = "2020-05-30"



            itemBinding?.root.setOnClickListener {
                onItemClick?.invoke(match)
            }


        }
    }

    fun addMatches(matches: List<Matche>) {
        mList.removeAll(mList)
        mList.addAll(matches)
        notifyDataSetChanged()
    }


}
