package Controller;

import DriverRideManagementModule.Ride;
import DriverRideManagementModule.SingleRide;
import DriverRideManagementModule.WeeklyRide;
import PassengerRideManagementModule.Passenger;
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


@WebServlet(name = "ViewPassengerConfirmedRidesController", urlPatterns = {"/ViewPassengerConfirmedRidesController"})
public class ViewPassengerConfirmedRidesController extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<SingleRide> singleRides;
        ArrayList<WeeklyRide> weeklyRides;
        HttpSession session = request.getSession();
        Passenger p = (Passenger) session.getAttribute("passenger"); //Get the Driver logged in Right now
        if (p != null){
            singleRides = p.viewSingleConfirmedRides();
            weeklyRides = p.viewWeeklyConfirmedRides();


            RequestDispatcher rd = request.getRequestDispatcher("ViewPassengerConfirmedRides.jsp");

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
