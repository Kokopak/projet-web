/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isis.cdg.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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

            if (rs.next()) {
                result = rs.getInt("number");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    public float turnoverByCategory(String category, String dateDep, String dateArr) {
        String sql = "select * "
                + "from product " 
                + "inner join purchase_order using (product_id) "
                + "inner join product_code on (product_code = prod_code) "
                + "inner join discount_code using(discount_code) "
                + "where product_code=? "
                + "and sales_date between ? and ?";
        
        float somme = 0;
        
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, category);
            stmt.setDate(2, Date.valueOf(dateDep));
            stmt.setDate(3, Date.valueOf(dateArr));
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                float prix = (rs.getFloat("purchase_cost") - (rs.getFloat("purchase_cost") * rs.getFloat("rate") / 100)) * rs.getInt("quantity") + rs.getFloat("shipping_cost");
                somme += prix;
//System.out.println(rs.getString("product_code")+ " "+ rs.getInt("quantity") + " " + rs.getDate("sales_date"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return somme;
    }

}
