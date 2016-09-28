/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.wssysfin.Dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Kamilla Rodrigues
 */
public class Dao {
    
    
     private Connection conn;

    public Connection getConnection() {
        if (conn == null) {
            try {
                String servidor = "191.191.20.138:8082";
                String banco = "systm";
                String usuario = "root";
                String senha = "jfhmaster123";

                conn = DriverManager.getConnection(
                        "jdbc:mysql://" + servidor + "/" + banco, usuario, senha);
                if (conn != null) {
                    System.out.println("Conectou");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
 
        }
        return conn;
    }
}
