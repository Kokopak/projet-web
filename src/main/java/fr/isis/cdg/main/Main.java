/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isis.cdg.main;

import fr.isis.cdg.model.DAO;
import fr.isis.cdg.model.DataSourceFactory;
import java.sql.Date;
import javax.sql.DataSource;

/**
 *
 * @author corentin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static DAO myDAO;
    private static DataSource myDataSource;

    public static void main(String[] args) {
        // TODO code application logic here
        myDataSource = DataSourceFactory.getDataSource();
        myDAO = new DAO(myDataSource);

        System.out.println(myDAO.turnoverByCategory("2009-03-20", "2012-03-24"));
        System.out.println(myDAO.turnoverByState("2009-03-20", "2012-03-24") + " €");
        System.out.println(myDAO.turnoverByCustomer("2009-03-20", "2012-03-24") + " €");
    }

}
