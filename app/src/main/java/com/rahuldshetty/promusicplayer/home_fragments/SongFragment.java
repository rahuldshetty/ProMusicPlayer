package com.rahuldshetty.promusicplayer.home_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahuldshetty.promusicplayer.MainActivity;
import com.rahuldshetty.promusicplayer.R;
import com.rahuldshetty.promusicplayer.adapters.SongAdapter2;
import com.rahuldshetty.promusicplayer.loaders.SongLoader;
import com.rahuldshetty.promusicplayer.models.Song;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends Fragment {

    private RecyclerView songRecyclerView;
    private  View mView;



    public SongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_song, container, false);


        songRecyclerView=mView.findViewById(R.id.song_songlistview);

        ArrayList<Song> songList= SongLoader.getAllSongs(MainActivity.mainContext);
        System.out.println("SIZE:" + songList.size());




        SongAdapter2 songAdapter = new SongAdapter2(songList);
        songAdapter.setHasStableIds(true);

        songRecyclerView.setHasFixedSize(true);
        songRecyclerView.setItemViewCacheSize(20);
        songRecyclerView.setDrawingCacheEnabled(true);
        songRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        songRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        songRecyclerView.setAdapter(songAdapter);




        return mView;
    }

}
