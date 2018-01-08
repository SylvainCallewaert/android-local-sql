package fr.sm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Formation on 08/01/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    //création des variables statiques :
    private static final String DATABASE_NAME = "contact_database";
    private static final int DATABASE_VERSION = 1;
    //création de la constante
    private static final String CONTACT_TABLE_SQL = "CREATE TABLE contacts(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "first_name TEXT," +
            "name TEXT NOT NULL," +
            "email TEXT NOT NULL )";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //création de la table
        sqLiteDatabase.execSQL(CONTACT_TABLE_SQL);//appel de la constante
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //destruction de la table si elle existe
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        //on recré la table
        this.onCreate(sqLiteDatabase);
    }
}
