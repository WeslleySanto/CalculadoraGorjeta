package com.example.calculadoragorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextValorConta;
    private TextView textViewPorcentagemGorjeta;

    private SeekBar seekBarGorjeta;
    private double seekBarPorcentagem = 0;

    private TextView textViewValorGorjeta;
    private TextView textViewTotalConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextValorConta          = findViewById(R.id.editTextValorConta);
        textViewPorcentagemGorjeta  = findViewById(R.id.textViewPorcentagemGorjeta);
        seekBarGorjeta              = findViewById(R.id.seekBarGorjeta);
        textViewValorGorjeta        = findViewById(R.id.textViewValorGorjeta);
        textViewTotalConta          = findViewById(R.id.textViewTotalConta);

        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarPorcentagem = progress;

                textViewPorcentagemGorjeta.setText(Math.round( seekBarPorcentagem) + " %");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calcular(){
        String valorContaString = editTextValorConta.getText().toString();

        if(valorContaString == null || valorContaString.equals("")){
            Toast.makeText(getApplicationContext(), "Digite o valor da conta!", Toast.LENGTH_LONG).show();
        }else{
            //String para double
            double valorContaDouble = Double.parseDouble(valorContaString);
            //Calcula a gorjeta total em R$
            double valorGorjeta = valorContaDouble * (seekBarPorcentagem/100);
            double valorTotal = valorContaDouble + valorGorjeta;

            //Exibe a gorjeta e total
            textViewValorGorjeta.setText("R$ " + valorGorjeta);
            textViewTotalConta.setText("R$ " + valorTotal);
        }
    }
}
