
package Controller;

import DriverRideManagementModule.Driver;
import PassengerRideManagementModule.Passenger;
import UserManagementModule.Account;
import UserManagementModule.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
import javax.servlet.http.Part;


@WebServlet("/LoginController")
//@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String userName = request.getParameter("email");
            String password = request.getParameter("pwd");
            boolean isValid= false;
            try {
                isValid = (new Account(userName, password)).login();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (isValid) {//Successful login
                HttpSession session = request.getSession(); 
                if (session != null) //If session is not null
                {
                    //removes all session attributes bound to the session
                    while (session.getAttributeNames().hasMoreElements()) {
                        session.removeAttribute(session.getAttributeNames().nextElement());
                    }
                }
                session.setAttribute("username", userName);
                //If admin, go to the view driver applications page
                if (Admin.isAdmin(userName)) {
   
     
                    RequestDispatcher rd = request.getRequestDispatcher("ViewDriverRequestsController");
                    
                    
                    rd.forward(request, response);
                }
                //else passenger and/or driver login
                Passenger p = Passenger.getPassenger(userName);
                session.setAttribute("passenger", p);
                if (p.isDriver()) {
                    Driver d = new Driver();
                    d.setEmailID(userName);
                    session.setAttribute("driver", d);
                    System.out.println("setting as driver" + userName);
                }
                
                RequestDispatcher rd = request.getRequestDispatcher("findRide.jsp");
                rd.forward(request, response);
            } else { // GO back to login page
                request.setAttribute("errmsg", "Username or Password is Invalid");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errmsg", "Connection Error");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
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
