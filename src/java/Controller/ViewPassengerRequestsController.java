
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


@WebServlet(name = "ViewPassengerRequestsController", urlPatterns = {"/ViewPassengerRequestsController"})
public class ViewPassengerRequestsController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Ride_ID = request.getParameter("rideid");
        HttpSession session = request.getSession();
        Driver d = (Driver) session.getAttribute("driver"); //Get the Driver logged in Right now
        if (d != null) {
            ArrayList<Request> Requests = d.viewPassengerRequests(Ride_ID);
            RequestDispatcher rd = request.getRequestDispatcher("viewPassengerRequests.jsp");
            request.setAttribute("Requests", Requests);
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
