package com.example.eventsapp.broadcasts

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.speech.tts.TextToSpeech
import com.example.eventsapp.data.room.dao.EventDao
import com.example.eventsapp.service.EventService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class EventBroadcast : BroadcastReceiver() {

    var tts: TextToSpeech? = null
    var isTrue:Boolean = false

    @Inject
    lateinit var dao: EventDao

    override fun onReceive(context: Context?, intent: Intent?) {
        if (tts == null) {
            tts = TextToSpeech(context) { status ->
                if (status != TextToSpeech.ERROR) {
                    tts!!.language = Locale.forLanguageTag("Ru")
                }
            }
        }
        when (intent?.action) {
            Intent.ACTION_SCREEN_ON -> {
                event(9)
            }
            Intent.ACTION_SCREEN_OFF -> {
                event(10)
            }
            WifiManager.NETWORK_STATE_CHANGED_ACTION -> {
                val action = intent.getParcelableExtra<NetworkInfo>(WifiManager.EXTRA_NETWORK_INFO)
                if (action?.isConnected!!) {

                    if (!isTrue){
                        event(1)
                        isTrue = true
                    }

                } else {
                    if (isTrue) {
                        event(2)
                        isTrue = false
                    }
                }
            }
            Intent.ACTION_POWER_CONNECTED -> {
                event(11)
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                event(12)
            }

            Intent.ACTION_BATTERY_LOW -> {
                event(4)
            }
            Intent.ACTION_BATTERY_OKAY -> {
                event(3)
            }

            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val isAirplaneModeOn = intent.getBooleanExtra("state", false)
                if (isAirplaneModeOn) {
                    event(5)
                } else {
                    event(6)
                }
            }
            Intent.ACTION_HEADSET_PLUG -> {
                val state = intent.getIntExtra("state", -1)
                if (state == 0) {
                    event(14)
                } else if (state == 1) {
                    event(13)

                }
            }
            BluetoothAdapter.ACTION_STATE_CHANGED -> {
                val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)
                if (state == BluetoothAdapter.STATE_OFF) {
                    event(8)
                } else if (state == BluetoothAdapter.STATE_ON) {
                    event(7)
                }
            }
            Intent.ACTION_REBOOT -> {
                context?.startService(Intent(context, EventService::class.java))
            }
        }
    }

    private fun event(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = dao.getEventById(id)
            if (data.status == 1) {
                speak(data.name)
            }
        }
    }

    private fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }
}