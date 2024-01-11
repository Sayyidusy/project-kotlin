package com.org.proyektingkat2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.org.proyektingkat2.model.User
import com.org.proyektingkat2.siagabanjir.R

class UserAdapter(
    private val userList: List<User>
    ) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewEmail: TextView = itemView.findViewById(R.id.textViewEmail)
        val textViewNama: TextView = itemView.findViewById(R.id.textViewNama)
        // Tambahkan TextView lainnya untuk menampilkan data pengguna lainnya
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.textViewEmail.text = "Email :" + user.email
        holder.textViewNama.text = "Nama Lengkap : " + user.namaLengkap
        // Set TextView lainnya dengan data pengguna lainnya
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
