package com.example.catchkenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timetext;
    TextView skortext;
    int score;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timetext=(TextView) findViewById(R.id.TimeText);
        skortext = (TextView) findViewById(R.id.skorText);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageArray = new ImageView[]{
                imageView1,
                imageView3,
                imageView4,
                imageView2,
                imageView5,
                imageView6,
                imageView7,
                imageView8,
                imageView9
        };
        score = 0;
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                timetext.setText("Time: "+l/1000);
            }

            @Override
            public void onFinish() {
                timetext.setText("time off");
                handler.removeCallbacks(runnable);
                for(ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart atacağız
                        //aşağıdaki kodlar şu anki activityi yeniden başlatırlar
                        Intent intent = getIntent();
                        finish();//bu kod güncel aktiviteyi arkada çalışmasın diye bitiriyor
                        startActivity(intent);//güncel aktiviteyi tekrar çalıştırıyor.
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Game Over!",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
            }
        }.start();
        hideImages();

    }
    public void increaseSkor(View view){
        score++;
        skortext.setText("Score : "+score);
    }
    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random rnd = new Random();
                int i = rnd.nextInt(8);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);

            }
        };
      handler.post(runnable);
    }
}