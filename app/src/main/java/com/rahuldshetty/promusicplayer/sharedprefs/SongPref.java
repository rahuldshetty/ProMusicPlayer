package com.rahuldshetty.promusicplayer.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Output;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rahuldshetty.promusicplayer.models.Song;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SongPref {

    public static void setSongs(Context context, ArrayList<Song> list){
        SharedPreferences sharedPreferences=context.getSharedPreferences("FOLDERS",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        ArrayList<Song> withoutImage=new ArrayList<>(list);
        ArrayList<Bitmap> images = new ArrayList<>();

        for(int i=0;i<withoutImage.size();i++){
            Song song = withoutImage.get(i);
            images.add(song.getCover());
            song.setCover(null);
            withoutImage.set(i,song);
        }
        Gson gson = new Gson();
        String data=gson.toJson(list);
        editor.putString("SONGS",data);
        editor.commit();
       // System.out.println("SONGS WRITTEN WITHOUT IMAGE");
        try {
            File file = new File(context.getFilesDir(),"PMPCache");
           // System.out.println("Written succesful"+file.getAbsolutePath());
            if(!file.exists())
                file.mkdir();
            OutputStream out ;
            for(int i=0;i< images.size();i++){
                Bitmap image = images.get(i);
                if(image!=null) {
                    String fileName = new File (withoutImage.get(i).getPath()).getName().replace(".mp3","").replace(".3gp","").replace(".wav","");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    out = new FileOutputStream(file.getAbsolutePath() + "/" + fileName + ".jpg");
                    out.write(byteArray);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Exceptionm:"+e);
        }

       // System.out.println("Successful Write");
    }



    public static ArrayList<Song> getSongs(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("FOLDERS",Context.MODE_PRIVATE);
        ArrayList<Song> list=null;

        Type listType = new TypeToken<ArrayList<Song>>() {}.getType();
        Gson gson = new Gson();

        String data = sharedPreferences.getString("SONGS",null);

        File dirFile = new File(context.getFilesDir(),"PMPCache");

        if(data!=null ) {
            list = gson.fromJson(data, listType);
            if (list.size() != 0) {
                for (int i = 0; i < list.size(); i++) {
                    String fileName = new File(list.get(i).getPath()).getName().replace(".mp3", "").replace(".3gp", "").replace(".wav", "") + ".jpg";
                    if(new File(dirFile.getAbsolutePath()+"/"+fileName).exists()) {
                        Bitmap image = OpenBitmap(context, dirFile.getAbsolutePath()+"/"+ fileName);
                        Song song = list.get(i);
                        song.setCover(image);
                        list.set(i, song);
                    }
                }
                return list;
            }

        }
        return null;

    }

    public static Bitmap OpenBitmap(Context context,String name){
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            File file = new File(name);
            if(file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                return bitmap;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
