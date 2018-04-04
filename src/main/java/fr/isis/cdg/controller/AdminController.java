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
public class AdminController extends HttpServlet {

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
        
        HttpSession session = request.getSession(false);
        
        if(session == null || session.getAttribute("email") == null) {
            response.sendRedirect("index.html");
        }
        else if (session.getAttribute("email").equals("jumboeagle@example.com")){
            String action = request.getParameter("action");
            DataSource myDataSource = DataSourceFactory.getDataSource();
            DAO myDAO = new DAO(myDataSource);
            if(action == null) {
                request.setAttribute("total_turnover", myDAO.turnoverTotal());
                request.setAttribute("total_customers", myDAO.numberOfCustomers());

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

            }
            else if(action.equals("turnover_by_category")) {
                HashMap<String, Float> tBC = myDAO.turnoverByCategory("2009-03-20", "2012-03-24");
                request.setAttribute("tBC", tBC);
                this.getServletContext().getRequestDispatcher("/WEB-INF/turnoverbycategory.jsp").forward(request, response);
            }
            else if(action.equals("turnover_by_customer")) {
                HashMap<String, Float> tBC = myDAO.turnoverByCustomer("2009-03-20", "2012-03-24");
                request.setAttribute("tBC", tBC);
                this.getServletContext().getRequestDispatcher("/WEB-INF/turnoverbycustomer.jsp").forward(request, response);

            }
            else if(action.equals("turnover_by_state")) {
                HashMap<String, Float> tBC = myDAO.turnoverByState("2009-03-20", "2012-03-24");
                request.setAttribute("tBC", tBC);
                this.getServletContext().getRequestDispatcher("/WEB-INF/turnoverbystate.jsp").forward(request, response);

            }   
        }
        else {
            response.sendRedirect("customer");
        }
       
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
        processRequest(request, response);
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
