package com.example.firebase;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import API.APIService;
import Adapter.VideoAdapter;
import Model.MessageVideoModel;
import Model.VideoModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cloudinary extends AppCompatActivity {
    ViewPager2 viewPager2;
    VideoAdapter videoAdapter;
    List<VideoModel> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_short);
        viewPager2 = findViewById(R.id.vpager);
        list =new ArrayList<>();
        getVideos();
    }
    private void getVideos(){
        list.add(new VideoModel(1,"Manh Ba","youtube","https://res.cloudinary.com/dkt7i3nib/video/upload/rnwucgwz3yhtxons8kin.mp4"));
        videoAdapter = new VideoAdapter(getApplicationContext(),list);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager2.setAdapter(videoAdapter);
    }
}
