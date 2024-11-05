package com.example.securevault.ui.credentials

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.securevault.data.entity.CredentialEntity
import com.example.securevault.databinding.DialogAddCredentialBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddCredentialDialog : DialogFragment() {
    private var _binding: DialogAddCredentialBinding? = null
    private val binding get() = _binding!!
    
    var onCredentialAdded: ((CredentialEntity) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddCredentialBinding.inflate(LayoutInflater.from(context))
        
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Credential")
            .setView(binding.root)
            .setPositiveButton("Save") { _, _ ->
                val title = binding.editTitle.text.toString()
                val username = binding.editUsername.text.toString()
                val password = binding.editPassword.text.toString()
                
                if (title.isNotBlank() && username.isNotBlank() && password.isNotBlank()) {
                    val credential = CredentialEntity(
                        title = title,
                        username = username,
                        password = password
                    )
                    onCredentialAdded?.invoke(credential)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}