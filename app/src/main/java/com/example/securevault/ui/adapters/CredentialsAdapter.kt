package com.example.securevault.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.securevault.R
import com.example.securevault.data.entity.CredentialEntity

class CredentialsAdapter : RecyclerView.Adapter<CredentialsAdapter.CredentialViewHolder>() {

    private val credentialList = mutableListOf<CredentialEntity>()

    // Function to update the list
    fun submitList(list: List<CredentialEntity>) {
        credentialList.clear()
        credentialList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CredentialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_credential, parent, false) // replace with your item layout file
        return CredentialViewHolder(view)
    }

    override fun onBindViewHolder(holder: CredentialViewHolder, position: Int) {
        holder.bind(credentialList[position])
    }

    override fun getItemCount(): Int = credentialList.size

    inner class CredentialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        private val textUsername: TextView = itemView.findViewById(R.id.textUsername)
        private val textPassword: TextView = itemView.findViewById(R.id.textPassword)
        private val layoutDetails: View = itemView.findViewById(R.id.layoutDetails)

        fun bind(credential: CredentialEntity) {
            textTitle.text = credential.title
            textUsername.text = credential.username
            textPassword.text = credential.password

            // Toggle visibility of details on click
            itemView.setOnClickListener {
                layoutDetails.visibility = if (layoutDetails.visibility == View.GONE) View.VISIBLE else View.GONE
            }
        }
    }
}
