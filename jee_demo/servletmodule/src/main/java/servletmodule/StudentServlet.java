package servletmodule;

import ejbmodules.beans.CalculatorBean;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Hikmat Dhamee
 *
 */
@WebServlet(value="/student", name="hello-student")
public class StudentServlet extends GenericServlet {
    DataSource ds = null;
    Connection conn = null;

    public void init() throws ServletException {
        try {
            InitialContext ic = new InitialContext();  // JNDI initial context
            ds = (DataSource) ic.lookup("jdbc/UserDB"); // JNDI lookup
            conn = ds.getConnection();  // database connection through data source
        }
        catch (SQLException se) {
            throw new ServletException(se);
        }
        catch (NamingException ne) {
            throw new ServletException(ne);
        }
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        res.getWriter().println("Hello Sample!");

        // simple JNDI lookup
        try {
            System.out.println("Connection Available: " +  conn.getNetworkTimeout());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // EJB call from servlet
        // Step 4: Use JNDI to look up the EJB local home interface.
        try {
            InitialContext ic = new InitialContext();
            CalculatorBean hlh = (CalculatorBean)ic.lookup("java:comp/env/ejb/CalculatorBean");
            res.getWriter().println(hlh.subtract(2,5));

        } catch (NamingException ne) {
            System.out.println("Could not locate the bean.");
        } catch (Exception e) {
            // Unexpected exception; send back to client for now.
            throw new ServletException(e);
        }

    }

}