
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


@WebServlet(name = "PassengerActionController", urlPatterns = {"/PassengerActionController"})
public class PassengerActionController extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Passenger_ID = request.getParameter("passengerid");
        String Ride_ID = request.getParameter("rideid");
        String Passenger_Status = request.getParameter("passengerstatus");
        HttpSession session = request.getSession();
        Driver d = (Driver) session.getAttribute("driver"); //Get the Driver logged in Right now
        if (d != null) {
            if ("Confirm/Remove".equals(Passenger_Status))
            {
                String Message = "Confirm or Remove Pending Passenger:";
                RequestDispatcher rd = request.getRequestDispatcher("ConfirmOrRemovePendingPassenger.jsp");
                request.setAttribute("Message", Message);
                request.setAttribute("Passenger_ID", Passenger_ID);
                request.setAttribute("Ride_ID", Ride_ID);
                rd.forward(request, response);
            }
            else
            {
                
                String Message = "Remove Registered Passenger:";
                RequestDispatcher rd = request.getRequestDispatcher("RemoveRegisteredPassenger.jsp");
                request.setAttribute("Message", Message);
                request.setAttribute("Passenger_ID", Passenger_ID);
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
