package fr.isis.cdg.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

        HashMap<String, Integer> result = new HashMap<>();

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

    public HashMap<String, String> getCustomersWithName() {
        String sql = "select email, name from customer";

        HashMap<String, String> result = new HashMap<>();

        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result.put(rs.getString("email"), rs.getString("name"));
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
                if (result.containsKey(productCode)) {
                    result.put(productCode, result.get(productCode) + prix);
                } else {
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
                if (result.containsKey(state)) {
                    result.put(state, result.get(state) + prix);
                } else {
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
                if (result.containsKey(customerName)) {
                    result.put(customerName, result.get(customerName) + prix);
                } else {
                    result.put(customerName, prix);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public float priceOfOrder(int orderNum) {
        String sql = "select * "
                + "from product "
                + "inner join purchase_order using (product_id) "
                + "inner join product_code on (product_code = prod_code) "
                + "inner join discount_code using(discount_code) "
                + "where order_num = ?";

        float res = 0;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, orderNum);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                res = (rs.getFloat("purchase_cost") - (rs.getFloat("purchase_cost") * rs.getFloat("rate") / 100)) * rs.getInt("quantity") + rs.getFloat("shipping_cost");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    public ArrayList<String[]> getPurchaseOfCustomer(int customerId) {
        String sql = "select * "
                + "from purchase_order "
                + "inner join product using (product_id) "
                + "where customer_id = ?";

        ArrayList<String[]> lPO = new ArrayList<String[]>();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String[] purchaseOrder = new String[5];
                purchaseOrder[0] = Integer.toString(rs.getInt("quantity"));
                purchaseOrder[1] = rs.getString("description");
                purchaseOrder[2] = Float.toString(priceOfOrder(rs.getInt("order_num")));
                purchaseOrder[3] = rs.getDate("sales_date").toString();
                purchaseOrder[4] = Integer.toString(rs.getInt("order_num"));

                lPO.add(purchaseOrder);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lPO;
    }

    public void updateQuantityFor(int orderNum, int newQuantity) {
        String sql = "update purchase_order "
                + "set quantity = ? "
                + "where order_num = ?";

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, orderNum);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap<String, Integer> listProducts() {
        String sql = "select * "
                + "from product";

        HashMap<String, Integer> result = new HashMap<String, Integer>();

        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result.put(rs.getString("description"), rs.getInt("product_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    public int getMaxOrderNum() {
        String sql = "select max(order_num) as maximum from purchase_order";
        int max = 0;
        
        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                max = rs.getInt("maximum");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return max;
    }
    
    public void insertPurchaseOrder(int customerId, int productId, int quantity, String salesDate) {
        String sql = "insert into purchase_order values (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, getMaxOrderNum() + 1);
            stmt.setInt(2, customerId);
            stmt.setInt(3, productId);
            stmt.setInt(4, quantity);
            stmt.setInt(5, 250);
            stmt.setDate(6, Date.valueOf(salesDate));
            stmt.setDate(7, Date.valueOf(salesDate));
            stmt.setString(8, "Poney Express");
            
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void deletePurchaseOrder(int orderNum) {
        String sql = "delete from purchase_order where order_num = ?";
        
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, orderNum);
            
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

}
