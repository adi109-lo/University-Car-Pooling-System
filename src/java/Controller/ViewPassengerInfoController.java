
package Controller;

import DriverRideManagementModule.Driver;
import PassengerRideManagementModule.Passenger;
import PassengerRideManagementModule.Request;
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

@WebServlet(name = "ViewPassengerInfoController", urlPatterns = {"/ViewPassengerInfoController"})
public class ViewPassengerInfoController extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Passenger_ID = request.getParameter("passengerid");
        HttpSession session = request.getSession();
        Driver d = (Driver) session.getAttribute("driver"); //Get the Driver logged in Right now
        if (d != null) {
            ArrayList<Passenger> Passengers = d.viewPassengerInfo(Passenger_ID);
            RequestDispatcher rd = request.getRequestDispatcher("viewPassengerInfo.jsp");
            request.setAttribute("Passengers", Passengers);
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
