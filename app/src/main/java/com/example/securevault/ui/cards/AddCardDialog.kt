package com.example.securevault.ui.cards

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.securevault.data.entity.CardEntity
import com.example.securevault.databinding.DialogAddCardBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddCardDialog : DialogFragment() {
    private var _binding: DialogAddCardBinding? = null
    private val binding get() = _binding!!
    
    var onCardAdded: ((CardEntity) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddCardBinding.inflate(LayoutInflater.from(context))
        
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Card")
            .setView(binding.root)
            .setPositiveButton("Save") { _, _ ->
                val title = binding.editTitle.text.toString()
                val number = binding.editCardNumber.text.toString()
                val cvv = binding.editCvv.text.toString()
                val expiryDate = binding.editExpiryDate.text.toString()
                
                if (title.isNotBlank() && number.isNotBlank() && cvv.isNotBlank() && expiryDate.isNotBlank()) {
                    val card = CardEntity(
                        title = title,
                        number = number,
                        cvv = cvv,
                        expiryDate = expiryDate
                    )
                    onCardAdded?.invoke(card)
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