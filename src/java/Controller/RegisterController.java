
package Controller;

import UserManagementModule.Platform;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("pwd");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gender = request.getParameter("gender");
        String mobilenumber = request.getParameter("mobilenumber");

        boolean isAdded;
        try {
            isAdded = (new Platform()).submitRegistrationFormDetails(email, password, fname, lname, gender, mobilenumber);
            if (isAdded) {
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("errmsg", "You can now log-in with your registered ID");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("NewUserRegistration.jsp");
                request.setAttribute("errmsg", "User Already Exist");
                rd.forward(request, response);
            }
        } catch (SQLException ex) {
            RequestDispatcher rd = request.getRequestDispatcher("NewUserRegistration.jsp");
            request.setAttribute("errmsg", "Connection Issues. Try again later. Please note you can only submit the form once.");
            rd.forward(request, response);
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
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
