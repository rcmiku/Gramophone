package org.akanework.gramophone.logic.utils

import android.annotation.SuppressLint

@SuppressLint("PrivateApi")
object XiaomiUtils {

    private const val KEY_MI_OS_VERSION_CODE = "ro.mi.os.version.code"
    private const val KEY_MI_OS_VERSION_NAME = "ro.mi.os.version.name"
    private const val KEY_MI_OS_VERSION_INCREMENTAL = "ro.mi.os.version.incremental"

    fun isXiaomiHyperOS(): Boolean = runCatching {
        val cls = Class.forName("android.os.SystemProperties")
        val getProperty: (String) -> String? = {
            cls.getMethod("get", String::class.java)(null, it) as? String
        }
        !(getProperty(KEY_MI_OS_VERSION_CODE).isNullOrBlank() ||
                getProperty(KEY_MI_OS_VERSION_NAME).isNullOrBlank() ||
                getProperty(KEY_MI_OS_VERSION_INCREMENTAL).isNullOrBlank())
    }.getOrDefault(false)

}