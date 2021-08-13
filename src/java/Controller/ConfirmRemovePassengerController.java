 
package Controller;

import DriverRideManagementModule.Driver;
import PassengerRideManagementModule.Passenger;
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


@WebServlet(name = "ConfirmRemovePassengerController", urlPatterns = {"/ConfirmRemovePassengerController"})
public class ConfirmRemovePassengerController extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Passenger_ID = request.getParameter("passenger_id");
        String Ride_ID = request.getParameter("rideid");
        String Choice = request.getParameter("choice");
        
        HttpSession session = request.getSession();
        Driver d = (Driver) session.getAttribute("driver"); //Get the Driver logged in Right now
        if (d != null) {
            
            if ("Confirm".equals(Choice))
            {
                String Confirmation = d.ConfirmPassengerRequest(Ride_ID, Passenger_ID);
                if("Confirmed".equals(Confirmation))
                {
                String Message = "Passenger Confirmed Successfully!";
                RequestDispatcher rd = request.getRequestDispatcher("RemoveRegisteredPassenger.jsp");
                request.setAttribute("Message", Message);
                request.setAttribute("Ride_ID", Ride_ID);
                request.setAttribute("Passenger_ID", Passenger_ID);
                rd.forward(request, response);
                }
                else if ("No Seats".equals(Confirmation))
                {
                    String Message = "No Seats Available in Requested Ride!";
                    RequestDispatcher rd = request.getRequestDispatcher("PassengerRemovedOrNotConfirmed.jsp");
                    request.setAttribute("Message", Message);
                    request.setAttribute("Ride_ID", Ride_ID);
                    rd.forward(request, response);
                }
            }
            else if("RemoveNew".equals(Choice))
            {
                d.RemovePassenger(Ride_ID, Passenger_ID);
                String Message = "Passenger Request Rejected Successfully!";
                RequestDispatcher rd = request.getRequestDispatcher("PassengerRemovedOrNotConfirmed.jsp");
                request.setAttribute("Message", Message);
                request.setAttribute("Ride_ID", Ride_ID);
                rd.forward(request, response);
            }
            else if("RemoveRegistered".equals(Choice))
            {
                d.RemovePassenger(Ride_ID, Passenger_ID);
                String Message = "Passenger Removed Successfully!";
                RequestDispatcher rd = request.getRequestDispatcher("PassengerRemovedOrNotConfirmed.jsp");
                request.setAttribute("Message", Message);
                request.setAttribute("Ride_ID", Ride_ID);
                rd.forward(request, response);
            }

            
            
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
