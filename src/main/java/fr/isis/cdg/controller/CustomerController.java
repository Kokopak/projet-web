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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class CustomerController extends HttpServlet {

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

        HttpSession session = request.getSession(false);
        DataSource myDataSource = DataSourceFactory.getDataSource();
        DAO myDAO = new DAO(myDataSource);

        ArrayList<String[]> lPO = myDAO.getPurchaseOfCustomer((int) session.getAttribute("userId"));
        HashMap<String, Integer> products = myDAO.listProducts();

        request.setAttribute("list_purchase_order", lPO);
        request.setAttribute("list_products", products);

        this.getServletContext().getRequestDispatcher("/WEB-INF/customer.jsp").forward(request, response);
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
        processRequest(request, response);
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
        
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");

        DataSource myDataSource = DataSourceFactory.getDataSource();
        DAO myDAO = new DAO(myDataSource);

        if (action.equals("update_quantity")) {
            int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));
            int orderNum = Integer.parseInt(request.getParameter("orderNum"));
            myDAO.updateQuantityFor(orderNum, newQuantity);
        }
        else if (action.equals("add_purchase")) {
            int productId = Integer.parseInt(request.getParameter("produit"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            DateFormat toFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            
            myDAO.insertPurchaseOrder((int) session.getAttribute("userId"), productId, quantity, toFormat.format(new Date()));
            
            response.sendRedirect("customer");
        }
        else if (action.equals("delete_purchase")) {
            myDAO.deletePurchaseOrder(Integer.parseInt(request.getParameter("orderNum")));
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
