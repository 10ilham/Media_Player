package com.ilham.mediaplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    TextView tvNama;
    Button btPlay, btPause, btStop, btNext, btPrevous, btKembali;
    ImageView imageView;

    ArrayList<DataMusic>daftarMusic=new ArrayList<>();
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mediaPlayer=null;
        daftarMusic.add(new DataMusic(R.raw.gak_ada_waktu_beib, "Ghea Youbi-Gak Ada Waktu Beib", R.drawable.ghea_youbi));
        daftarMusic.add(new DataMusic(R.raw.sebelas_duabelas, "Nella Kharisma-Sebelas Dua Belas", R.drawable.sebelas_duabelas));
        daftarMusic.add(new DataMusic(R.raw.happy_nation, "Ace Of Base-Happy Nation", R.drawable.happy_nation));

        btPlay=findViewById(R.id.bt_play);
        btPause=findViewById(R.id.bt_pause);
        btStop=findViewById(R.id.bt_stop);
        tvNama=findViewById(R.id.tv_nama);
        btNext=findViewById(R.id.bt_next);
        btPrevous=findViewById(R.id.bt_previous);
        btKembali=findViewById(R.id.bt_kembali);
        imageView=findViewById(R.id.iv_banner_lagu);

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { playMusic(); }
        });
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { stopMusic(); }
        });
        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { pauseMusic(); }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { nextMusic(); }
        });
        btPrevous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { previousMusic(); }
        });
        btKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopMusic();
    }

    void playMusic(){
        if (mediaPlayer==null){
            mediaPlayer=MediaPlayer.create(this, daftarMusic.get(currentIndex).get_file());
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) { stopMusic(); }
        });
        mediaPlayer.start();
        tvNama.setText(daftarMusic.get(currentIndex).get_judul());
        // Untuk mengatur gambar yang sedang diputar
        int imageResource = daftarMusic.get(currentIndex).get_gambar();
        if (imageResource != 0) {
            // Jika ada gambar yang terkait, tampilkan di ImageView
            imageView.setImageResource(imageResource);
        } else {
            // Jika tidak ada gambar yang terkait, Anda bisa mengatur gambar default atau melakukan tindakan yang sesuai
            imageView.setImageResource(R.drawable.ghea_youbi);
        }

        // Menampilkan Notifikasi berdasarkan lagu saat ini
        int position = currentIndex + 1; // Position in the list (1-based index)
        String message = "Playing song " + position + " of " + daftarMusic.size();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    void pauseMusic(){
        if (mediaPlayer!=null){
            mediaPlayer.pause();
        }
    }
    void stopMusic() {
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }

    void nextMusic(){
        stopMusic();
        currentIndex = (currentIndex + 1) % daftarMusic.size();
        playMusic();
    }
    void previousMusic(){
        stopMusic();
        currentIndex = (currentIndex - 1 + daftarMusic.size()) % daftarMusic.size();
        playMusic();
    }
}