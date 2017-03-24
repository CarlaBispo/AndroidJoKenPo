package br.com.heiderlopes.jokenpo;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Random numeroAleatorio;

    private final int PEDRA = 1;
    private final int PAPEL = 2;
    private final int TESOURA = 3;

    private Button btPedra;
    private Button btPapel;
    private Button btTesoura;

    private ImageView ivJogadaPlayer;
    private ImageView ivJogadaPC;

    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        numeroAleatorio = new Random();

        ivJogadaPlayer = (ImageView) findViewById(R.id.ivJogadaPlayer);
        ivJogadaPC = (ImageView) findViewById(R.id.ivJogadaPC);
        tvResultado = (TextView) findViewById(R.id.tvResultado);

        btPedra = (Button) findViewById(R.id.btPedra);
        btPedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivJogadaPlayer.setImageDrawable(ContextCompat.getDrawable(GameActivity.this, R.drawable.pedra));
                realizaJogada(PEDRA);
            }
        });

        btPapel = (Button) findViewById(R.id.btPapel);
        btPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivJogadaPlayer.setImageDrawable(ContextCompat.getDrawable(GameActivity.this, R.drawable.papel));
                realizaJogada(PAPEL);
            }
        });

        btTesoura = (Button) findViewById(R.id.btTesoura);
        btTesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivJogadaPlayer.setImageDrawable(ContextCompat.getDrawable(GameActivity.this, R.drawable.tesoura));
                realizaJogada(TESOURA);
            }
        });
    }

    private void realizaJogada(int jogadaPlayer) {

        int jogadaPC = numeroAleatorio.nextInt(3) + 1;

        switch (jogadaPC) {
            case PEDRA:
                ivJogadaPC.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pedra));
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
                ivJogadaPC.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.papel));
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
                ivJogadaPC.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.tesoura));
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
    }

    private void perdeu() {
        tvResultado.setText(getString(R.string.perdeu));
        tvResultado.setTextColor(ContextCompat.getColor(this, R.color.derrota));
    }

    private void empatou() {
        tvResultado.setText(getString(R.string.empatou));
        tvResultado.setTextColor(ContextCompat.getColor(this, R.color.empate));
    }
}