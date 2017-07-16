package utils;

import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {
    public static DataSource dataSource() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        final OracleDataSource ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@localhost:1521:XE");
        ods.setUser("student1");
        ods.setPassword("pass");
        return ods;
    }

    public static final String CUSTOMERS = "customers";
    public static final String SUPPLIERS = "suppliers";
    public static final String PRODUCTS = "products";
    public static final String EMPLOYEES = "employees";
    public static final String RECIPES = "recipes";

    private static final String CREATE_TABLE = "CREATE TABLE ";
    private static final String PRIM_KEY = "PRIMARY KEY";
    private static final String NOT_NULL = "NOT NULL";
    private static final String NEWLINE = ",\n";
    private static final String RPAREN = "\n)";
    private static final String LPAREN = "(\n";

    public static void buildDatabase() throws SQLException, ClassNotFoundException { buildDatabase(dataSource()); }
    public static void buildDatabase(DataSource ds) throws SQLException, ClassNotFoundException {
        exec(ds, CREATE_TABLE + CUSTOMERS + LPAREN
                    + "id    INT "      + PRIM_KEY + NEWLINE
                    + "first CHAR(20) " + NOT_NULL + NEWLINE
                    + "last  CHAR(20) " + NOT_NULL + NEWLINE
                    + "email CHAR(20) " + NOT_NULL + NEWLINE
                    + "dob   CHAR(80) " + RPAREN
        );
        exec(ds, CREATE_TABLE + SUPPLIERS + LPAREN
                    + "firstName      CHAR(20) " + NOT_NULL + NEWLINE
                    + "lastName       CHAR(20) " + NOT_NULL + NEWLINE
                    + "suppliersID    INT      " + PRIM_KEY + NEWLINE
                    + "SuppliersEmail CHAR(20) " + NOT_NULL + RPAREN
        );
        exec(ds, CREATE_TABLE + PRODUCTS + LPAREN
                    + "productName  CHAR(20)      " + NOT_NULL + NEWLINE
                    + "productPrice DECIMAL(7, 2) " + NOT_NULL + NEWLINE
                    + "productID    INT           " + PRIM_KEY + NEWLINE
                    + "productIsNew CHAR(3)       " + NOT_NULL + NEWLINE
                    + "suppliersID  INT           " + NOT_NULL + RPAREN
        );
        exec(ds,CREATE_TABLE + EMPLOYEES + LPAREN
                    + "firstName      CHAR(20)      " + NOT_NULL + NEWLINE
                    + "lastName       CHAR(20)      " + NOT_NULL + NEWLINE
                    + "wage           DECIMAL(7, 2) " + NOT_NULL + NEWLINE
                    + "employeesID    INT           " + PRIM_KEY + NEWLINE
                    + "productIsNew   CHAR(3)       " + NOT_NULL + NEWLINE
                    + "employeesEmail CHAR(20)      " + NOT_NULL + RPAREN
        );
        exec(ds, CREATE_TABLE + RECIPES + LPAREN
                    + " recipesName CHAR(20) " + NOT_NULL + NEWLINE
                    + " imagePath   CHAR(60) " + NOT_NULL + RPAREN
        );
        exec(ds, "ALTER TABLE table_product "
                    + "ADD CONSTRAINT FK_SUPPLIERS FOREIGN KEY (suppliersID) "
                    + "REFERENCES table_Suppliers(suppliersID)"
        );
    }

    private static void exec(DataSource ds, String query) throws SQLException {
        try (
                Connection conn = ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ) { stmt.execute(); }
    }
}
