
package Controller;

import DriverRideManagementModule.Driver;
import DriverRideManagementModule.Ride;
import DriverRideManagementModule.SingleRide;
import DriverRideManagementModule.WeeklyRide;
import PassengerRideManagementModule.Location;
import PassengerRideManagementModule.Passenger;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(name = "OfferARideController", urlPatterns = {"/OfferARideController"})
public class OfferARideController extends HttpServlet {

    public void setRideAttributes(Ride r, String isToUni, Location startingLocation, Location endingLocation,Driver d, String arrivalDepTime) {
        if (isToUni.equals("1")) {
            r.setIsToUni(true);
        } else {
            r.setIsToUni(false);
        }
        r.setStartingLocation(startingLocation);
        r.setEndingLocation(endingLocation);
        r.setDriver(d);
        r.setArrivalDepartureTime(arrivalDepTime);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Collect and parse all form inputs 

        String rideTime = request.getParameter("ride_time");
        if (Ride.isValid(rideTime)) {
            HttpSession session = request.getSession();
            Driver d = (Driver) session.getAttribute("driver");
            System.out.println(d.getEmailID());//Get the Driver logged in Right now
            Ride r = null;
            String isToUni = (request.getParameter("to_from_uni").equals("to") ? "1" : "0");
            float startingLocationLongitude = 0;
            float startingLocationLatitude = 0;
            float endingLocationLongitude = 0;
            float endingLocationLatitude = 0;

            if (isToUni.equals("1")) {
                //Starting location is home,,, dropoff is uni
                startingLocationLongitude = Float.parseFloat(request.getParameter("home_location_longitude"));
                startingLocationLatitude = Float.parseFloat(request.getParameter("home_location_latitude"));
                endingLocationLongitude = Float.parseFloat(request.getParameter("uni_location_longitude"));
                endingLocationLatitude = Float.parseFloat(request.getParameter("uni_location_latitude"));
            } else {

                //Starting is Uni, dropoff is home
                startingLocationLongitude = Float.parseFloat(request.getParameter("uni_location_longitude"));
                startingLocationLatitude = Float.parseFloat(request.getParameter("uni_location_latitude"));
                endingLocationLongitude = Float.parseFloat(request.getParameter("home_location_longitude"));
                endingLocationLatitude = Float.parseFloat(request.getParameter("home_location_latitude"));
            }
            Location startingLocation = new Location(startingLocationLatitude, startingLocationLongitude);
            Location endingLocation = new Location(endingLocationLatitude, endingLocationLongitude);

            //Single or Weekly Rides
            boolean isSingle = request.getParameter("single_or_weekly").equals("single");
            if (isSingle) {
                r = new SingleRide();
                setRideAttributes(r,isToUni,startingLocation,endingLocation,d, rideTime);
                LocalDate date = LocalDate.parse(request.getParameter("ride_date"));
                ((SingleRide) r).setDate(date);
                ((SingleRide) r).updateRideInfo();
            } else {
                r = new WeeklyRide();
                setRideAttributes(r,isToUni,startingLocation,endingLocation,d, rideTime);
                for (String day : request.getParameterValues("ride_days")) {
                    ((WeeklyRide)r).setDay(day);
                    ((WeeklyRide)r).updateRideInfo();
                }
            }

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("OfferARide.jsp");
            request.setAttribute("errmsg", "Invalid Ride Timing. Please offer a time before 10p.m and after 3a.m");
            rd.forward(request, response);
        }

        request.setAttribute("successmsg", "Successfully Offered Ride!");
        RequestDispatcher rd = request.getRequestDispatcher("OfferARide.jsp");
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
