package fr.isis.cdg.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
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
    
    public float turnoverTotal() {
        String sql = "select * "
                + "from product "
                + "inner join purchase_order using (product_id) "
                + "inner join product_code on (product_code = prod_code) "
                + "inner join discount_code using(discount_code) ";

        float somme = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
        
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                float prix = (rs.getFloat("purchase_cost") - (rs.getFloat("purchase_cost") * rs.getFloat("rate") / 100)) * rs.getInt("quantity") + rs.getFloat("shipping_cost");
                somme += prix;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return somme;
    }
    
     public HashMap<String, Integer> getCustomers() {
     String sql = "select email, customer_id from customer";

        HashMap<String, Integer> result = new HashMap<String, Integer>();

        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result.put(rs.getString("email"), rs.getInt("customer_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    public HashMap<String, Float> turnoverByCategory(String dateDep, String dateArr) {
     String sql = "select * "
                + "from product "
                + "inner join purchase_order using (product_id) "
                + "inner join product_code on (product_code = prod_code) "
                + "inner join discount_code using(discount_code) "
                + "where sales_date between ? and ?";

        HashMap<String, Float> result = new HashMap<String, Float>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(dateDep));
            stmt.setDate(2, Date.valueOf(dateArr));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                float prix = (rs.getFloat("purchase_cost") - (rs.getFloat("purchase_cost") * rs.getFloat("rate") / 100)) * rs.getInt("quantity") + rs.getFloat("shipping_cost");
                String productCode = rs.getString("product_code");
                if(result.containsKey(productCode)) {
                    result.put(productCode, result.get(productCode)+prix);
                }
                else {
                    result.put(productCode, prix);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public HashMap<String, Float> turnoverByState(String dateDep, String dateArr) {
        String sql = "select * "
                + "from product "
                + "inner join purchase_order using (product_id) "
                + "inner join product_code on (product_code = prod_code) "
                + "inner join discount_code using(discount_code) "
                + "inner join customer using(customer_id) "
                + "where sales_date between ? and ?";

        HashMap<String, Float> result = new HashMap<String, Float>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(dateDep));
            stmt.setDate(2, Date.valueOf(dateArr));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                float prix = (rs.getFloat("purchase_cost") - (rs.getFloat("purchase_cost") * rs.getFloat("rate") / 100)) * rs.getInt("quantity") + rs.getFloat("shipping_cost");
                String state = rs.getString("state");
                if(result.containsKey(state)) {
                    result.put(state, result.get(state)+prix);
                }
                else {
                    result.put(state, prix);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public HashMap<String, Float> turnoverByCustomer(String dateDep, String dateArr) {
        String sql = "select * "
                + "from product "
                + "inner join purchase_order using (product_id) "
                + "inner join product_code on (product_code = prod_code) "
                + "inner join discount_code using(discount_code) "
                + "inner join customer using(customer_id) "
                + "where sales_date between ? and ?";
        
        HashMap<String, Float> result = new HashMap<String, Float>();
        
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(dateDep));
            stmt.setDate(2, Date.valueOf(dateArr));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                float prix = (rs.getFloat("purchase_cost") - (rs.getFloat("purchase_cost") * rs.getFloat("rate") / 100)) * rs.getInt("quantity") + rs.getFloat("shipping_cost");
                String customerName = rs.getString("name");
                if(result.containsKey(customerName)) {
                    result.put(customerName, result.get(customerName)+prix);
                }
                else {
                    result.put(customerName, prix);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

}
