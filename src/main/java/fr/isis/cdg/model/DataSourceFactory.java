/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isis.cdg.model;

import javax.sql.DataSource;

/**
 *
 * @author corentin
 */
public class DataSourceFactory {

    public static DataSource getDataSource() {
        DataSource result;

        org.apache.derby.jdbc.ClientDataSource ds = new org.apache.derby.jdbc.ClientDataSource();
        ds.setDatabaseName("sample");
        ds.setUser("app");
        ds.setPassword("app");

        ds.setServerName("localhost");

        ds.setPortNumber(1527);

        return ds;
    }

}
