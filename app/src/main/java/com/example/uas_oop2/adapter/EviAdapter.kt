package com.example.uas_oop2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_oop2.R
import com.example.uas_oop2.model.EviUser

class EviAdapter (val context: Context):RecyclerView.Adapter<EviAdapter.UserViewHolder>(){
    private var users: MutableList<EviUser> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EviAdapter.UserViewHolder {
        return UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_evi, parent,false))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: EviAdapter.UserViewHolder, position: Int) {
        holder.bindModel(users[position])
    }
    fun setUser(data: List<EviUser>){
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }
    inner class UserViewHolder(i : View): RecyclerView.ViewHolder(i){
        val tvId: TextView = i.findViewById(R.id.tv_id)
        val tvNama: TextView = i.findViewById(R.id.tv_nama)
        val tvNim: TextView = i.findViewById(R.id.tv_nim)
        fun bindModel(u: EviUser){
            tvId.text=u.getId().toString()
            tvNama.text=u.getNama()
            tvNim.text=u.getNim()
        }
    }

}