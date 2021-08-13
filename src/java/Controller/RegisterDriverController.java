package Controller;

import DriverRideManagementModule.Driver;
import PassengerRideManagementModule.Passenger;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import javax.sql.rowset.CachedRowSet;


@MultipartConfig
@WebServlet(name = "RegisterDriverController", urlPatterns = {"/RegisterDriverController"})
public class RegisterDriverController extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String carModel = request.getParameter("carModel");
        String capacity = request.getParameter("capacity");

        // obtains the upload file part in this multipart request
        Part filePartEID = request.getPart("EID");
        Part filePartLicense = request.getPart("license");
        Part filePartCarReg = request.getPart("carReg");
        System.out.println(filePartCarReg);
        HttpSession session = request.getSession();
        Passenger p = (Passenger) session.getAttribute("passenger");
        Integer carCapacity = Integer.parseInt(capacity);

        boolean checkDetails = p.checkDriverRegistrationDetails(carModel, carCapacity, filePartEID, filePartLicense, filePartCarReg);
        if(checkDetails){
            try {
                System.out.println("Inside");
                Driver d = new Driver();
                boolean success = d.updateDriverProfile(p.getEmailID(), carModel, carCapacity, filePartEID, filePartLicense, filePartCarReg);
                if(success){
                    request.setAttribute("errmsg", "Submitted your application. Please wait for admin to review.");
                }else{
                    System.out.println("Driver did not add");
                    request.setAttribute("errmsg", "Something went wrong. Try again Later");
                }
            } catch (SQLException ex) {
                Logger.getLogger(RegisterDriverController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("errmsg", "Something went wrong. If you already submitted a request earlier, please have patience.");
            }
        }else{
            System.out.println("Passenger said false");
             request.setAttribute("errmsg", "Something went wrong. Please enter details correctly.");
        }
        RequestDispatcher rd = request.getRequestDispatcher("RegisterAsDriver.jsp");
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
