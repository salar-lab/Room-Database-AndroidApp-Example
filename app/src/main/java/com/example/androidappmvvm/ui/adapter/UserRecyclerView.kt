package com.example.androidappmvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidappmvvm.R
import com.example.androidappmvvm.model.entity.User

class UserRecyclerView : RecyclerView.Adapter<UserRecyclerView.UserViewHolder>() {

    var onListItemClick: OnListItemClick? = null

    var userList: List<User> = emptyList()

    fun setList(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var iv_userImage: ImageView = itemView.findViewById(R.id.iv_item_userImage)
        var tv_userName: TextView = itemView.findViewById(R.id.tv_item_userName)
        var tv_message: TextView = itemView.findViewById(R.id.tv_item_message)

        fun bind(user: User) {
            iv_userImage.setImageResource(user.imageId)
            tv_userName.text = user.name
            tv_message.text = user.message

            itemView.setOnClickListener{
                onListItemClick?.onItemClick(user)

            }
        }
    }

    //This Method to return View Holder in List Item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // To Get View(Activity) i am not in Activity Class, so i need Layoutinflater.from(context).inflate(R..)
        // view = list_item
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return UserViewHolder(view) // return to Class UserViewHolder
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var user: User = userList.get(position)
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }


}