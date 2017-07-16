package servlets;

import com.google.common.collect.Lists;
import models.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.sql.*;
import oracle.jdbc.OracleDriver;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

@WebServlet(name = "SupplierRegistration", urlPatterns = { "/SupplierRegistration", "/supplierRegistration", "/supplierregistration" })
public class SupplierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static int recordId = 0;
    Connection con = null;
    Statement stmt = null;

    public SupplierServlet() {
        init();
    }

    public void init() {
        try {
            DriverManager.registerDriver(new OracleDriver());
            con =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student1", "pass");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT * FROM table_Suppliers");
            List<Supplier> suppliers = Lists.newArrayList();

            request.getSession().setAttribute("suppliers", suppliers);

            while (rs.next()) {
                suppliers.add(new Supplier(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4)));
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
        request.getRequestDispatcher("/index.jsp?page=suppliers").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Supplier> suppliers = (List<Supplier>) request.getSession().getAttribute("suppliers");

        if (suppliers == null) {
            suppliers = Lists.newArrayList();
            request.getSession().setAttribute("suppliers", suppliers);
        }

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String suppliersEmail = request.getParameter("suppliersEmail");
        PreparedStatement insertSupplier = null;

        Supplier c = new Supplier(String.valueOf(recordId++), firstName, lastName, suppliersEmail);
        try {
            insertSupplier = con.prepareStatement("INSERT INTO table_Suppliers(suppliersID, firstName, lastName, suppliersEmail) VALUES (?,?,?,?)");
            insertSupplier.setInt(1, recordId++);
            insertSupplier.setString(2, c.getFirstName());
            insertSupplier.setString(3, c.getLastName());
            insertSupplier.setString(4, c.getSuppliersEmail());
            insertSupplier.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                insertSupplier.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //TODO: check for null before persisting
        suppliers.add(c);

        request.getSession().setAttribute("supplier", c);
        System.out.println(firstName + " " + lastName + " " + suppliersEmail);
        doGet(request, response);
   }
}
