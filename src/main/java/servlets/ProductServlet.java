package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import com.google.common.collect.Lists;
import models.Product;
import utils.Bool;
import utils.DBUtils;
import utils.UUID;

@WebServlet(name = "ProductRegistration", urlPatterns = { "/ProductRegistration", "/productRegistration", "/productregistration" })
public class ProductServlet extends HttpServlet {
    private final DataSource ds = DBUtils.dataSource();
    private final static String SESSION_ATTR = "productxs";

    public ProductServlet() throws SQLException, ClassNotFoundException {
    }

    public void display(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try (
                Connection conn = this.ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + DBUtils.PRODUCTS)
        ) {
            ResultSet rs = stmt.executeQuery();

            List<Product> products = Lists.newArrayList();
            while (rs.next()) {
                products.add(
                        new Product(
                                new UUID(rs.getBytes("id")),
                                rs.getString("name"),
                                new BigDecimal(rs.getString("price")),
                                Bool.parse(rs.getString("isNew"))
                        )
                );
            }
            req.getSession().setAttribute(SESSION_ATTR, products);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/index.jsp?page=product").forward(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        display(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = (List<Product>) req.getSession().getAttribute(SESSION_ATTR);

        if (products == null) {
            products = Lists.newArrayList();
            req.getSession().setAttribute(SESSION_ATTR, products);
        }

        String name = req.getParameter("name");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        boolean isNew = Bool.parse(req.getParameter("isNew"));

        Product p = new Product(name, price, isNew);
        try (
                Connection conn = this.ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement("" +
                        "INSERT INTO " + DBUtils.PRODUCTS + " VALUES (DEFAULT, ?, ?, ?)"
                )
        ){
            stmt.setString(1, p.name);
            stmt.setBigDecimal(2, p.price);
            stmt.setBoolean(3, p.isNew);
            stmt.execute();

            products.add(p);
            req.getSession().setAttribute(SESSION_ATTR, p);
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
            display(req, resp);
        }


        System.err.println(p.toString());
        display(req, resp);
    }
}
