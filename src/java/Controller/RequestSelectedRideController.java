
package Controller;

import DriverRideManagementModule.Ride;
import PassengerRideManagementModule.Passenger;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;


@WebServlet(name = "RequestSelectedRideController", urlPatterns = {"/RequestSelectedRideController"})
public class RequestSelectedRideController extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String outcomeMessage = null;
        HttpSession session = request.getSession();
        Passenger p = (Passenger) session.getAttribute("passenger");
        
        if (p.getEmailID().equals(request.getParameter("driver_id"))){
            outcomeMessage = "Error! Can't request own ride";
        }
        else{
            boolean addSuccess = Ride.createRequest(Integer.parseInt(request.getParameter("selected_ride")), p.getEmailID(), request.getParameter("pickup_location"), request.getParameter("dropoff_location"));
            if (addSuccess) {
                outcomeMessage = "Successfully requested ride!";
            } else {
                outcomeMessage = "Error! Unable to request ride";
            }
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("RideRequestConfirmation.jsp");
        request.setAttribute("outcome_message", outcomeMessage);
        rd.forward(request, response);
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
