package com.example.btvn_android_29_10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailAdapter(private val emailList: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]
        holder.avatar.text = email.senderName.first().toString()
        holder.senderName.text = email.senderName
        holder.emailTitle.text = email.emailTitle
        holder.emailSnippet.text = email.emailSnippet
        holder.emailTime.text = email.time
    }

    override fun getItemCount() = emailList.size

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: TextView = itemView.findViewById(R.id.avatar)
        val senderName: TextView = itemView.findViewById(R.id.senderName)
        val emailTitle: TextView = itemView.findViewById(R.id.emailTitle)
        val emailSnippet: TextView = itemView.findViewById(R.id.emailSnippet)
        val emailTime: TextView = itemView.findViewById(R.id.emailTime)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.favoriteIcon)
    }
}
