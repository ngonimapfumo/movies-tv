package zw.co.nm.moviedb.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

object PrefUtils {



    private const val PREF_KEY = "prefs"

    private fun getPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)

    private fun getPrefsEditor(context: Context): SharedPreferences.Editor =
        getPrefs(context).edit()

    @SuppressLint("ApplySharedPref")
    fun removeKey(context: Context, key: String) {
        val editor = getPrefsEditor(context)
        editor.remove(key)
        editor.commit()
    }
}