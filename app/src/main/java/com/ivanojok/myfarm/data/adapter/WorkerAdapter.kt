package com.ivanojok.myfarm.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ivanojok.myfarm.R
import com.ivanojok.myfarm.data.model.AuthResponse

class WorkerAdapter(val context:Context, val list: List<AuthResponse>): RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>() {

    class WorkerViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.worker_name)
        val title = itemView.findViewById<TextView>(R.id.worker_title)
        val phone = itemView.findViewById<TextView>(R.id.worker_phone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_worker_layout, parent, false)
        return WorkerViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        with(holder) {
            name.text = list[position].f_name + "\t" + list[position].l_name
            title.text = list[position].title
            phone.text = "+256 ${list[position].phone?.take(9)}"
        }
    }
}