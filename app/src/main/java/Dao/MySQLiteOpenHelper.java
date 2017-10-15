package Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Model.MPatron;

/**
 * Created by root on 14/10/17.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper{

    public static final String NOMBRE_BD="mydb.db";
    public static final Integer VERSION_BD = 1;
    //private static final String TAG="MySQLiteOpenHelper";
    private static StringBuilder sbCreateTableSQL = new StringBuilder()
            .append("CREATE TABLE "+ MPatron.TABLE_NAME+ " (")
            .append(MPatron.ID_FIELD+" INTEGER PRIMARY KEY autoincrement, ")
            .append(MPatron.TIPO_FIELD+" TEXT, ")
            .append(MPatron.SECUENCIA_FIELD+" TEXT, ")
            .append(MPatron.VALOR_FIELD+" INTEGER ) ");

    public MySQLiteOpenHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Log.d(TAG,"crear BD, tabla. onCreate(SQLiteDatabase db)");
        db.execSQL(sbCreateTableSQL.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
