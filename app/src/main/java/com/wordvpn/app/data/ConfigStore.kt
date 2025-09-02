package com.wordvpn.app.data

import android.content.Context
import java.io.File

class ConfigStore(private val ctx: Context) {
    private val fileName = "wg.conf"

    fun saveConfig(text: String) {
        ctx.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(text.toByteArray(Charsets.UTF_8))
        }
    }

    fun readConfigOrNull(): String? {
        val f = File(ctx.filesDir, fileName)
        return if (f.exists()) f.readText(Charsets.UTF_8) else null
    }
}
