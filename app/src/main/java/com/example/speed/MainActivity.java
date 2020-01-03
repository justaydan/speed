package com.example.speed;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity  {
    Random rand = new Random();
    Integer[] arr = new Integer[]{R.drawable.cap, R.drawable.tony, R.drawable.spidey};
    Drawable drawable1, drawable2,drawable3;
    TextView score;
    Button yes,no;
    int score2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yes = (Button) findViewById(R.id.ys);
        no = (Button) findViewById(R.id.no);
        score = (TextView) findViewById(R.id.score);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawable2 = findViewById(R.id.img).getBackground();//spi
                if (drawable2.getConstantState().equals(drawable1.getConstantState())) {
                    score2+=10;


                } else {
                    score2-=10;
                }
                drawable3 = getResources().getDrawable(arr[rand.nextInt(3)]);
                findViewById(R.id.img).setBackground(drawable3);
                drawable1 = drawable2;
                score.setText("" + score2);


            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawable2 = findViewById(R.id.img).getBackground();//spi
                if (drawable2.getConstantState().equals(drawable1.getConstantState())) {
                    score2-=10;


                } else {//spi tony cap
                    score2+=10;
                }
                score.setText("" + score2);
                drawable3 = getResources().getDrawable(arr[rand.nextInt(3)]);
                findViewById(R.id.img).setBackground(drawable3);
                drawable1 = drawable2;
            }
        });


    }

    public void start(final View view) {


        findViewById(R.id.start).setVisibility(view.GONE);
        findViewById(R.id.txt).setVisibility(view.GONE);
        findViewById(R.id.img).setVisibility(view.VISIBLE);
        findViewById(R.id.ys).setVisibility(view.VISIBLE);
        findViewById(R.id.no).setVisibility(view.VISIBLE);

        findViewById(R.id.score).setVisibility(view.VISIBLE);
        findViewById(R.id.score1).setVisibility(view.VISIBLE);
        findViewById(R.id.time1).setVisibility(view.VISIBLE);

        drawable1 = getResources().getDrawable(arr[rand.nextInt(3)]);
        findViewById(R.id.img).setBackground(drawable1);


        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                TextView a= findViewById(R.id.mytxt);
                a.setText(""+millisUntilFinished / 1000);

            }

            public void onFinish() {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(MainActivity.this);
                builder.setMessage("your score "+score2);
                builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        score2=0;
                        score.setText("" + score2);
                        start();
                    }
                });
                builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       System.exit(0);
                    }
                });
                builder.show();

            }

        }.start();
    }
}
