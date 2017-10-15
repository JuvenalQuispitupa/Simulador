package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.MPatron;

/**
 * Created by root on 14/10/17.
 */

public class PatronDao {

    private MySQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public PatronDao(Context context) {
        this.sqLiteOpenHelper = new MySQLiteOpenHelper(context);
        this.sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    public long insert(MPatron p)
    {
        ContentValues valores = new ContentValues();

        valores.put(MPatron.TIPO_FIELD,p.getTipo());
        valores.put(MPatron.SECUENCIA_FIELD,p.getSecuencia().toString());
        valores.put(MPatron.VALOR_FIELD,p.getValor().toString());

        //retorna numero de filas insetadas ó -1 si ocurre algun error
        long result;
        try {
            openDB();
            result = sqLiteDatabase.insert(MPatron.TABLE_NAME, null, valores);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            result=0;
        }
        return result;
    }
    public long update(MPatron p)
    {
        ContentValues valores = new ContentValues();
        //ID
        valores.put(MPatron.TIPO_FIELD,p.getTipo());
        valores.put(MPatron.SECUENCIA_FIELD,p.getSecuencia());
        valores.put(MPatron.VALOR_FIELD,p.getValor());

        String whereClause = MPatron.ID_FIELD+"=?";
        String[] whereArgs = {String.valueOf(p.getId())};

        //retorna la cantidad de registroa modificados, 0 si nada se actualiza
        long result;
        try {
            openDB();
            result = sqLiteDatabase.update(MPatron.TABLE_NAME,valores,whereClause,whereArgs);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            result=0;
        }
        return result;
    }
    public long delete(int idPatron)
    {
        String whereClause = MPatron.ID_FIELD+"=?";
        String[] whereArgs = {String.valueOf(idPatron) };
        //retorna la cantidad de registros eliminados, 0 si nada se elimina
        long result;
        try {
            openDB();
            result = sqLiteDatabase.delete(MPatron.TABLE_NAME,whereClause,whereArgs);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            result=0;
        }
        return result;

    }
    public List<MPatron> getPatrones()
    {
        List<MPatron> patrones = new ArrayList<>();

        String[] columns = {
                MPatron.ID_FIELD,
                MPatron.TIPO_FIELD,
                MPatron.SECUENCIA_FIELD,
                MPatron.VALOR_FIELD
        };

        openDB();
        Cursor cursor = sqLiteDatabase.query(MPatron.TABLE_NAME,columns,null,null,null,null,MPatron.TIPO_FIELD+" , "+MPatron.SECUENCIA_FIELD);
        while ( cursor.moveToNext()){
            patrones.add(convertCursorToModel(cursor));
        }
        cursor.close();
        return patrones;
    }

    public List<MPatron> getPatrones(String tipo)
    {
        List<MPatron> patrones = new ArrayList<>();

        String[] columns = {
                MPatron.ID_FIELD,
                MPatron.TIPO_FIELD,
                MPatron.SECUENCIA_FIELD,
                MPatron.VALOR_FIELD
        };

        if(tipo.contains("AB"))
            tipo="ABAJO";
        if(tipo.contains("AR"))
            tipo="ARRIBA";
        if(tipo.contains("CL"))
            tipo="CLICK";
        if(tipo.contains("DE"))
            tipo="DERECHA";
        if(tipo.contains("IZ"))
            tipo="IZQUIERDA";

        openDB();
        Cursor cursor = sqLiteDatabase.query(MPatron.TABLE_NAME,columns,MPatron.TIPO_FIELD+" =  '"+tipo+"' ",null,null,null,MPatron.TIPO_FIELD+" , "+MPatron.SECUENCIA_FIELD);
        while ( cursor.moveToNext()){
            patrones.add(convertCursorToModel(cursor));
        }
        cursor.close();
        return patrones;
    }

    private MPatron convertCursorToModel(Cursor cursor){
        MPatron patron = new MPatron();
        patron.setId(cursor.getInt(cursor.getColumnIndex(MPatron.ID_FIELD)));
        patron.setTipo(cursor.getString(cursor.getColumnIndex(MPatron.TIPO_FIELD)));
        patron.setSecuencia(cursor.getInt(cursor.getColumnIndex(MPatron.SECUENCIA_FIELD)));
        patron.setValor(cursor.getInt(cursor.getColumnIndex(MPatron.VALOR_FIELD)));
        return patron;
    }
    public void openDB()
    {
        if(!sqLiteDatabase.isOpen()){
            this.sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
        }
    }
    public void closeDB()
    {
        sqLiteDatabase.close();
    }

    /*private List<MPatron> convertCursorToList(Cursor c)
    {
        //creando la lista de patrones
        List<PatronModel> arrList = new ArrayList<>();

        //recorrer el cursor
        if(c.moveToFirst())
        {
            //crea patron
            PatronModel model=new PatronModel();
            //mapear
            model.setId(c.getInt(c.getColumnIndex(PatronModel.ID_FIELD)));
            model.setTipo(c.getString(c.getColumnIndex(PatronModel.TIPO_FIELD)));
            model.setSecuencia(c.getColumnIndex(PatronModel.SECUENCIA_FIELD));
            model.setValor(c.getInt(c.getColumnIndex(PatronModel.VALOR_FIELD)));
            //agregar item
            arrList.add(model);
        }while (c.moveToNext());

        return arrList;
    }*/
}
