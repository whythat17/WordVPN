package com.wordvpn.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wordvpn.app.R
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var status: TextView
    private lateinit var toggle: Button
    private var isConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Если конфига нет — вернём на логин
        val cfg = File(File(filesDir, "wg"), "user.conf")
        if (!cfg.exists()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_main)
        status = findViewById(R.id.tvState)
        toggle = findViewById(R.id.btnToggle)

        render()

        toggle.setOnClickListener {
            // Пока просто имитация подключения
            isConnected = !isConnected
            render()
        }
    }

    private fun render() {
        status.text = if (isConnected) "Статус: Подключено" else "Статус: Отключено"
        toggle.text = if (isConnected) "Выключить" else "Включить"
    }
}
