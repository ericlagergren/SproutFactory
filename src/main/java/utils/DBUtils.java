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

    public static void buildDatabase() throws SQLException, ClassNotFoundException { buildDatabase(dataSource()); }
    public static void buildDatabase(DataSource ds) throws SQLException, ClassNotFoundException {
        exec(ds, "CREATE TABLE customers ( "
                    + "id INT NOT NULL PRIMARY KEY, "
                    + "first CHAR(20) NOT NULL, "
                    + "last CHAR(20) NOT NULL, "
                    + "email CHAR(20) NOT NULL, "
                    + "dob CHAR(80) )"
        );
        exec(ds, "CREATE TABLE table_Suppliers ( "
                    + "firstName CHAR(20) NOT NULL, "
                    + "lastName CHAR(20) NOT NULL, "
                    + "suppliersID INT NOT NULL PRIMARY KEY, "
                    + "SuppliersEmail CHAR(20) NOT NULL )"
        );
        exec(ds, "CREATE TABLE table_product ( "
                    + "productName CHAR(20) NOT NULL, "
                    + "productPrice decimal(7,2) NOT NULL, "
                    + "productID INT NOT NULL PRIMARY KEY, "
                    + "productIsNew CHAR(3) NOT NULL, "
                    + "suppliersID INT NOT NULL )"
        );
        exec(ds,"CREATE TABLE table_employees ( "
                    + "firstName CHAR(20) NOT NULL, "
                    + "lastName CHAR(20) NOT NULL, "
                    + "wage decimal(7,2) NOT NULL, "
                    + "employeesID INT NOT NULL PRIMARY KEY, "
                    + "productIsNew CHAR(3) NOT NULL, "
                    + "employeesEmail CHAR(20) NOT NULL )"
        );
        exec(ds, "CREATE TABLE table_recipes ( "
                    + " recipesName CHAR(20) NOT NULL, "
                    + " imagePath CHAR(60) NOT NULL )"
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
