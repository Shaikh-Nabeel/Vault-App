package com.example.securevault

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.securevault.databinding.ActivityMainBinding
import com.example.securevault.ui.cards.CardsFragment
import com.example.securevault.ui.credentials.CredentialsFragment
import com.example.securevault.ui.settings.SettingsFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var biometricPrompt: BiometricPrompt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBiometricAuth()
        setupBottomNavigation()
    }

    private fun setupBiometricAuth() {
        val executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    // Authentication successful, show the app content
                    binding.root.visibility = android.view.View.VISIBLE
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(this@MainActivity, "Authentication error: $errString", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Authenticate")
            .setSubtitle("Use your fingerprint or face to access the vault")
            .setNegativeButtonText("Cancel")
            .build()

        // Start authentication
        biometricPrompt.authenticate(promptInfo)
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_credentials -> {
                    loadFragment(CredentialsFragment())
                    true
                }
                R.id.navigation_cards -> {
                    loadFragment(CardsFragment())
                    true
                }
                R.id.navigation_settings -> {
                    loadFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }

        // Set default fragment
        loadFragment(CredentialsFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}