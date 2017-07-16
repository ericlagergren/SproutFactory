package servlets;

import utils.DBUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.SQLException;

@WebServlet(name = "Server", urlPatterns = {"/startup"}, loadOnStartup = 1)
public class Server extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            DBUtils.buildDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
