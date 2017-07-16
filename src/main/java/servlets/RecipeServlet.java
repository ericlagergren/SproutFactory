package servlets;

import models.Recipe;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RecipeRegistration", urlPatterns = { "/RecipeRegistration", "/recipeRegistration", "/reciperegistration" })
public class RecipeServlet extends HttpServlet {
    private static final String SESSION_ATTR = "recipes";

    private final DataSource ds = DBUtils.dataSource();

    public RecipeServlet() throws SQLException, ClassNotFoundException {
    }

    public void display(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try (
                Connection conn = this.ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + DBUtils.RECIPES);
        ) {
            List<Recipe> recipes = new ArrayList<>();

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                recipes.add(new Recipe(rs.getString(1), rs.getString(2)));
            }
            req.getSession().setAttribute(SESSION_ATTR, recipes);
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
        }
        req.getRequestDispatcher("/index.jsp?page=recipes").forward(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        display(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Recipe> recipes = (List<Recipe>) req.getSession().getAttribute(SESSION_ATTR);

        if (recipes == null) {
            recipes = new ArrayList<>();
            req.getSession().setAttribute(SESSION_ATTR, recipes);
        }

        String recipesName = req.getParameter("recipesName");
        String imagePath = req.getParameter("imagePath");

        Recipe r = new Recipe(recipesName, imagePath);
        try (
                Connection conn = this.ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO " + DBUtils.RECIPES + " VALUES (?, ?)"
                )
        ) {
            stmt.setString(1, r.name);
            stmt.setString(2, r.imagePath);
            stmt.execute();

            recipes.add(r);
            req.getSession().setAttribute(SESSION_ATTR, r);
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
        }

        System.err.println(r.toString());
        display(req, resp);
    }
}
