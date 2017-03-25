package br.com.heiderlopes.jokenpo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private TextView tvHeader, tvFooter;

    private Button btPlay, btRegras, btSair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btPlay = (Button) findViewById(R.id.btPlay);
        btSair = (Button) findViewById(R.id.btSair);
        btRegras = (Button) findViewById(R.id.btRegras);

        tvHeader = (TextView) findViewById(R.id.tvHeader);
        tvFooter = (TextView) findViewById(R.id.tvFooter);

        configuraFonte();
    }

    private void configuraFonte() {
        Typeface fonte = Typeface.createFromAsset(getAssets(), "fonts/game_over.ttf");

        btPlay.setTypeface(fonte);
        btSair.setTypeface(fonte);
        btRegras.setTypeface(fonte);

        tvHeader.setTypeface(fonte);
        tvFooter.setTypeface(fonte);

        tvHeader.setTextSize(130);
        tvFooter.setTextSize(35);
        btPlay.setTextSize(45);
        btSair.setTextSize(45);
        btRegras.setTextSize(45);
    }

    public void play(View v) {
        startActivity(new Intent(this, GameActivity.class));
    }

    public void sair(View v) {
        finish();
    }
}
