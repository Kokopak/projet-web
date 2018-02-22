/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isis.cdg.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author corentin
 */
public class DAO {
    
    protected final DataSource dataSource;
    
    public DAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public int numberOfCustomers() {
        String sql = "select count(*) as number from CUSTOMER";
        
        int result = 0;
        
        try {    
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()) {
                result = rs.getInt("number");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
}
