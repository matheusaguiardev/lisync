package com.raiuga.lisync.data

import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.raiuga.lisync.domain.LoginRepository

class LoginDataRepository: LoginRepository {

    override fun loginByNumber(options: PhoneAuthOptions): Boolean {
        PhoneAuthProvider.verifyPhoneNumber(options)
        return true
    }


}