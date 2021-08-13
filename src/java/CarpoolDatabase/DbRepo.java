
package CarpoolDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

 
public class DbRepo {
   
    //CachedRowSet getConfiguredConnection()
    public static CachedRowSet getConfiguredConnection() {
        CachedRowSet crs = null;
        try {
            //System.out.println("****");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
            crs.setUsername("system");
            crs.setPassword("tiger");

        } catch (SQLException ex) {
            //System.out.println("****");
            Logger.getLogger(DbRepo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //System.out.println("************");
            Logger.getLogger(DbRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return crs;
    }
}
