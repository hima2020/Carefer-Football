package org.carefer.football.ui.home.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.carefer.football.R
import org.carefer.football.databinding.ItemMatchBinding
import org.carefer.football.ui.home.data.model.MatchModel
import org.carefer.football.views.extentions.getTextColor

class MatchListAdapter(private val mList: ArrayList<Any>) :
    RecyclerView.Adapter<MatchListAdapter.ViewHolder>() {

    var onItemClick: ((MatchModel) -> Unit)? = null

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
            if (item is MatchModel) {
                itemBinding?.cvMatch?.visibility = View.VISIBLE
                //  itemBinding?.tvDate?.visibility = View.GONE
                itemBinding?.tvAwayTeam?.text = item.awayTeamName
                itemBinding?.tvHomeTeam?.text = item.homeTeamName
//                itemBinding?.tvScoreTeamAway?.text = "${item.score?.fullTime?.away ?: "-"}"
//                itemBinding?.tvScoreTeamHome?.text = "${item.score?.fullTime?.home ?: "-"}"
                // itemBinding?.tvMatchDate?.text = item.utcDate?.getDate()


                itemBinding?.tvMatchTime?.setTextColor(
                    ContextCompat.getColor(
                        itemBinding.root.context,
                        item.status?.getTextColor()!!
                    )
                )
                if (item?.homeTeamScore == null) {
                    itemBinding?.tvScoreTeamHome?.text = "-"
                } else {
                    itemBinding?.tvScoreTeamHome?.text = item.homeTeamScore.toString()
                }
                if (item?.awayTeamScore == null) {
                    itemBinding?.tvScoreTeamAway?.text = "-"
                } else {
                    itemBinding?.tvScoreTeamAway?.text = item.awayTeamScore.toString()
                }
                itemBinding?.tvStatus?.text = item.status


                itemBinding?.tvMatchTime?.text = item.shortTime


                Glide.with(itemBinding.root.context)
                    .load(R.drawable.ic_favorites_active)
                    .placeholder(
                        ContextCompat.getDrawable(
                            itemBinding.root.context,
                            R.drawable.ic_launcher_foreground
                        )
                    )
                    .into(itemBinding.ivFav)

                Glide.with(itemView.context).load(item.awayTeamFlag)
                    .placeholder(R.drawable.ic_launcher_foreground).into(itemBinding.ivAwayFlag)
                Glide.with(itemView.context).load(item.homeTeamFlag)
                    .placeholder(R.drawable.ic_launcher_foreground).into(itemBinding.ivHomeFlag)


            }


            itemBinding?.ivFav?.setOnClickListener {

                onItemClick?.invoke(item as MatchModel)
                notifyItemChanged(position)
            }


        }
    }


    fun addMatches(matches: List<Any>) {
        mList.clear()
        mList.addAll(matches)
        notifyDataSetChanged()
    }


}
