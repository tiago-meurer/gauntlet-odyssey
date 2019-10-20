package com.gilvanstudios.gauntletodyssey;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class EndGameActivity extends AppCompatActivity {

    private Button startGameAgain;
    private TextView DisplayScore;
    private TextView DisplayHighScore;
    private String score;
    private String highScore;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        //TextView scoreLabel = (TextView) findViewById(R.id.score_display);
        //TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);

        //score = getIntent().getExtras().get("score").toString();
        //highScore = getIntent().getExtras().get("HIGH_SCORE").toString();

        startGameAgain = (Button) findViewById(R.id.try_again);
        DisplayScore = (TextView) findViewById(R.id.score_display);
        DisplayHighScore = (TextView) findViewById(R.id.highScoreLabel);

        int score = getIntent().getIntExtra("SCORE",0);
        DisplayScore.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);


        if (score > highScore){

            DisplayHighScore.setText("High Score: " + highScore);

            //Save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        } else {

            DisplayHighScore.setText("High Score: "+ highScore);
        }

        //Main AD
        interstitial = new InterstitialAd(this);

        interstitial.setAdUnitId("ca-app-pub-4957836647276069/7935259485");

        //Create request
        AdRequest adRequest = new AdRequest.Builder().build();

        //Loading start
        interstitial.loadAd(adRequest);

        //Once loaded, display ad:
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded(){
                displayInterstitial();
            }
        });

        startGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(EndGameActivity.this, com.gilvanstudios.gauntletodyssey.MainActivity.class);
                startActivity(mainIntent);
            }
        });

        DisplayScore.setText("Final Energy: " + score);
        DisplayHighScore.setText("High Score: "+ highScore);
    }

    public void displayInterstitial(){

        if(interstitial.isLoaded()){
            interstitial.show();
        }

    }
}
