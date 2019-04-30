package com.rahuldshetty.promusicplayer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rahuldshetty.promusicplayer.MainActivity;
import com.rahuldshetty.promusicplayer.R;
import com.rahuldshetty.promusicplayer.models.Song;

public class SongPlayActivity extends AppCompatActivity {

    private Song song;

    private TextView title,artist,album,startTime,endTime;
    private ImageView mainBtn,skip,prev,fav,cover;
    private SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_play);

        song = MainActivity.memoryClass.currentSong;

        title=findViewById(R.id.play_title);
        artist=findViewById(R.id.play_artist);
        album=findViewById(R.id.play_album);
        startTime=findViewById(R.id.play_starttime);
        endTime=findViewById(R.id.play_endtime);
        mainBtn=findViewById(R.id.play_mainBtn);
        prev = findViewById(R.id.play_prevBtn);
        skip=findViewById(R.id.play_nextBtn);
        fav=findViewById(R.id.play_fav_button);
        cover=findViewById(R.id.play_imageView);
        seekBar=findViewById(R.id.play_seekbar);


        title.setText(song.getTitle());
        artist.setText(song.getArtist());
        album.setText(song.getAlbum());
        if(song.getCover()!=null)
            cover.setImageBitmap(song.getCover());
        else
            cover.setImageResource(R.drawable.ic_music_icon);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }
}
