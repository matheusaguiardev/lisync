package com.raiuga.lisync.domain

import com.google.firebase.auth.PhoneAuthOptions

interface LoginRepository {

    fun loginByNumber(options: PhoneAuthOptions): Boolean

}