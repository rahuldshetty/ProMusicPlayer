package com.rahuldshetty.promusicplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.rahuldshetty.promusicplayer.activity.SettingsActivity;
import com.rahuldshetty.promusicplayer.home_fragments.AlbumFragment;
import com.rahuldshetty.promusicplayer.home_fragments.ArtistFragment;
import com.rahuldshetty.promusicplayer.home_fragments.GenresFragment;
import com.rahuldshetty.promusicplayer.home_fragments.PlaylistFragment;
import com.rahuldshetty.promusicplayer.home_fragments.SongFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private FrameLayout frameLayout;

    public static MemoryClass memoryClass;

    public static Context mainContext;
    public static Activity mainActivity;

    private SongFragment songFragment;
    private AlbumFragment albumFragment;
    private ArtistFragment artistFragment;
    private GenresFragment genresFragment;
    private PlaylistFragment playlistFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabs);
        toolbar=findViewById(R.id.main_toolbar);
        frameLayout=findViewById(R.id.main_frame);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        mainContext=MainActivity.this;
        mainActivity=this;

        songFragment=new SongFragment();
        albumFragment=new AlbumFragment();
        artistFragment=new ArtistFragment();
        genresFragment=new GenresFragment();
        playlistFragment = new PlaylistFragment();

        loadFragment(songFragment);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case 0:
                        loadFragment(songFragment);
                        break;

                    case 1:
                        loadFragment(albumFragment);
                        break;

                    case 2:
                        loadFragment(artistFragment);
                        break;

                    case 3:
                        loadFragment(playlistFragment);
                        break;

                    case 4:
                        loadFragment(genresFragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        
    }


    public void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame,fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.main_toolbar_search:

                return true;

            case R.id.main_toolbar_settings:
                Intent activity=new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(activity);
                return true;


            default:return false;
        }
    }
}
