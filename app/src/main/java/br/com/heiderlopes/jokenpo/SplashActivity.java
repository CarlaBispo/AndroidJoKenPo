package br.com.heiderlopes.jokenpo;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;
    private ImageView ivSplash;
    private TextView tvSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvSplash = (TextView) findViewById(R.id.tvSplash);
        ivSplash = (ImageView) findViewById(R.id.ivSplash);

        configuraFonte();
        carregar();
    }

    private void playAudio() {
        MediaPlayer player = MediaPlayer.create(this, R.raw.jokenpo);
        player.start();
    }

    private void configuraFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/game_over.ttf");
        tvSplash.setTypeface(fonte);
    }

    private void carregar() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash);
        anim.reset();

        if (ivSplash != null) {
            ivSplash.clearAnimation();
            ivSplash.startAnimation(anim);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playAudio();
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,
                        MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
