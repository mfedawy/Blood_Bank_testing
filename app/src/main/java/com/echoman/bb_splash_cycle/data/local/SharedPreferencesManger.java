package com.echoman.bb_splash_cycle.data.local;

import android.app.Activity;
import android.content.SharedPreferences;

import com.echoman.bb_splash_cycle.data.model.client.ClientData;
import com.echoman.bb_splash_cycle.data.model.general.blood.BloodType;
import com.google.gson.Gson;

public class SharedPreferencesManger {

    public static SharedPreferences sharedPreferences = null;
    public static final String USER_DATA = "USER_DATA";
    public static final String REMEMBER_ME = "REMEMBER_ME";
    public static final String USER_PASSWORD = "USER_PASSWORD";
    public static final String USER_APITOKEN= "USER_APITOKEN";


    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "Blood", activity.MODE_PRIVATE);
        }
    }

    public static void saveData(Activity activity, String data_Key, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }
        public static void saveApi(Activity activity, String data_Key, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }
    public static void saveData(Activity activity, String data_Key, boolean data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }


    public static void saveData(Activity activity, String data_Key, int data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void saveData(Activity activity, String data_Key, Object data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String StringData = gson.toJson(data_Value);
            editor.putString(data_Key, StringData);
            editor.commit();
        }
    }

    public static String loadData(Activity activity, String data_Key) {
        setSharedPreferences(activity);

        return sharedPreferences.getString(data_Key, null);
    }

    public static BloodType loadbloodgroubs(Activity activity) {
        BloodType bloodType = null;

        Gson gson = new Gson();
        bloodType = gson.fromJson(loadData(activity, "bloodgroubs"), BloodType.class);

        return bloodType;
    }
    public static boolean loadBoolean(Activity activity, String data_Key) {
        setSharedPreferences(activity);

        return sharedPreferences.getBoolean(data_Key, false);
    }

    public static void saveUserData(Activity activity, ClientData clientData) {

        if (clientData.getApiToken() == null) {
            clientData.setApiToken(loadUserData(activity).getApiToken());
        }

       saveData(activity, USER_DATA, clientData);
    }

    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }
    public static ClientData loadUserData(Activity activity) {
        ClientData userData = null;

        Gson gson = new Gson();
        userData = gson.fromJson(loadData(activity, USER_DATA), ClientData.class);

        return userData;
    }

}
