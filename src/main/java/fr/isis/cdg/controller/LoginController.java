/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isis.cdg.controller;

import fr.isis.cdg.model.DAO;
import fr.isis.cdg.model.DataSourceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author corentin
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userField = request.getParameter("userField");
        String mdpField = request.getParameter("mdpField");

        HttpSession session = request.getSession();

        DataSource myDataSource = DataSourceFactory.getDataSource();
        DAO myDAO = new DAO(myDataSource);
        HashMap<String, Integer> customers = myDAO.getCustomers();
        HashMap<String, String> customersWithName = myDAO.getCustomersWithName();

        if (customers.containsKey(userField)) {
            if (mdpField.equals(customers.get(userField).toString())) {
                session.setAttribute("email", userField);
                session.setAttribute("name", customersWithName.get(userField));
                session.setAttribute("userId", customers.get(userField));

                if (userField.equals("jumboeagle@example.com")) {
                    response.sendRedirect("admin");
                } else {
                    response.sendRedirect("customer");
                }

            } else {
                response.sendRedirect("index.html");
            }
        } else {
            response.sendRedirect("index.html");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
