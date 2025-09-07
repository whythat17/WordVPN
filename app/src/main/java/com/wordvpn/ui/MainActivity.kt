package com.wordvpn.app.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wordvpn.app.R

class MainActivity : AppCompatActivity() {

    private lateinit var tvStatus: TextView
    private lateinit var btnConnect: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvStatus = findViewById(R.id.tvStatus)
        btnConnect = findViewById(R.id.btnConnect)

        // Стартовые значения
        tvStatus.text = getString(R.string.status_disconnected)
        btnConnect.text = getString(R.string.connect)

        // Временный «тоггл» для демо
        btnConnect.setOnClickListener {
            val disconnected = getString(R.string.status_disconnected)
            if (tvStatus.text == disconnected) {
                tvStatus.text = getString(R.string.status_connected)
                btnConnect.text = getString(R.string.disconnect)
            } else {
                tvStatus.text = getString(R.string.status_disconnected)
                btnConnect.text = getString(R.string.connect)
            }
        }
    }
}
