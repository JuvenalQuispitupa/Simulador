package com.juvenalquisptitupayupa.simulador;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import Adapter.PatronAdapter;
import Dao.PatronDao;
import Model.MPatron;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
    /*paso 6. implementar*/
        implements PatronAdapter.OnRviItemClickListener
{


    private static final String TAG ="MainActivity";
    @BindView(R.id.rv_patrones)
    RecyclerView rvPatrones;
    private PatronDao patronDao;
    private List<MPatron> patrones;
    private PatronAdapter adapter;
    @BindView(R.id.btn_ver)
    Button btnVer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        patronDao = new PatronDao(this);
        adapter = new PatronAdapter(this);

        Random ran = new Random();
        patrones = patronDao.getPatrones();
        MPatron pat = new MPatron();
        if( patrones.size() == 0)
        {
            for(int i=1;i<=5;i++){

                pat.setTipo("ABAJO");
                pat.setSecuencia(i);
                pat.setValor(ran.nextInt(100));
                patronDao.insert(pat);

                pat.setTipo("ARRIBA");
                pat.setSecuencia(i);
                pat.setValor(ran.nextInt(100));
                patronDao.insert(pat);

                pat.setTipo("CLICK");
                pat.setSecuencia(i);
                pat.setValor(ran.nextInt(100));
                patronDao.insert(pat);

                pat.setTipo("DERECHA");
                pat.setSecuencia(i);
                pat.setValor(ran.nextInt(100));
                patronDao.insert(pat);

                pat.setTipo("IZQUIERDA");
                pat.setSecuencia(i);
                pat.setValor(ran.nextInt(100));
                patronDao.insert(pat);
            }
        }



        rvPatrones.setLayoutManager(new LinearLayoutManager(this));
        rvPatrones.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rvPatrones.setAdapter(adapter);
    }

    /** Paso 8, implementar los metodos.* */
    @Override
    public void onItemClick(MPatron patron) {
        Log.v(TAG,"UD ha clickeado el item");
        long success = patronDao.delete(patron.getId());
        if(success>0){
            patrones = patronDao.getPatrones();
            adapter.addList(patrones);
        }
        else
        {
            Toast.makeText(this,"No se pudo eliminar",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBtnEditarClick(MPatron patron) {
        FormularioPatron.start(this, patron);
    }

    @OnClick(R.id.btn_agregar)
    public void onViewClicked() {
        FormularioPatron.start(this, null);
    }

    @Override protected void onResume() {
        super.onResume();
        patrones = patronDao.getPatrones();
        adapter.addList(patrones);
    }

    @OnClick(R.id.btn_ver)
    public void onViewVerClicked() {
        Ver.start(this);
    }
}
