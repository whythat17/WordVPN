package com.wordvpn.app.net

/** Тело запроса для проверки кода на сервере */
data class VerifyRequest(
    val code: String
)
