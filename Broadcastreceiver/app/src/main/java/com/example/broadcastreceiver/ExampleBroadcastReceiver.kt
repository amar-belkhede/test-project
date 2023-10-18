package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class ExampleBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
//        if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
//            Toast.makeText(context, "Boot completed", Toast.LENGTH_LONG).show()
//        }
//
//        if (ConnectivityManager.CONNECTIVITY_ACTION == intent?.action) {
//            Toast.makeText(context, "Connectivity changed", Toast.LENGTH_LONG).show()
//        }
//
//        Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show()

        if("com.example.broadcastreceiver.EXAMPLE_ACTION" == intent?.action) {
            var receivedText = intent.getStringExtra("com.example.broadcastreceiver.EXTRA_TEXT")
            Toast.makeText(context, receivedText, Toast.LENGTH_SHORT).show()
        }

    }
}