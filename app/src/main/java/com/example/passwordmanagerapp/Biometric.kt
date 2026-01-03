package com.example.passwordmanagerapp

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

object Biometric{
    fun isBiometricAvailable(context: Context): Boolean{

        val biometricManager = androidx.biometric.BiometricManager.from(context)
        return biometricManager.canAuthenticate(
            androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
        )== androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
    }

    fun showBiometricPrompt(
        activity : FragmentActivity,
        onSuccess : ()-> Unit
    ){
        val executor = ContextCompat.getMainExecutor(activity)

        val promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Unlock Password")
            .setSubtitle("Authenticate to view password")
            .setNegativeButtonText("Cancel")
            .build()

        val biometricPrompt = androidx.biometric.BiometricPrompt(
            activity,
            executor,
            object : androidx.biometric.BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationSucceeded(
                    result: androidx.biometric.BiometricPrompt.AuthenticationResult)
                {
                    onSuccess
                }
            }
        )
        biometricPrompt.authenticate(promptInfo)
    }
}