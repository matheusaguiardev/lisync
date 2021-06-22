package com.raiuga.lisync.ui.login

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.raiuga.lisync.R
import com.raiuga.lisync.data.LoginDataRepository
import java.util.concurrent.TimeUnit

class LoginActivity: AppCompatActivity() {

    private val TAG = "LoginActivity"

    private val firebaseAuth = Firebase.auth
    private val phoneNumber = "+5516982181614"

    private val loginRepository = LoginDataRepository()

    private val loginButton by lazy { findViewById<Button>(R.id.btn_login) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth.setLanguageCode("pr-BR")

        loginButton.setOnClickListener { startLogin() }
    }

    private fun startLogin() {
        loginRepository.loginByNumber(createOptionForLogin(phoneNumber))
    }

    private fun getCredential() {
        // Depois que o usuário digitar o código de verificação que o Firebase enviou para o telefone do usuário
//        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user

                    Log.d(TAG, "user")
                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    private fun createOptionForLogin(number: String) = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber(number)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(listenerVerificationState())          // OnVerificationStateChangedCallbacks
            .build()

    private fun listenerVerificationState(): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        return object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Log.d(TAG, "DEU CERTOOOOO: ${p0.smsCode}")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.d(TAG, "Falou porque: ${p0.localizedMessage}")
            }
        }
    }

}