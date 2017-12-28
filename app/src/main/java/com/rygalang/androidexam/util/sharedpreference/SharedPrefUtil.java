package com.rygalang.androidexam.util.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

/**
 * Created by Computer3 on 12/28/2017.
 */

public class SharedPrefUtil implements ISharedPrefUtil {

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = SharedPrefUtil.class.getName();

    @Inject
    public SharedPrefUtil(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    @Override
    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0L);
    }

    @Override
    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    @Override
    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0f);
    }

    @Override
    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    @Override
    public Set<String> getStringSet(String key) {
        return sharedPreferences.getStringSet(key, new HashSet<>());
    }

    @Override
    public void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    @Override
    public void setLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    @Override
    public void setInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    @Override
    public void setFloat(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    @Override
    public void setBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    @Override
    public void setStringSet(String key, Set<String> value) {
        sharedPreferences.edit().putStringSet(key, value).apply();
    }

    @Override
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    @Override
    public void removeKey(String key) {
        sharedPreferences.edit().remove(key).apply();
    }
}
