package com.rahuldshetty.promusicplayer.adapters;


import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rahuldshetty.promusicplayer.R;
import com.rahuldshetty.promusicplayer.models.Song;

import java.util.List;

public class SongAdapter2 extends RecyclerView.Adapter<SongAdapter2.MyViewHolder> {


    private List<Song> songList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.song_single,viewGroup,false);
        ViewGroup.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(params);
        return new MyViewHolder(itemView);
    }

    public SongAdapter2(List<Song> songList)
    {
        this.songList=songList;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            Song song = songList.get(i);
            myViewHolder.album.setText(song.getAlbum());
            myViewHolder.artist.setText(song.getArtist());
            myViewHolder.title.setText(song.getTitle());
            myViewHolder.cover.setImageBitmap(song.getCover());
    }

    @Override
    public long getItemId(int position) {
        return songList.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView artist,album,title;
        public ImageView cover,button;
        public MyViewHolder(View view) {
            super(view);
            artist = view.findViewById(R.id.single_song_artist);
            album=view.findViewById(R.id.single_song_album);
            title=view.findViewById(R.id.single_song_title);
            cover=view.findViewById(R.id.single_song_cover);
            button=view.findViewById(R.id.single_song_play);
        }
    }


}
