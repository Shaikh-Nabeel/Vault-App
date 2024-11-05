package com.example.securevault.ui.credentials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.securevault.data.VaultDatabase
import com.example.securevault.databinding.FragmentCredentialsBinding
import com.example.securevault.ui.adapters.CredentialsAdapter
import kotlinx.coroutines.launch

class CredentialsFragment : Fragment() {

    private var _binding: FragmentCredentialsBinding? = null
    private val binding get() = _binding!!
    private lateinit var credentialsAdapter: CredentialsAdapter
    private lateinit var database: VaultDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCredentialsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = VaultDatabase.getDatabase(requireContext())
        setupRecyclerView()
        setupFab()
        observeCredentials()
    }

    private fun setupRecyclerView() {
        credentialsAdapter = CredentialsAdapter()
        binding.recyclerViewCredentials.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = credentialsAdapter
        }
    }

    private fun setupFab() {
        binding.fabAddCredential.setOnClickListener {
            showAddCredentialDialog()
        }
    }

    private fun showAddCredentialDialog() {
        val dialog = AddCredentialDialog().apply {
            onCredentialAdded = { credential ->
                lifecycleScope.launch {
                    database.credentialDao().insert(credential)
                }
            }
        }
        dialog.show(childFragmentManager, "AddCredentialDialog")
    }

    private fun observeCredentials() {
        lifecycleScope.launch {
            database.credentialDao().getAllCredentials().collect { credentials ->
                credentialsAdapter.submitList(credentials)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}