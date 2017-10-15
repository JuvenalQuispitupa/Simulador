package com.juvenalquisptitupayupa.simulador;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Dao.PatronDao;
import Model.MPatron;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormularioPatron extends AppCompatActivity {

    @BindView(R.id.et_tipo)
    EditText etTipo;
    @BindView(R.id.et_secuencia)
    EditText etSecuencia;
    @BindView(R.id.et_valor)
    EditText etValor;
    @BindView(R.id.btn_accion)
    Button btnAccion;

    private static final String TAG ="DetallePatron";
    private MPatron patron;
    private PatronDao patronDao;
    private boolean esActualizable=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_patron);
        ButterKnife.bind(this);

        patron = getIntent().getParcelableExtra("patron");
        patronDao = new PatronDao(this);
        if(patron !=null){
            etTipo.setText(patron.getTipo());
            etSecuencia.setText(patron.getSecuencia().toString());
            etValor.setText(patron.getValor().toString());
            btnAccion.setText("EDITAR*");
            esActualizable=true;
        }
        else
        {
            btnAccion.setText("AGREGAR**");
        }
    }

    public static void start(Context context, MPatron patron) {
        Intent starter = new Intent(context, FormularioPatron.class);
        starter.putExtra("patron",patron);
        context.startActivity(starter);
    }

    @OnClick(R.id.btn_accion)
    public void onViewClicked() {
        if(esActualizable){
            ActualizarPatron();
        }
        else
        {
            AgregarPatron();
        }
    }
    private void AgregarPatron() {
        if (validarcamposvacios()!="") {
            Toast.makeText(this, validarcamposvacios(), Toast.LENGTH_SHORT).show();
            return;
        }
        MPatron patron = new MPatron();
        patron.setTipo(etTipo.getText().toString());
        patron.setSecuencia(Integer.parseInt(etSecuencia.getText().toString()));
        patron.setValor((Integer.parseInt(etValor.getText().toString())));


            long exito = patronDao.insert(patron);
            if (exito > 0) {
                Toast.makeText(this, "Patron creado", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error al crear", Toast.LENGTH_SHORT).show();
            }
    }

    private void ActualizarPatron() {
        if (validarcamposvacios()!="") {
            Toast.makeText(this, validarcamposvacios(), Toast.LENGTH_SHORT).show();
            return;
        }
        patron.setTipo(etTipo.getText().toString());
        patron.setSecuencia(Integer.parseInt(etSecuencia.getText().toString()));
        patron.setValor(Integer.parseInt(etValor.getText().toString()));


            long exito = patronDao.update(patron);
            if (exito > 0) {
                Toast.makeText(this, "Patron actualizado", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
            }
    }
    private String validarcamposvacios() {
        String mensajeErro="";
        if(etTipo.getText().toString().equals(""))
            mensajeErro+="Ingrese: "+MPatron.TIPO_FIELD+". ";
        if(etSecuencia.getText().toString().equals(""))
            mensajeErro+="Ingrese: "+MPatron.SECUENCIA_FIELD+". ";
        if(etValor.getText().toString().equals(""))
            mensajeErro+="Ingrese: "+MPatron.VALOR_FIELD+". ";//"\n";
        return mensajeErro;
    }
}
