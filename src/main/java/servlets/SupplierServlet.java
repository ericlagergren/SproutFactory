package servlets;

import com.google.common.collect.Lists;
import models.Supplier;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.sql.*;
import utils.DBUtils;
import utils.UUID;

@WebServlet(name = "SupplierRegistration", urlPatterns = { "/SupplierRegistration", "/supplierRegistration", "/supplierregistration" })
public class SupplierServlet extends HttpServlet {
    private final static String SESSION_ATTR = "suppliers";
    private final DataSource ds = DBUtils.dataSource();

    public SupplierServlet() throws SQLException, ClassNotFoundException {
    }

    public void display(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try (
                Connection conn = this.ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + DBUtils.SUPPLIERS);
        ) {
            List<Supplier> suppliers = Lists.newArrayList();
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                suppliers.add(
                        new Supplier(
                                new UUID(rs.getBytes("id")),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("email")
                        )
                );
            }

            req.getSession().setAttribute("suppliers", suppliers);
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
        }
        req.getRequestDispatcher("/index.jsp?page=suppliers").forward(req, resp);
    }


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        display(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Supplier> suppliers = (List<Supplier>) req.getSession().getAttribute(SESSION_ATTR);

        if (suppliers == null) {
            suppliers = Lists.newArrayList();
            req.getSession().setAttribute("suppliers", suppliers);
        }

        Supplier s = new Supplier(
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                req.getParameter("suppliersEmail")
        );
        try (
                Connection conn = this.ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO " + DBUtils.SUPPLIERS + " VALUES (DEFAULT, ?, ?, ?)"
                )
        ) {
            stmt.setString(1, s.firstName);
            stmt.setString(2, s.lastName);
            stmt.setString(3, s.email);
            stmt.execute();

            req.getSession().setAttribute(SESSION_ATTR, s);
            suppliers.add(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.err.println(s.toString());
        display(req, resp);
   }
}
