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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("index.html");
        } else if (session.getAttribute("email").equals("jumboeagle@example.com")) {
            String action = request.getParameter("action");
            //System.out.println(request.getParameter("date_dep"));
            
            String date_dep = "2009-03-20";
            String date_fin = "2012-03-24";
            if (request.getParameter("date_dep") != null && request.getParameter("date_fin") != null) {
                if (!request.getParameter("date_dep").equals("") && !request.getParameter("date_fin").equals("")) {
                    DateFormat fromFormat = new SimpleDateFormat("dd/MM/yyyy");
                    DateFormat toFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        date_dep = toFormat.format(fromFormat.parse(request.getParameter("date_dep")));
                        date_fin = toFormat.format(fromFormat.parse(request.getParameter("date_fin")));
                    } catch (ParseException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            System.out.println(date_dep);
            
            DataSource myDataSource = DataSourceFactory.getDataSource();
            DAO myDAO = new DAO(myDataSource);
            if (action == null) {
                request.setAttribute("total_turnover", myDAO.turnoverTotal());
                request.setAttribute("total_customers", myDAO.numberOfCustomers());

                this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

            } else if (action.equals("turnover_by_category")) {
                HashMap<String, Float> tBC = myDAO.turnoverByCategory(date_dep, date_fin);
                request.setAttribute("tBC", tBC);
                this.getServletContext().getRequestDispatcher("/WEB-INF/turnoverbycategory.jsp").forward(request, response);
            } else if (action.equals("turnover_by_customer")) {
                HashMap<String, Float> tBC = myDAO.turnoverByCustomer(date_dep, date_fin);
                request.setAttribute("tBC", tBC);
                this.getServletContext().getRequestDispatcher("/WEB-INF/turnoverbycustomer.jsp").forward(request, response);

            } else if (action.equals("turnover_by_state")) {
                HashMap<String, Float> tBC = myDAO.turnoverByState(date_dep, date_fin);
                request.setAttribute("tBC", tBC);
                this.getServletContext().getRequestDispatcher("/WEB-INF/turnoverbystate.jsp").forward(request, response);

            }
        } else {
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
