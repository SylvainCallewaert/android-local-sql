package sm.fr.localsqlapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ActionBar;

import fr.sm.database.DatabaseHandler;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        //création d'une barre d'action retour
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public void onValid(View v){
        Button clickedButton = (Button) v;
        //récupération de la saisie de l'utilisateur
        String name = ((EditText) findViewById(R.id.editTextNom)).getText().toString();
        String firstName = ((EditText) findViewById(R.id.editTextPrenom)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        String isMessage = "";

        //Instanciation de la connexion à la base de données
        DatabaseHandler db = new DatabaseHandler(this);//responsable de la gestion des demandes CRUD
        //définition des données à insérer
        ContentValues insertValues = new ContentValues();
        insertValues.put("firstName",firstName);
        insertValues.put("name",name);
        insertValues.put("email", email);

        //insertion des données
        try {
            db.getWritableDatabase().insert("contacts", null, insertValues);
            isMessage = "Insertion réussie";
        } catch (SQLiteException ex) {
            Log.e("SQL EXCEPTION", ex.getMessage());
            isMessage = "Erreur insertion";
        }
        Toast.makeText(this, isMessage, Toast.LENGTH_LONG).show();
        //on efface les zones de saisies
        ((EditText) findViewById(R.id.editTextNom)).setText("");
        ((EditText) findViewById(R.id.editTextPrenom)).setText("");
        ((EditText) findViewById(R.id.editTextEmail)).setText("");
    }
}
