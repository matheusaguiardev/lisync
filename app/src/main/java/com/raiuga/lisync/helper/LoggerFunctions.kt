package com.raiuga.lisync.helper

import android.util.Log

class LoggerFunctions {

    companion object {
        private const val logMsg = "debugger"

        fun appLog(msg: String) {
            Log.d(logMsg, msg)
        }
    }

}