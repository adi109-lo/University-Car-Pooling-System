
package Controller;

import DriverRideManagementModule.Driver;
import DriverRideManagementModule.SingleRide;
import DriverRideManagementModule.WeeklyRide;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(name = "ViewOfferedRidesController", urlPatterns = {"/ViewOfferedRidesController"})
public class ViewOfferedRidesController extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Driver d = (Driver) session.getAttribute("driver"); //Get the Driver logged in Right now
        if (d != null) {
            ArrayList<SingleRide> singleRides = d.viewSingleOfferedRides();
            ArrayList<WeeklyRide> weeklyRides = d.viewWeeklyOfferedRides();
            RequestDispatcher rd = request.getRequestDispatcher("ViewOfferedRides.jsp");
            request.setAttribute("singleRides", singleRides); 
            request.setAttribute("weeklyRides", weeklyRides);
            rd.forward(request, response);
            
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
