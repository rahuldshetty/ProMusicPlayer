package com.rahuldshetty.promusicplayer.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;

import com.rahuldshetty.promusicplayer.models.Song;

import java.io.File;

public class SongHelper {

    public static Song getSong(String path){
        MediaMetadataRetriever meta = new MediaMetadataRetriever();
        meta.setDataSource(path);
        Song song = new Song();

        String album = meta.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        String artist = meta.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        String artist2 = meta.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST);
        String genre = meta.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
        byte[] data = meta.getEmbeddedPicture();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap cover=null;

        if(data!=null) {
            cover = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        }

        song.setAlbum(album);
        if(artist!=null)
            song.setArtist(artist);
        else
            song.setArtist(artist2);
        song.setGenre(genre);
        song.setCover(cover);
        song.setPath(path);
        String title = new File(path).getName().replace(".mp3","");
        song.setTitle(title);

        return song;
    }

}
