package com.rahuldshetty.promusicplayer.loaders;

import android.content.Context;

import com.rahuldshetty.promusicplayer.MainActivity;
import com.rahuldshetty.promusicplayer.helper.SongHelper;
import com.rahuldshetty.promusicplayer.models.Song;
import com.rahuldshetty.promusicplayer.sharedprefs.Folders;
import com.rahuldshetty.promusicplayer.sharedprefs.SongPref;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongLoader {


    public static ArrayList<File> listf(String directoryName,ArrayList<String> songs) {
        File directory = new File(directoryName+"/");
        ArrayList<File> resultList = new ArrayList<File>();

        File[] fList = directory.listFiles();


        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile() && (file.getName().contains(".mp3") || file.getName().contains(".wav") || file.getName().contains(".3gp") )) {
                songs.add(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                 ArrayList<File> temp = listf(file.getAbsolutePath(),songs);
            }
        }
        return resultList;
    }

    public static ArrayList<Song> getAllSongs(Context ctx){
        ArrayList<Song> actualSongs;

        if(MainActivity.memoryClass.mainSongs!=null && MainActivity.memoryClass.mainSongs.size()!=0){
            return MainActivity.memoryClass.mainSongs;
        }

        actualSongs= SongPref.getSongs(MainActivity.mainContext);



        if(actualSongs==null) {

            ArrayList<String> directories = Folders.getFolders(ctx);
            ArrayList<String> songs = new ArrayList<String>();

            for (String dir : directories) {
                ArrayList<File> files = listf(dir, songs);
            }

            actualSongs = new ArrayList<Song>();

            for (String path : songs) {
                Song song = SongHelper.getSong(path);
                actualSongs.add(song);
            }

            SongPref.setSongs(MainActivity.mainContext,actualSongs);
        }
        MainActivity.memoryClass.mainSongs = actualSongs;
        return actualSongs;
    }

}
