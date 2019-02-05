package br.com.rafaelleme.senai.calculoangulo;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtValorAngulo;
    RadioButton rbSeno, rbCosseno, rbTang;
    Button btCalcular;
    private int opcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtValorAngulo =  findViewById(R.id.edtValorAngulo);
        rbSeno = findViewById(R.id.rbSeno);
        rbCosseno = findViewById(R.id.rbCosseno);
        rbTang = findViewById(R.id.rbTang);
        btCalcular = findViewById(R.id.btCalcular);

        rbSeno.setOnClickListener(this);
        rbCosseno.setOnClickListener(this);
        rbTang.setOnClickListener(this);
        btCalcular.setOnClickListener(this);
    }

    public double calcularSeno(double angulo){
        return Math.sin(Math.toRadians(angulo));
    }

    public double calcularCosseno(double angulo){
        return Math.cos(Math.toRadians(angulo));
    }

    public double calcularTangente(double angulo){
        return Math.tan(Math.toRadians(angulo));
    }

    public void calcular(){

        AlertDialog digAlerta;
        double angulo, valorCalculado;
        String titulo;
        angulo = Double.valueOf(edtValorAngulo.getText().toString());

        if(opcao == 1){
            titulo = "Cálculo de Seno";
            valorCalculado = calcularSeno(angulo);
        }else if(opcao == 2){
            titulo = "Cálculo de Cosseno";
            valorCalculado = calcularCosseno(angulo);
        }else{
            titulo = "Cálculo de Tangente";
            valorCalculado = calcularTangente(angulo);
        }

        digAlerta = new AlertDialog.Builder( this).create();
        digAlerta.setTitle(titulo);
        //String valorFormatado = String.format("0.2%", valorCalculado);
        DecimalFormat format = new DecimalFormat( "0.0002");
        String valorFormatado = format.format(valorCalculado);
        digAlerta.setMessage(String.valueOf(valorFormatado));
        digAlerta.show();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rbSeno:
                opcao = 1;
                rbCosseno.setChecked(false);
                rbTang.setChecked(false);
                break;

            case R.id.rbCosseno:
                opcao = 2;
                rbSeno.setChecked(false);
                rbTang.setChecked(false);
                break;

            case R.id.rbTang:
                opcao = 3;
                rbSeno.setChecked(false);
                rbCosseno.setChecked(false);
                break;

            case R.id.btCalcular:
                if(validar()){
                    calcular();
                }
                break;
        }
    }

    private boolean validar(){
        if(edtValorAngulo.getText().toString().equals("")){
            edtValorAngulo.setError("Campo obrigatório!");
            return false;
        }else{
            double valorAngulo = Double.valueOf(edtValorAngulo.getText().toString());
            if((valorAngulo >= 0) &&  (valorAngulo <= 360) ){
                return true;
            }else{
                edtValorAngulo.setError("Valores abaixo de 0 e acima de 360 não serão computados.");
                return false;
            }
        }

    }
}
