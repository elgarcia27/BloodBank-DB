//***********************************************************
//                         DBase.java                       *
//***********************************************************
//  This class provides a user interface to a database of   *
//  bank accounts.  Methods are provided to perform create  *
//  new customers and bank accounts using automatically     *
//  generated keys.                                         *
//***********************************************************

import java.sql.*;
import java.util.*;

public class DBase {
    private Connection conn;
    private boolean isopen;

    // Attempt to connect to the JavaDB bank database.
    // Turn off autocommit to enable transactions.
    public DBase(String uname, String pword) {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:BLOOD BANK DB 2.db",uname,pword);
            conn.createStatement().execute("PRAGMA foreign_keys = ON"); /** Turn on FOREIGN KEYS */
            conn.setAutoCommit(false);
        } catch (Exception e) {conn = null;}
        isopen = (conn != null);
    }

    // Test whether the database is open.
    public boolean isOpen() {return isopen;}

    // Close the database connection.
    public void close() {
        if (!isopen) return;
        try {conn.close();}
        catch (Exception e) {}
        isopen = false;
        conn = null;
    }

    // Display all blood types.
    public void showBloodType() {
        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql, type;
        int bloodId;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Create a PreparedStatement for the query.
            sql = "SELECT * FROM BloodType";
            stmt = conn.prepareStatement(sql);

            // Execute the query and print the result set.
            rset = stmt.executeQuery();
            System.out.printf("%n");
            System.out.printf(
                "   Id      Blood Type%n");
            while (rset.next()) {
                bloodId = rset.getInt(1);
                type = rset.getString(2);
                System.out.printf("%5d      %-12s%n",
                    bloodId , type);
            }
            stmt.close();
            conn.commit();
        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }

    // Display all hospitals.
    public void showHospital() {
        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql, name, phone;
        int hospId, hospAddress;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Create a PreparedStatement for the query.
            sql = "SELECT * FROM Hospital";
            stmt = conn.prepareStatement(sql);

            // Execute the query and print the result set.
            rset = stmt.executeQuery();
            System.out.printf("%n");
            System.out.printf(
                "   Id    Name                             Phone" +
                "   Address%n");
            while (rset.next()) {
                hospId = rset.getInt(1);
                name = rset.getString(2);
                phone = rset.getString(3);
                hospAddress = rset.getInt(4);
                System.out.printf("%5d  %-30s  %-12s    %-5d%n",
                    hospId , name, phone, hospAddress);
            }
            stmt.close();
            conn.commit();
        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }

    // Display all addresses.
    public void showAddress() {
        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql, street, city, state;
        int aId;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Create a PreparedStatement for the query.
            sql = "SELECT * FROM Address";
            stmt = conn.prepareStatement(sql);

            // Execute the query and print the result set.
            rset = stmt.executeQuery();
            System.out.printf("%n");
            System.out.printf(
                "   Id      Street                         City" +
                "                   State%n");
            while (rset.next()) {
                aId = rset.getInt(1);
                street = rset.getString(2);
                city = rset.getString(3);
                state = rset.getString(4);
                System.out.printf("%5d   %-30s  %-20s   %-20s%n",
                    aId, street, city, state);
            }
            stmt.close();
            conn.commit();

        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }

    // Display all donors.
    public void showDonor() {

        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql, dName, dBt, dPhone;
        int dId, dAd, dChart;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Create a PreparedStatement for the query.
            sql = "SELECT * FROM Donor";
            stmt = conn.prepareStatement(sql);

            // Execute the query and print the result set.
            rset = stmt.executeQuery();
            System.out.printf("%n");
            System.out.printf(
                "    Id     Name                        Phone" +
                "       Blood Type      Chart     Address%n");
            while (rset.next()) {
                dId = rset.getInt(1);
                dName = rset.getString(2);
                dBt = rset.getString(3);
                dPhone = rset.getString(4);
                dAd = rset.getInt(5);
                dChart = rset.getInt(6);
                System.out.printf("%5d   %-30s  %-12s   %-12s " + 
                    "%-5d    %-5d%n",
                    dId, dName, dBt, dPhone, dAd, dChart);
            }
            stmt.close();
            conn.commit();

        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }

    // Display all patients.
    public void showPatient() {

        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql, pName, pBt, pPhone;
        int pId, pAd, pCondition, pHosp;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Create a PreparedStatement for the query.
            sql = "SELECT * FROM Patient";
            stmt = conn.prepareStatement(sql);

            // Execute the query and print the result set.
            rset = stmt.executeQuery();
            System.out.printf("%n");
            System.out.printf(
                "   Id      Name                        Phone"+
                "           Blood Type  Condition  " +
                "Address      Hospital%n");
            while (rset.next()) {
                pId = rset.getInt(1);
                pName = rset.getString(2);
                pBt = rset.getString(3);
                pPhone = rset.getString(4);
                pAd = rset.getInt(5);
                pCondition = rset.getInt(6);
                pHosp = rset.getInt(7);
                System.out.printf("%5d   %-30s  %-12s   %-12s " + 
                    "%-10d    %-10d    %-10d%n",
                    pId, pName, pBt, pPhone, pAd, pCondition, pHosp);
            }
            stmt.close();
            conn.commit();

        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }
    
    // Display all donors and the blood banks they donate to.
    public void showDonor_BloodBank() {

        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql;
        int bloodbank, donor;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Create a PreparedStatement for the query.
            sql = "SELECT * FROM Donor_Bloodbank";
            stmt = conn.prepareStatement(sql);

            // Execute the query and print the result set.
            rset = stmt.executeQuery();
            System.out.printf("The following table displays" +
                " all donors and the blood banks they donate to.");
            System.out.printf("%n");
            System.out.printf(
                "   donor   bloodBank   ");
            System.out.printf("%n");
            while (rset.next()) {
                bloodbank = rset.getInt(1);
                donor = rset.getInt(2);
                System.out.printf("%5d              %-5d%n",
                    bloodbank, donor);
            }
            stmt.close();
            conn.commit();

        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }
    
    // Display all blood banks and the blood types they carry.
    public void showBloodBank_bloodTypes() {

        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql;
        int bloodbank, btype;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Create a PreparedStatement for the query.
            sql = "SELECT * FROM BloodType_BloodBank";
            stmt = conn.prepareStatement(sql);

            // Execute the query and print the result set.
            rset = stmt.executeQuery();
            System.out.println("The following table displays " +
                "all blood banks and the blood types they carry.");
            System.out.printf("%n");
            System.out.printf(
                "   bloodBank   bloodTypes   ");
            System.out.printf("%n");
            while (rset.next()) {
                bloodbank = rset.getInt(1);
                btype = rset.getInt(2);
                System.out.printf("%5d              %-5d%n",
                    bloodbank, btype);
            }
            stmt.close();
            conn.commit();

        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }
    
    // Display all patients in their respective hospitals.
    public void showPatient_Hospital() {

        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql;
        int patient, hospital;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Create a PreparedStatement for the query.
            sql = "SELECT * FROM Patient_Hospital";
            stmt = conn.prepareStatement(sql);

            // Execute the query and print the result set.
            rset = stmt.executeQuery();
            System.out.printf("The following table display " +
                    "all patients in their  respective hospitals.");
            System.out.printf("%n");
            System.out.printf(
                "   patients   hospital   ");
            System.out.printf("%n");
            while (rset.next()) {
                patient = rset.getInt(1);
                hospital = rset.getInt(2);
                System.out.printf("%5d              %-5d%n",
                    patient, hospital);
            }
            stmt.close();
            conn.commit();

        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }
    
     // Display all blood banks that deliver to specific hospitals.
    public void showBloodBank_Hospital() {

        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql;
        int bloodbank, hospital;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Create a PreparedStatement for the query.
            sql = "SELECT * FROM BloodBank_Hospital";
            stmt = conn.prepareStatement(sql);

            // Execute the query and print the result set.
            rset = stmt.executeQuery();
            System.out.println("The following table displays " +
                    "all blood banks that deliver blood types" +
                    " to specific hospitals.");
            System.out.printf("%n");
            System.out.printf(
                "   bloodBank   hospital   ");
            System.out.printf("%n");
            while (rset.next()) {
                bloodbank = rset.getInt(1);
                hospital = rset.getInt(2);
                System.out.printf("%5d              %-5d%n",
                    bloodbank, hospital);
            }
            stmt.close();
            conn.commit();

        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }
    
    // Display all blood banks.
    public void showBloodBank() {

        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql, bbName, bbPhone;
        int bbId, bbAd;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Create a PreparedStatement for the query.
            sql = "SELECT * FROM BloodBank";
            stmt = conn.prepareStatement(sql);

            // Execute the query and print the result set.
            rset = stmt.executeQuery();
            System.out.printf("%n");
            System.out.printf(
                "   Id      Name                            Phone" +
                "       Address%n");
            while (rset.next()) {
                bbId = rset.getInt(1);
                bbName = rset.getString(2);
                bbPhone = rset.getString(3);
                bbAd = rset.getInt(4);  
                System.out.printf("%5d   %-30s    %-12s   %-5d%n",
                    bbId, bbName, bbPhone, bbAd);
            }
            stmt.close();
            conn.commit();

        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }

    // Create a new blood bank.
    public void makeBloodbank(Scanner kbd) {
        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql, name, phone;
        int id = -1, address;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Read the blood bank's info.
            System.out.println("Please enter new Blood Bank information");
            kbd.nextLine();
            System.out.print("Name? ");
            name = kbd.nextLine();
            // System.out.print("Blood Type? ");
            // bt = kbd.nextLine();
            System.out.print("Phone Number?  ");
            phone = kbd.nextLine();
            System.out.print("Address? ");
            address = kbd.nextInt();

            // Create a PreparedStatement for the update.
            sql = "INSERT INTO BloodBank(bbName, bbPhone, " +
            "bbAddress ) VALUES "
            + "(?, ?, ?)";
            stmt = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            //stmt.setString(2, bt);//bloodbank doesnt have a bt attribute
            stmt.setString(2, phone);
            stmt.setInt(3, address);

            // Execute the update and retrieve the generated key.
            stmt.executeUpdate();
            rset = stmt.getGeneratedKeys();
            if (rset.next()) id = rset.getInt(1);

            // Display the generated key.
            if (id >= 0) {
                System.out.printf("Blood bank %d was created for %s.%n",
                    id, name);
            } else {
                System.out.printf("The blood bank record was not created.%n");
            }
            stmt.close();
            conn.commit();

        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }

    // Create a new address.
    public void makeAddress(Scanner kbd) {
        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql, street, city, state;
        int id = -1;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Read the Address's info.
            //kbd.nextInt();
            //System.out.println("Id? ");
            //id = kbd.nextInt();
            System.out.println("Please enter new Address information");
            kbd.nextLine();
            System.out.println("Street? ");
            street = kbd.nextLine();
            System.out.println("City? ");
            city = kbd.nextLine();
            System.out.println("State?  ");
            state = kbd.nextLine();

            // Create a PreparedStatement for the update.
            sql = "INSERT INTO Address(street, city, state) VALUES "
            + "(?, ?, ?)";
            stmt = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS);
            //stmt.setInt(1, id);
            stmt.setString(1, street);
            stmt.setString(2, city);
            stmt.setString(3, state);

            // Execute the update and retrieve the generated key.
            stmt.executeUpdate();
            rset = stmt.getGeneratedKeys();
            if (rset.next()) id = rset.getInt(1);

            // Display the generated key.
            if (id >= 0) {
                System.out.printf("Address %d was created for %s    %s  %s.%n",
                    id, street, city, state);
            } else {
                System.out.printf("The address record was not created.%n");
            }
            stmt.close();
            conn.commit();

        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }

    // Create a new donor.
    public void makeDonor(Scanner kbd) {
        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql, name, phone;
        int id = -1, address, btype;
        boolean chart;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Read the Donor's info.
            System.out.println("Please enter new Donor information");
            kbd.nextLine();
            System.out.println("Name? ");
            name = kbd.nextLine();
            System.out.println("Blood Type? ");
            btype = kbd.nextInt();
            System.out.println("Phone Number?  ");
            //System.out.println();
            kbd.nextLine();
            phone = kbd.nextLine();
            System.out.println("Address? ");
            address = kbd.nextInt();
            kbd.nextLine();
            System.out.println("Chart? ");
            chart = kbd.nextBoolean();
            // if ( kbd.nextBoolean() == true || 
                // kbd.nextBoolean() == false) {
                // chart = kbd.nextBoolean();
            // } else if (kbd.nextBoolean() != true || 
                // kbd.nextBoolean() != false) {
                // System.out.println("Please enter true or false");
            // } 

            // Create a PreparedStatement for the update.
            sql = "INSERT INTO Donor(DName, DPhone, DBloodType," +
            "DChart, DAddress) VALUES "
            + "(?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setInt(3, btype);
            stmt.setBoolean(4, chart);
            stmt.setInt(5, address);

            // Execute the update and retrieve the generated key.
            stmt.executeUpdate();
            rset = stmt.getGeneratedKeys();
            if (rset.next()) id = rset.getInt(1);

            // Display the generated key.
            if (id >= 0) {
                System.out.printf("Donor %d was created for %s.%n",
                    id, name);
            } else {
                System.out.printf("The donor record was not created.%n");
            }
            stmt.close();
            conn.commit();

        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }

    // Create a new patient.
    public void makePatient(Scanner kbd) {
        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql, name, phone;
        int id = -1, address, hosp, btype;
        boolean cond;
        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Read the patient's info.
            System.out.println("Please enter new Patient information");
            kbd.nextLine();
            System.out.println("Name? ");
            name = kbd.nextLine();
            System.out.println(name);
            System.out.println("Blood Type? ");
            btype = kbd.nextInt();
            System.out.println(btype);
            kbd.nextLine();
            System.out.println("Phone Number?  ");
            phone = kbd.nextLine();
            System.out.println(phone);
            System.out.println("Address? ");
            address = kbd.nextInt();
            System.out.println(address);
            kbd.nextLine();
            System.out.println("Condition? ");
            cond = kbd.nextBoolean();
            System.out.println(cond);
            System.out.println("Hospital? ");
            hosp = kbd.nextInt();
            System.out.println(hosp);

            // Create a PreparedStatement for the update.
            sql = "INSERT INTO Patient(PName, PPhone, PBloodType,PCondition, " + 
                    "PAddress, PHospital) VALUES "
            + "(?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setInt(3, btype);
            stmt.setBoolean(4, cond);
            stmt.setInt(5, address);
            stmt.setInt(6, hosp);

            // Execute the update and retrieve the generated key.
            stmt.executeUpdate();
            rset = stmt.getGeneratedKeys();
            if (rset.next()) id = rset.getInt(1);

            // Display the generated key.
            if (id >= 0) {
                System.out.printf("Patient %d was created for %s.%n",
                    id, name);
            } else {
                System.out.printf("The patient record was not created.%n");
            }
            stmt.close();
            conn.commit();

        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }

    // Create a new Hospital.
    public void makeHospital(Scanner kbd) {
        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sql, name, phone;
        int id = -1, address;

        // Return if the database is closed.
        if (!isopen) return;

        try {

            // Read the Hospital's info.
            System.out.println("Please enter new Hospital information");
            kbd.nextLine();
            System.out.print("Hospital Name? ");
            name = kbd.nextLine();
            System.out.print("Phone Number?  ");
            phone = kbd.nextLine();
            System.out.print("Address? ");
            address = kbd.nextInt();

            // Create a PreparedStatement for the update.
            sql = "INSERT INTO Hospital(hName, hPhone, hAddress)" 
            + " VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setInt(3, address);

            // Execute the update and retrieve the generated key.
            stmt.executeUpdate();
            rset = stmt.getGeneratedKeys();
            if (rset.next()) id = rset.getInt(1);

            // Display the generated key.
            if (id >= 0) {
                System.out.printf("Hospital %d was created for %s.%n",
                    id, name);
            } else {
                System.out.printf("The Hospital record was not created.%n");
            }
            stmt.close();
            conn.commit();

        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
            try {stmt.close();}
            catch (Exception err) {}
            try {conn.rollback();}
            catch (Exception err) {}
        }
    }
    
    
    
}

// // Create a new account.
// public void makeAccount(Scanner kbd) {
// PreparedStatement stmt = null;
// ResultSet rset = null;
// String sql, kind;
// int owner, acct = -1;
// double balance;

// // Return if the database is closed.
// if (!isopen) return;

// try {

// // Read the account information.
// System.out.print("Customer id?  ");
// owner = kbd.nextInt();
// System.out.print("Account type? ");
// kind = kbd.next();
// System.out.printf("Balance?     ");
// balance = kbd.nextDouble();

// // Create a PreparedStatement for the update.
// sql = "INSERT INTO Account(Owner, Type, Balance) "
// + "VALUES (?, ?, ?)";
// stmt = conn.prepareStatement(
// sql, Statement.RETURN_GENERATED_KEYS);
// stmt.setInt(1, owner);
// stmt.setString(2, kind);
// stmt.setDouble(3, balance);

// // Execute the update and retrieve the generated key.
// stmt.executeUpdate();
// rset = stmt.getGeneratedKeys();
// if (rset.next()) acct = rset.getInt(1);

// // Display the generated key.
// if (acct >= 0) {
// System.out.printf(
// "Account %d was created for customer %d.%n",
// acct, owner);
// } else {
// System.out.printf("The account was not created.%n");
// }
// stmt.close();
// conn.commit();

// } catch (Exception e) {
// System.out.printf("%s%n", e.getMessage());
// try {stmt.close();}
// catch (Exception err) {}
// try {conn.rollback();}
// catch (Exception err) {}
// }
// }
// }
