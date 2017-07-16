package servlets;

import com.google.common.collect.Lists;
import models.Customer;
import utils.DBUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CustomerRegistration", urlPatterns = { "/CustomerRegistration", "/customerRegistration", "/customerregistration" })
public class CustomerServlet extends HttpServlet {
    private final DataSource ds = DBUtils.dataSource();
    private static final String SESSION_ATTR = "customers";

    public CustomerServlet() throws ClassNotFoundException, SQLException {
    }

    private void display(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try (
                Connection conn = this.ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM table_customers");
                ResultSet rs = stmt.executeQuery()
        ) {
            List<Customer> customers = Lists.newArrayList();
            while (rs.next()) {
                Customer c = new Customer(
                        rs.getInt("id"),
                        rs.getString("first"),
                        rs.getString("last"),
                        rs.getString("email"),
                        rs.getString("dob")
                );
                customers.add(c);
            }
            req.getSession().setAttribute(SESSION_ATTR, customers);
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
        }
        req.getRequestDispatcher("/index.jsp?page=customer").forward(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        display(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = (List<Customer>) req.getSession().getAttribute(SESSION_ATTR);

        if (customers == null) {
            customers = Lists.newArrayList();
            req.getSession().setAttribute("customers", customers);
        }

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String birthDate = req.getParameter("birthDate");

        Customer c = new Customer(firstName, lastName, email, birthDate);
        try (
                Connection conn = this.ds.getConnection();
                // id, first, last, email, dob
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO table_customers VALUES (DEFAULT, ?, ?, ?, ?)"
                )
        ) {
            stmt.setString(1, c.firstName);
            stmt.setString(2, c.lastName);
            stmt.setString(3, c.email);

            SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
            Date date = fmt.parse(c.birthDate);

            stmt.setString(4, fmt.format(date));
            stmt.execute();

            customers.add(c);
            req.getSession().setAttribute(SESSION_ATTR, c);
        } catch (SQLException | ParseException e) {
            resp.getWriter().println(e.getMessage());
        }

        System.err.println(firstName + " " + lastName + " " + email + " " + birthDate);
        display(req, resp);
    }
}
