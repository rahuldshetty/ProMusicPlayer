package com.rahuldshetty.promusicplayer.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.v4.provider.DocumentFile;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.rahuldshetty.promusicplayer.R;
import com.rahuldshetty.promusicplayer.helper.Paths;
import com.rahuldshetty.promusicplayer.sharedprefs.Folders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_OPEN_DIRECTORY = 1;

    private ListView listView;
    private Button saveBtn;
    private ImageButton addBtn;

    private List<String> lists;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        lists = Folders.getFolders(SettingsActivity.this);

        adapter = new ArrayAdapter<String>(this,R.layout.simplerow,lists);
        listView=findViewById(R.id.settings_folders);
        addBtn=findViewById(R.id.settings_add_folder_btn);
        saveBtn = findViewById(R.id.settings_save);


        listView.setAdapter(adapter);



        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                startActivityForResult(intent,REQUEST_CODE_OPEN_DIRECTORY);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Folders.updateFolders(SettingsActivity.this,lists);
                Toast.makeText(SettingsActivity.this,"Saved successfully",Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OPEN_DIRECTORY && resultCode == SettingsActivity.RESULT_OK) {
            Uri uri = data.getData();
            String path = Paths.getFullPathFromTreeUri(uri,this);
            lists.add(path);
            adapter.notifyDataSetChanged();
        }
    }




}
