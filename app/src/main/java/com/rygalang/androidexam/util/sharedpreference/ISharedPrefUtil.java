package com.rygalang.androidexam.util.sharedpreference;

import java.util.Set;

/**
 * Created by Computer3 on 12/28/2017.
 */

public interface ISharedPrefUtil {
    String getString(String key);

    long getLong(String key);

    int getInt(String key);

    float getFloat(String key);

    boolean getBoolean(String key);

    Set<String> getStringSet(String key);

    void setString(String key, String value);

    void setLong(String key, long value);

    void setInt(String key, int value);

    void setFloat(String key, float value);

    void setBoolean(String key, boolean value);

    void setStringSet(String key, Set<String> value);

    void clear();

    void removeKey(String key);
}
