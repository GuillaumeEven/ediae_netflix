/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ediae.netflix;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        System.out.println("Test de conexión a la base de datos:");
        DBManager dbManager = new DBManager();
        if (dbManager.getConnection() != null) {
            System.out.println("¡Conexión exitosa a la base de datos!");
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }

        System.out.println("---------------------------------------------");

        System.out.println("¡Bienvenido al proyecto de Netflix! Aquí podrás gestionar tu catálogo de películas, series y usuarios de manera eficiente. ¡Disfruta explorando y creando contenido para tu plataforma de streaming!");

        System.out.println("---------------------------------------------");
    }
}
