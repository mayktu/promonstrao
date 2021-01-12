package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class GenericDAO {
    
    public GenericDAO() {
        try {
            
        	/* Setup Banco de dados MySQL */
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	System.out.print("ok?\n");
        	
        } catch (ClassNotFoundException e) {
        	System.out.print("Erro no GenericDAO: " + e);
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        /* Conexão banco de dados MySQL */
    	
    	String url = "jdbc:mysql://localhost:3306/ProMonstrao?useTimezone=true&serverTimezone=UTC";

    	return DriverManager.getConnection(url, "root", "root");
    }
}
