package servlets;

import utils.DBUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.SQLException;

@WebServlet(name = "StartupServlet", urlPatterns = "/startup", loadOnStartup = 1)
public class StartupServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            DBUtils.buildDatabase();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
