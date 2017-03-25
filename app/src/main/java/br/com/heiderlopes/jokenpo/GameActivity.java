package br.com.heiderlopes.jokenpo;

import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Random numeroAleatorio;

    private final int PEDRA = 1;
    private final int PAPEL = 2;
    private final int TESOURA = 3;

    private int totalVitorias = 0;
    private int totalEmpates = 0;
    private int totalDerrotas = 0;

    private TextView tvVitorias;
    private TextView tvDerrotas;
    private TextView tvEmpates;

    private Button btPedra;
    private Button btPapel;
    private Button btTesoura;

    private ImageView ivJogadaPlayer;
    private ImageView ivJogadaPC;

    private TextView tvResultado;
    private ImageView ivResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        numeroAleatorio = new Random();

        tvVitorias = (TextView) findViewById(R.id.tvVitorias);
        tvDerrotas = (TextView) findViewById(R.id.tvDerrotas);
        tvEmpates = (TextView) findViewById(R.id.tvEmpates);

        ivJogadaPlayer = (ImageView) findViewById(R.id.ivJogadaPlayer);
        ivJogadaPC = (ImageView) findViewById(R.id.ivJogadaPC);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        ivResultado = (ImageView) findViewById(R.id.ivResultado);

        btPedra = (Button) findViewById(R.id.btPedra);
        btPedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivJogadaPlayer.setImageDrawable(ContextCompat.getDrawable(GameActivity.this, R.drawable.m_pedra));
                realizaJogada(PEDRA);
            }
        });

        btPapel = (Button) findViewById(R.id.btPapel);
        btPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivJogadaPlayer.setImageDrawable(ContextCompat.getDrawable(GameActivity.this, R.drawable.m_papel));
                realizaJogada(PAPEL);
            }
        });

        btTesoura = (Button) findViewById(R.id.btTesoura);
        btTesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivJogadaPlayer.setImageDrawable(ContextCompat.getDrawable(GameActivity.this, R.drawable.m_tesoura));
                realizaJogada(TESOURA);
            }
        });
    }

    private void realizaJogada(int jogadaPlayer) {

        int jogadaPC = numeroAleatorio.nextInt(3) + 1;

        MediaPlayer player = MediaPlayer.create(this, R.raw.jokenpo);
        player.start();

        switch (jogadaPC) {
            case PEDRA:
                ivJogadaPC.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.m_pedra));
                switch (jogadaPlayer) {
                    case PAPEL:
                        venceu();
                        break;
                    case PEDRA:
                        empatou();
                        break;
                    case TESOURA:
                        perdeu();
                        break;
                }
                break;

            case PAPEL:
                ivJogadaPC.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.m_papel));
                switch (jogadaPlayer) {
                    case PAPEL:
                        empatou();
                        break;
                    case PEDRA:
                        perdeu();
                        break;
                    case TESOURA:
                        venceu();
                        break;
                }
                break;

            case TESOURA:
                ivJogadaPC.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.m_tesoura));
                switch (jogadaPlayer) {
                    case PAPEL:
                        perdeu();
                        break;
                    case PEDRA:
                        venceu();
                        break;
                    case TESOURA:
                        empatou();
                        break;
                }
                break;
        }
    }

    private void venceu() {
        tvResultado.setText(getString(R.string.venceu));
        tvResultado.setTextColor(ContextCompat.getColor(this, R.color.vitoria));
        ivResultado.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.vitoria));
        totalVitorias++;
        tvVitorias.setText(String.valueOf(totalVitorias));
        animarScore(tvVitorias);
    }

    private void perdeu() {
        tvResultado.setText(getString(R.string.perdeu));
        tvResultado.setTextColor(ContextCompat.getColor(this, R.color.derrota));
        ivResultado.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.derrota));
        totalDerrotas++;
        tvDerrotas.setText(String.valueOf(totalDerrotas));
        animarScore(tvDerrotas);
    }

    private void empatou() {
        tvResultado.setText(getString(R.string.empatou));
        tvResultado.setTextColor(ContextCompat.getColor(this, R.color.empate));
        ivResultado.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.empate));
        totalEmpates++;
        tvEmpates.setText(String.valueOf(totalEmpates));
        animarScore(tvEmpates);
    }

    private void animarScore(View v) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.ponto_animacao);
        anim.reset();

        if (v != null) {
            v.clearAnimation();
            v.startAnimation(anim);
        }
    }
}