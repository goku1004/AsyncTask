package com.example.goku.asynctask;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ScreenActivity extends AppCompatActivity {
    private List<File> mPhotos;
    GetImage getImage;

    private static final String TAG = "CHECK_LOAD_IMAGE";
    private static final String MESSAGE_PERMISSION_GRANTED = "Permission Granted";
    private static final String MESSAGE_PERMISSION_REVOKED = "Permission Revoked";
    private static final int SPAN_COUNT = 2;
    RecyclerView recyclerView;
    private static final int REQUEST_READ_STORAGE_PERMISSION_CODE = 3;

    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);
        init();
    }

    private void init() {
        recyclerView= findViewById(R.id.recycler_view);
        myAsyncTask=new MyAsyncTask(ScreenActivity.this);
        myAsyncTask.execute();

        StaggeredGridLayoutManager gridLayoutManager = new
                StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(getApplicationContext(),getData());
        recyclerView.setAdapter(recyclerAdapter);
    }


    public  boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,MESSAGE_PERMISSION_GRANTED);
                return true;
            } else {
                Log.v(TAG,MESSAGE_PERMISSION_REVOKED);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_STORAGE_PERMISSION_CODE);
                return false;
            }
        }
        else {
            Log.v(TAG,MESSAGE_PERMISSION_GRANTED);
            return true;
        }
    }

    public List<File> getData(){
        mPhotos = new ArrayList<>();
        getImage=new GetImage();

        if(checkPermission()){
            File[] images = getImage.getAllImage();
            for (File i : images){
                mPhotos.add(i);
                Log.d(TAG, i.toString());
            }
        }
        return mPhotos;
    }
}

