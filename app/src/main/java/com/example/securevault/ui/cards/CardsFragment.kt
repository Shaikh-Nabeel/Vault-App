package com.example.securevault.ui.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.securevault.data.VaultDatabase
import com.example.securevault.databinding.FragmentCardsBinding
import com.example.securevault.ui.adapters.CardsAdapter
import kotlinx.coroutines.launch

class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!
    private lateinit var cardsAdapter: CardsAdapter
    private lateinit var database: VaultDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = VaultDatabase.getDatabase(requireContext())
        setupRecyclerView()
        setupFab()
        observeCards()
    }

    private fun setupRecyclerView() {
        cardsAdapter = CardsAdapter()
        binding.recyclerViewCards.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cardsAdapter
        }
    }

    private fun setupFab() {
        binding.fabAddCard.setOnClickListener {
            showAddCardDialog()
        }
    }

    private fun showAddCardDialog() {
        val dialog = AddCardDialog().apply {
            onCardAdded = { card ->
                lifecycleScope.launch {
                    database.cardDao().insert(card)
                }
            }
        }
        dialog.show(childFragmentManager, "AddCardDialog")
    }

    private fun observeCards() {
        lifecycleScope.launch {
            database.cardDao().getAllCards().collect { cards ->
                cardsAdapter.submitList(cards)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}