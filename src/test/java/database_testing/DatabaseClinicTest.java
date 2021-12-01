package database_testing;

import databaseintegration.DatabaseClinic;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import org.junit.*;
import static org.junit.Assert.*;

public class DatabaseClinicTest {
    Connection connection;
    Statement statement;
    DatabaseClinic clinicDB;

    @Before // Setting up before the tests
    public void setUp() throws Exception{
        String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5447070"; //URL of database to be connected
        connection = DriverManager.getConnection(url, "sql5447070", "AxQ1YdG6MP");
        statement = connection.createStatement();
        clinicDB = new DatabaseClinic(connection, statement);
    }

    @Test(timeout = 150) // Testing adding an existing clinic to the clinic table
    public void TestAddClinicExisting() throws SQLException {
        assertFalse(clinicDB.addClinic(1, "420 Yonge St.", true));
    }

    @Test(timeout = 100) // Testing an expired batch of vaccine doses does not get added
    public void TestLoadClinicIDs() throws SQLException {
        ArrayList<Object> results = clinicDB.loadAllClinics();
        assertNotNull(results);
    }

}
