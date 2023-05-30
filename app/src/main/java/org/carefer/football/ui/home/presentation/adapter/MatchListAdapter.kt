package org.carefer.football.ui.home.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.carefer.football.R
import org.carefer.football.databinding.ItemMatchBinding
import org.carefer.football.ui.home.data.model.Matche
import org.carefer.football.views.extentions.getTextColor
import org.carefer.football.views.extentions.getTime

class MatchListAdapter(private val mList: ArrayList<Any>) :
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
        fun bind(item: Any) {
            if (item is Matche) {
                itemBinding?.cvMatch?.visibility = View.VISIBLE
                //  itemBinding?.tvDate?.visibility = View.GONE
                itemBinding?.tvAwayTeam?.text = item.awayTeam?.name
                itemBinding?.tvHomeTeam?.text = item.homeTeam?.name
//                itemBinding?.tvScoreTeamAway?.text = "${item.score?.fullTime?.away ?: "-"}"
//                itemBinding?.tvScoreTeamHome?.text = "${item.score?.fullTime?.home ?: "-"}"
                // itemBinding?.tvMatchDate?.text = item.utcDate?.getDate()


                itemBinding?.tvMatchTime?.setTextColor(
                    ContextCompat.getColor(
                        itemBinding.root.context,
                        item.status?.getTextColor()!!
                    )
                )
                if (item.status.equals("TIMED")) {
                    itemBinding?.tvMatchTime?.text = item.utcDate?.getTime()
                } else {
                    itemBinding?.tvMatchTime?.text = item.status?.trim()
                }

//                Glide.with(itemView.context).load(item.awayTeam?.crest)
//                    .placeholder(R.drawable.ic_launcher_foreground).into(itemBinding.ivAwayFlag)
//                Glide.with(itemView.context).load(item.homeTeam?.crest)
//                    .placeholder(R.drawable.ic_launcher_foreground).into(itemBinding.ivHomeFlag)


                itemBinding?.root.setOnClickListener {
                    onItemClick?.invoke(item)
                }
            }
            if (item is String) {
                itemBinding?.cvMatch?.visibility = View.GONE
                //  itemBinding?.tvDate?.visibility = View.VISIBLE
                //  itemBinding.tvDate?.text = item

            }


        }
    }

    fun addMatches(matches: List<Any>) {
        mList.addAll(matches)
        notifyDataSetChanged()
    }


}
