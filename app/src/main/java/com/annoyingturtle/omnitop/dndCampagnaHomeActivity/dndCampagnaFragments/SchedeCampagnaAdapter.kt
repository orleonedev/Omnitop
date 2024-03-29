package com.annoyingturtle.omnitop.dndCampagnaHomeActivity.dndCampagnaFragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.annoyingturtle.omnitop.R
import dndData.entities.Scheda
import kotlinx.android.synthetic.main.lista_schede_campagna.view.*

class SchedeCampagnaAdapter(private val listener : onItemClickListner): RecyclerView.Adapter<SchedeCampagnaAdapter.SchedeViewHolder>() {

    private var SchedeList = emptyList<Scheda>()

    inner class SchedeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val pos = adapterPosition

            if(pos!= RecyclerView.NO_POSITION)
                listener.onItemClick(pos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedeViewHolder {
        return SchedeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lista_schede_campagna,parent,false))
    }

    override fun onBindViewHolder(holder: SchedeViewHolder, position: Int){
        val currentItem = SchedeList[position]
        holder.itemView.nomePgText.text = currentItem.nomePG
        holder.itemView.pgImage.load(currentItem.imgSchedaBitmap)
    }

    fun setDataSchede(scheda: List<Scheda>){
        this.SchedeList = scheda
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return SchedeList.size
    }

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun getItemID(position: Int): Int{
        return SchedeList[position].id
    }




}
