
package Controller;

import DriverRideManagementModule.Driver;
import UserManagementModule.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;

@WebServlet(name = "ViewDriverRequestsController", urlPatterns = {"/ViewDriverRequestsController"})
public class ViewDriverRequestsController extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            ArrayList<Driver> driverList = Admin.retriveDriverRequests();
            RequestDispatcher rd = request.getRequestDispatcher("ViewDriverApplications.jsp");
            request.setAttribute("DriverList", driverList);
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ViewDriverRequestsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
