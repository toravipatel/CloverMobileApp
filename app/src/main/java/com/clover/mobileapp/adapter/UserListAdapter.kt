package com.clover.mobileapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.clover.mobileapp.model.userlist.Result

open class UserListAdapter(private var userList:ArrayList<Result>, private var context: Context, private val userClickListener: OnUserClickListener) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_user_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemUser = userList[position]

        // sets the image to the imageview from our itemHolder class
        holder.nameTV.text = itemUser.name

        // sets the text to the textview from our itemHolder class
        holder.statusTV.text = itemUser.status



        holder.layout.setOnClickListener {
            userClickListener.onUserClick(itemUser)
        }


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun submitData(dataList: List<Result>) {
        userList.clear()
        userList.addAll(dataList)
        notifyDataSetChanged()
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val nameTV: TextView = itemView.findViewById(R.id.nameTV)
        val statusTV: TextView = itemView.findViewById(R.id.statusTV)
        val layout:ConstraintLayout = itemView.findViewById(R.id.layout)
    }

    interface OnUserClickListener{

        fun onUserClick(result: Result)

    }

}
