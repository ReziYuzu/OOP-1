package com.example.uas_oop2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_oop2.R
import com.example.uas_oop2.model.SholehDosen

//adapter komponen recycleview = menyeting / wadah untuk menampilkan sebuah data
class SholehAdapter (val context: Context):RecyclerView.Adapter<SholehAdapter.DosenViewHolder>(){
    private var dosen: MutableList<SholehDosen> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SholehAdapter.DosenViewHolder {
        return DosenViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sholeh, parent,false))
    }

    override fun getItemCount(): Int {
        return dosen.size
    }

    override fun onBindViewHolder(holder: SholehAdapter.DosenViewHolder, position: Int) {
        holder.bindModel(dosen[position])
    }
    //untuk memastikan data ada atau tidak
    fun setDosen(data: List<SholehDosen>){
        dosen.clear()
        dosen.addAll(data)
        notifyDataSetChanged()
    }

    inner class DosenViewHolder(i : View):RecyclerView.ViewHolder(i){
        val tvId: TextView = i.findViewById(R.id.tv_id) //
        val tvNama: TextView = i.findViewById(R.id.tv_nama)
        val tvNipy: TextView = i.findViewById(R.id.tv_nipy)
        fun bindModel(u: SholehDosen){
            tvId.text=u.getId().toString()
            tvNama.text=u.getNama()
            tvNipy.text=u.getNipy()
        }
    }
}