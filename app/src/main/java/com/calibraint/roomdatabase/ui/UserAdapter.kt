package com.calibraint.roomdatabase.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.calibraint.roomdatabase.databinding.AdapterUserBinding
import com.calibraint.roomdatabase.model.UserData


class UserAdapter(private val data: ArrayList<UserData>?) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var binding: AdapterUserBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        binding = AdapterUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.updateView(data?.get(position))
    }

    override fun getItemCount() = data?.size?.let { data.size } ?: 0

     class ViewHolder(itemView: AdapterUserBinding) : RecyclerView.ViewHolder(itemView.root) {

        var name: TextView = itemView.tvName
        var email: TextView = itemView.tvEmail

        fun updateView(user: UserData?) {
            name.text = user?.name
            email.text = user?.email
        }
    }

}
