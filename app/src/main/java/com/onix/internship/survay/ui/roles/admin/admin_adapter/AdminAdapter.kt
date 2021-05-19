package com.onix.internship.survay.ui.roles.admin.admin_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.arch.events.SingleLiveEvent
import com.onix.internship.survay.data.local.user.User
import com.onix.internship.survay.databinding.AdminItemAdapterBinding
import com.onix.internship.survay.ui.roles.admin.AdminFragment
import com.onix.internship.survay.ui.roles.admin.AdminFragmentDirections

class AdminAdapter(private val list: List<User>) : RecyclerView.Adapter<AdminViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
        return AdminViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.admin_item_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

class AdminViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val binding = AdminItemAdapterBinding.bind(view)

    fun bind(user: User) {
        binding.textViewId.text = user.id.toString()
        binding.textViewLogin.text = user.login

        binding.cardView.setOnClickListener {
            val action = AdminFragmentDirections.actionAdminFragmentToEditUserFragment(user)
            view.findNavController().navigate(action)

        }

        binding.textViewRole.text = when (user.role) {
            0 ->  "Admin"
            1 -> "Manager"
            else -> "User"
        }
    }
}