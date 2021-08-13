
package Controller;

import DriverRideManagementModule.Driver;
import DriverRideManagementModule.Ride;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "ViewDriverAndRideInfoController", urlPatterns = {"/ViewDriverAndRideInfoController"})
public class ViewDriverAndRideInfoController extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Driver selectedDriver = Ride.retrieveDriverInfo(request.getParameter("driver_id"));
        RequestDispatcher rd = request.getRequestDispatcher("ViewDriverAndRideInfo.jsp");
        request.setAttribute("selected_driver", selectedDriver);
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
