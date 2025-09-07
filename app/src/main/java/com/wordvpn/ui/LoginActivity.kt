package com.wordvpn.app.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wordvpn.app.databinding.ActivityLoginBinding
import com.wordvpn.app.net.Api
import com.wordvpn.app.net.VerifyRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Открыть наш Telegram-бот
        binding.tvGetCode.setOnClickListener {
            // замени username на своего бота
            val tg = "https://t.me/your_bot_username"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(tg)))
        }

        // Отправка кода на сервер
        binding.btnContinue.setOnClickListener {
            val code = binding.etCode.text?.toString()?.trim().orEmpty()
            if (code.length != 6) {
                Toast.makeText(this, "Введите 6-значный код", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            validateCode(code)
        }
    }

    private fun validateCode(code: String) {
        // сетка в IO, UI — в Main
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val resp = Api.service.validateCode(VerifyRequest(code))
                if (resp.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@LoginActivity, "Код принят", Toast.LENGTH_SHORT).show()
                        // Переход на главный экран
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Неверный код (${resp.code()})",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Ошибка сети: ${e.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
