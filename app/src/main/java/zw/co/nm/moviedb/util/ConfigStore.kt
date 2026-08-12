package zw.co.nm.moviedb.util

import android.content.Context
import android.content.SharedPreferences

object ConfigStore {

    const val SEARCH_CONFIG_KEY: String = "search_config"
    private const val PREF_KEY = "prefs"

    private fun getPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)

    private fun getPrefsEditor(context: Context): SharedPreferences.Editor =
        getPrefs(context).edit()

    fun saveBoolConfig(context: Context, key: String, boolean: Boolean) {
        val editor = getPrefsEditor(context)
        editor.putBoolean(key, boolean)
        editor.commit()
    }

    fun getBool(context: Context, key: String): Boolean {
        val prefs = getPrefs(context)
        return prefs.getBoolean(key, true)
    }

    fun saveIntConfig(context: Context, key: String, intId: Int) {
        val editor = getPrefsEditor(context)
        editor.putInt(key, intId)
        editor.commit()
    }

    fun saveStringConfig(context: Context, key: String, strId: String) {
        val editor = getPrefsEditor(context)
        editor.putString(key, strId)
        editor.commit()
    }

    fun getStringLang(context: Context, key: String): String? {
        val prefs = getPrefs(context)
        return prefs.getString(key, "en-US")
    }

    fun getString(context: Context, key: String): String? {
        val prefs = getPrefs(context)
        return prefs.getString(key, null)
    }

    fun clearConfig(context: Context, key: String) {
        val editor = getPrefsEditor(context)
        editor.remove(key)
        editor.commit()
    }

    fun getInt(context: Context, key: String): Int {
        val prefs = getPrefs(context)
        return prefs.getInt(key, 0)
    }

    fun getThemeConfig(context: Context, key: String): Int {
        val prefs = getPrefs(context)
        return prefs.getInt(key, 2)
    }

    fun isLoggedIn(context: Context): Boolean {
        val sessionId = getString(context, Constants.SESSION_ID)
        val accountId = getInt(context, Constants.ACCOUNT_ID)
        return !sessionId.isNullOrBlank() && accountId != 0
    }
}
