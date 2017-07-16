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

    // I promise using these constants is prettier and safer than manually typing it out.

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
    private static final String UUID_TYPE = "RAW (16) DEFAULT sys_guid() ";

    public static void buildDatabase() throws SQLException, ClassNotFoundException { buildDatabase(dataSource()); }
    public static void buildDatabase(DataSource ds) throws SQLException, ClassNotFoundException {
        exec(ds, CREATE_TABLE + CUSTOMERS + LPAREN
                    + "id "     + UUID_TYPE  + PRIM_KEY + NEWLINE
                    + "first_name CHAR(20)  " + NOT_NULL + NEWLINE
                    + "last_name  CHAR(20)  " + NOT_NULL + NEWLINE
                    + "email      CHAR(20)  " + NOT_NULL + NEWLINE
                    + "dob        TIMESTAMP " + RPAREN
        );
        exec(ds, CREATE_TABLE + SUPPLIERS + LPAREN
                    + "first_name CHAR(20) " + NOT_NULL + NEWLINE
                    + "last_name  CHAR(20) " + NOT_NULL + NEWLINE
                    + "id "     + UUID_TYPE  + PRIM_KEY + NEWLINE
                    + "email      CHAR(20) " + NOT_NULL + RPAREN
        );
        exec(ds, CREATE_TABLE + PRODUCTS + LPAREN
                    + "id "      + UUID_TYPE        + PRIM_KEY + NEWLINE
                    + "name        CHAR(20)       " + NOT_NULL + NEWLINE
                    + "price       DECIMAL(7, 2)  " + NOT_NULL + NEWLINE
                    + "is_new      CHAR(3)        " + NOT_NULL + NEWLINE
                    + "supplier_id RAW(16)        " + NOT_NULL + RPAREN
        );
        exec(ds, CREATE_TABLE + EMPLOYEES + LPAREN
                    + "first_name CHAR(20)      " + NOT_NULL + NEWLINE
                    + "last_name  CHAR(20)      " + NOT_NULL + NEWLINE
                    + "wage       DECIMAL(7, 2) " + NOT_NULL + NEWLINE
                    + "id "     + UUID_TYPE       + PRIM_KEY + NEWLINE
                    + "is_new     CHAR(1)       " + NOT_NULL + NEWLINE
                    + "email      CHAR(20)      " + NOT_NULL + RPAREN
        );
        exec(ds, CREATE_TABLE + RECIPES + LPAREN
                    + " name      CHAR(20) " + NOT_NULL + NEWLINE
                    + " imagePath CHAR(60) " + NOT_NULL + RPAREN
        );
        exec(ds, "ALTER TABLE products "
                    + "ADD CONSTRAINT FK_SUPPLIERS FOREIGN KEY (supplier_id) "
                    + "REFERENCES suppliers(id)"
        );
    }

    private static void exec(DataSource ds, String query) throws SQLException {
        try (
                Connection conn = ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ) { stmt.execute(); }
    }
}
