package com.ilham.mediaplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoActivity extends AppCompatActivity {

    VideoView videoView;
    MediaController mediaController;
    Button btPlay, btStop, btKembali;
    TextView tvJudul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        videoView=findViewById(R.id.vv_video);
        btPlay=findViewById(R.id.bt_play);
        tvJudul=findViewById(R.id.tv_judul);
        btStop=findViewById(R.id.bt_stop);
        btKembali=findViewById(R.id.bt_kembali);

        mediaController=new MediaController(this);

        //Agar ketika tombol play ditekan akan memainkan video
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVideo();
                btStop.setVisibility(View.VISIBLE);
                btPlay.setVisibility(View.GONE);
            }
        });

        //Agar ketika tombol stop ditekan akan menstopkan video
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopVideo();
            }
        });

        //Agar ketika tombol kemali ditekan akan menuju ke Dashboard
        btKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    void stopVideo(){
        videoView.stopPlayback();
        btStop.setVisibility(View.GONE);
        btPlay.setVisibility(View.VISIBLE);
    }

    void startVideo(){
        //URL menentukan lokasi file video
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tokdalang);
        videoView.setVideoURI(uri);
        //Setting MediaController
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        //menjalankan video
        videoView.start();
        tvJudul.setText("Atok Dalang");
    }
}