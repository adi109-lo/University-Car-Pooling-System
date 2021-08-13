
package Controller;

import DriverRideManagementModule.Driver;
import DriverRideManagementModule.Ride;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "ViewDriverApplicationDetailsController", urlPatterns = {"/ViewDriverApplicationDetailsController"})
public class ViewDriverApplicationDetailsController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Driver selectedDriver = Driver.getDriverInfo(request.getParameter("driverID"));
            RequestDispatcher rd = request.getRequestDispatcher("ViewDriverDetails.jsp");
            request.setAttribute("selected_driver", selectedDriver);
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ViewDriverApplicationDetailsController.class.getName()).log(Level.SEVERE, null, ex);
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
