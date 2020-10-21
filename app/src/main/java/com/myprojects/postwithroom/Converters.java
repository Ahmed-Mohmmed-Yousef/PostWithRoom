package com.myprojects.postwithroom;

import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class Converters {

    @TypeConverter
    public String fromUserToString(User user) {
        return new Gson().toJson(user);
    }

    @TypeConverter
    public User fromStringToUser(String string) {
        return new Gson().fromJson(string, User.class);
    }
}
