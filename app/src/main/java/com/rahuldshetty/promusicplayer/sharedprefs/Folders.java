package com.rahuldshetty.promusicplayer.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Folders {



    public static void updateFolders(Context context, List<String> list){
        SharedPreferences sharedPreferences=context.getSharedPreferences("FOLDERS",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String data=gson.toJson(list);
        editor.putString("LIST",data);
        editor.commit();
    }

    public static ArrayList<String> getFolders(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("FOLDERS",Context.MODE_PRIVATE);
        ArrayList<String> list=null;

        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        Gson gson = new Gson();

        String data = sharedPreferences.getString("LIST",null);
        if(data!=null)
        {
            list = gson.fromJson(data,listType);
            return list;
        }
        else
            return new ArrayList<String>();

    }


}
