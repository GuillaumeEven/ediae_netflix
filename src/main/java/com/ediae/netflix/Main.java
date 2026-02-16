package com.ediae.netflix;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import java.sql.Connection;
import com.ediae.netflix.utils.DBManager;
import com.ediae.netflix.utils.ConnexionTmdb;
import models.Filmografia;
import com.ediae.netflix.daos.FilmografiaDao;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        System.out.println("---------------------------------------------");
        System.out.println("Testing API connection:");
        ArrayList<Filmografia> new_filmos = new ArrayList<>();

        try {
            new_filmos = ConnexionTmdb.searchMovie("Inception");
        } catch (Exception e) {
            System.err.println("Erreur lors de la connexion à l'API TMDB :");
            e.printStackTrace();
        }
        for (Filmografia filmografia : new_filmos) {
            System.out.println("Title: " + filmografia.getTitulo() + ", Release Date: " + filmografia.getFecha_estreno() + ", Overview: " + filmografia.getSinopsis());
        }


        System.out.println("Database connection test:");
        DBManager dbManager = new DBManager();
        Connection connection = dbManager.getConnection();

        if (connection != null) {
            System.out.println("Connexion à la base de données réussie !");
        } else {
            System.out.println("Erreur lors de la connexion à la base de données.");
        }

        // create filmografiaDao
        FilmografiaDao filmografiaDao = new FilmografiaDao(connection);

        // save new_filmos to database
        try {
            for (Filmografia filmografia : new_filmos) {
                filmografiaDao.insert(filmografia);
            }
            System.out.println("" + new_filmos.size() + " films enregistrés dans la base de données.");
        } catch (java.sql.SQLException e) {
            System.err.println("Erreur lors de la sauvegarde des films : " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("---------------------------------------------");

        System.out.println("Bienvenue sur le projet Netflix ! Ici, vous pouvez gérer efficacement votre catalogue de films, séries et utilisateurs. Profitez de l'exploration et de la création de contenu pour votre plateforme de streaming !");

        System.out.println("---------------------------------------------");

        DBManager.disconnect(connection);
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
