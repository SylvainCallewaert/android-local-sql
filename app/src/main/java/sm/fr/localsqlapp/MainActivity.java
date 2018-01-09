package sm.fr.localsqlapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.sm.database.DatabaseHandler;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView contactListView;
    private List<Map<String,String>> contactList;
    private Integer selectedIndex;
    private Map<String,String> selectedPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //référence au widget ListView sur le layout
        contactListView = findViewById(R.id.contactListView);
        //récupération de la liste des contacts
        contactList = this.getAllContacts();
        //Création d'un contactArrayAdapter
        ContactArrayAdapter contactAdapter = new ContactArrayAdapter(this,contactList);
        //Définition de l'adapter de notre listView
        contactListView.setAdapter(contactAdapter);

        //Définition d'un écouteur
        contactListView.setOnItemClickListener(this);

    }

    /**
     * Lancement de l'activité formulaire au clic sur un bouton
     * @param view
     */

    public void onAddContact(View view){
        if (view == findViewById(R.id.buttonAddContact)) { // facultaif car onAddContact est utilisé pour un seul bouton
            Intent FormIntent = new Intent(this, FormActivity.class);
            startActivity(FormIntent);
        }

    }
    //récupérer les enregistrements de la base de données contacts
    private List<Map<String,String>> getAllContacts(){
        //Instanciation de la connexion à la base de données
        DatabaseHandler db = new DatabaseHandler(this);
        //Exécute une requete de sélection
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT name, first_name, email FROM contacts", null);
        //Instanciation de la liste qui recevra les données
        List<Map<String,String>> contactList = new ArrayList<>();


        //Parcourir les résultats de la requête (parcours du curseur)
        while (cursor.moveToNext()){
            Map<String,String> contactCols = new HashMap<>();
            //Remplissage du tableau associatif en fonction des données du curseur
            contactCols.put("name",cursor.getString(0));
            contactCols.put("first_name",cursor.getString(1));
            contactCols.put("email",cursor.getString(2));

            //Ajout du map à la liste
            contactList.add(contactCols);
        }


        return contactList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        this.selectedIndex = position;
        this.selectedPerson = contactList.get(position);
        Toast.makeText(this, "Ligne "+ position + "cliquée", Toast.LENGTH_SHORT).show();
    }
}
