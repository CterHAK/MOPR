package com.example.firebase;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


import java.util.ArrayList;
import java.util.List;

import API.APIService;
import Adapter.VideoAdapter;
import Model.MessageVideoModel;
import Model.VideoModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoShortActivity extends AppCompatActivity {
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
        APIService.serviceapi.getVideos().enqueue(new Callback<MessageVideoModel>() {
            @Override
            public void onResponse(Call<MessageVideoModel> call, Response<MessageVideoModel> response) {
                list = response.body().getResult();

                videoAdapter = new VideoAdapter(getApplicationContext(),list);
                viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                viewPager2.setAdapter(videoAdapter);
            }

            @Override
            public void onFailure(Call<MessageVideoModel> call, Throwable t) {
                Log.d("TAG",t.getMessage());
            }
        });
    }
}
