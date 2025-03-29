package com.ricardo.broadcastreceivers

import android.content.Context
import android.content.Intent
import android.os.Bundle

object BroadcastUtils {

    fun sendBroadcast(context: Context, action: String, extras: Bundle? = null) {
        val intent = Intent(action)
        extras?.let { intent.putExtras(it) }
        context.sendBroadcast(intent)
    }
}
