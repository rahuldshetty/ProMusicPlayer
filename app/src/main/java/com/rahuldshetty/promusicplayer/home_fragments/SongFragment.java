package com.rahuldshetty.promusicplayer.home_fragments;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahuldshetty.promusicplayer.MainActivity;
import com.rahuldshetty.promusicplayer.R;
import com.rahuldshetty.promusicplayer.adapters.SongAdapter2;
import com.rahuldshetty.promusicplayer.helper.EndlessRecyclerOnScrollListener;
import com.rahuldshetty.promusicplayer.loaders.SongLoader;
import com.rahuldshetty.promusicplayer.models.Song;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends Fragment {

    private RecyclerView songRecyclerView;
    private  View mView;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int visibleThreshold = 10;

    Parcelable recyclerState;

    private ArrayList<Song> shownSongs=new ArrayList<Song>();


    private boolean loadingForFirstTime=true;

    public SongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_song, container, false);


        if(loadingForFirstTime) {

            songRecyclerView = mView.findViewById(R.id.song_songlistview);

            final ArrayList<Song> songList = SongLoader.getAllSongs(MainActivity.mainContext);
            Collections.sort(songList);
            //System.out.println("SIZE:" + songList.size());


            shownSongs.clear();

            if (songList.size() > 0)
                shownSongs.addAll(songList.subList(0, visibleThreshold));


            final SongAdapter2 songAdapter = new SongAdapter2(shownSongs);
            songAdapter.setHasStableIds(true);


            songRecyclerView.setHasFixedSize(true);
            songRecyclerView.setItemViewCacheSize(20);
            songRecyclerView.setDrawingCacheEnabled(true);
            songRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            songRecyclerView.setItemAnimator(new DefaultItemAnimator());

            final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());

            songRecyclerView.setLayoutManager(mLayoutManager);
            songRecyclerView.setAdapter(songAdapter);


            songRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
                @Override
                public void onLoadMore() {
                    int index = shownSongs.size();
                    int end = (index + visibleThreshold >= songList.size()) ? songList.size() : index + visibleThreshold;
                    shownSongs.addAll(songList.subList(index, end));
                    songAdapter.notifyItemRangeInserted(index, end);
                }
            });

        }



        return mView;
    }



    @Override
    public void onPause() {
        super.onPause();
        recyclerState = songRecyclerView.getLayoutManager().onSaveInstanceState();
        System.out.println("PAUSED");
    }

    @Override
    public void onResume() {
        super.onResume();
        songRecyclerView.getLayoutManager().onRestoreInstanceState(recyclerState );
        System.out.println("RESUMED");
    }
}
