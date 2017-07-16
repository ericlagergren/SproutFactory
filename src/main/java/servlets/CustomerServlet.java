package servlets;

import com.google.common.collect.Lists;
import models.Customer;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.pool.OracleDataSource;
import utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.*;

@WebServlet(name = "CustomerRegistration", urlPatterns = { "/CustomerRegistration", "/customerRegistration", "/customerregistration" })
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static int recordId = 0;

    private final DataSource ds = DBUtils.dataSource();

    public CustomerServlet() throws ClassNotFoundException, SQLException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT * FROM table_customers");
            List<Customer> customers = Lists.newArrayList();

            request.getSession().setAttribute("customers", customers);

            while (rs.next()) {
                customers.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null){
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/index.jsp?page=customer").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = (List<Customer>) request.getSession().getAttribute("customers");

        if (customers == null) {
            customers = Lists.newArrayList();
            request.getSession().setAttribute("customers", customers);
        }

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String birthDate = request.getParameter("birthDate");
        PreparedStatement insertCustomer = null;

        Customer c = new Customer(String.valueOf(recordId++), firstName, lastName, email, birthDate);
        try {
            insertCustomer = con.prepareStatement("INSERT INTO table_customers(customerID, firstName, lastName, email, birthDate) VALUES (?,?,?,?,?)");
            insertCustomer.setInt(1, recordId++);
            insertCustomer.setString(2, c.getFirstName());
            insertCustomer.setString(3, c.getLastName());
            insertCustomer.setString(4, c.getEmail());
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date date = sdf1.parse(c.getBirthDate());
            insertCustomer.setString(5, sdf1.format(date));
            insertCustomer.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                insertCustomer.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //TODO: check for null before persisting
        customers.add(c);

        request.getSession().setAttribute("customer", c);
        System.out.println(firstName + " " + lastName + " " + email + " " + birthDate);
        doGet(request, response);
/**/    }
}
