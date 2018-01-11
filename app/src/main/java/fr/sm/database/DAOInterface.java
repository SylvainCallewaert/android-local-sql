package fr.sm.database;

import android.database.sqlite.SQLiteException;

import java.util.List;

import sm.fr.localsqlapp.model.Contact;

/**
 * Created by Formation on 11/01/2018.
 */

//Générique ou marqueur DTO qui permet d'insérer plusieurs génériques
interface DAOInterface <DTO>{
    DTO findOneById(int id) throws SQLiteException;

    List<DTO> findAll() throws SQLiteException;

    void deleteOneById(Long id) throws SQLiteException;

    void persist(DTO entity);
}
