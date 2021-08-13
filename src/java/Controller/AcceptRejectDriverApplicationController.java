
package Controller;

import DriverRideManagementModule.Driver;
import PassengerRideManagementModule.Passenger;
import UserManagementModule.Admin;
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


@WebServlet(name = "AcceptRejectDriverApplicationController", urlPatterns = {"/AcceptRejectDriverApplicationController"})
public class AcceptRejectDriverApplicationController extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Driver_ID = request.getParameter("driverID");
        String Action = request.getParameter("action");
        
        HttpSession session = request.getSession();
            
            if ("accept".equals(Action))
            {
                Admin.acceptDriverRequest(Driver_ID);
                String Message = "Driver Application Request Accepted Successfully!";
                RequestDispatcher rd = request.getRequestDispatcher("DriverAcceptedOrRejected.jsp");
                request.setAttribute("Message", Message);
                rd.forward(request, response);
     

            }
            else if("cancel".equals(Action))
            {
                Admin.rejectDriverRequest(Driver_ID);
                String Message = "Driver Application Request Rejected Successfully!";
                RequestDispatcher rd = request.getRequestDispatcher("DriverAcceptedOrRejected.jsp");
                request.setAttribute("Message", Message);
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