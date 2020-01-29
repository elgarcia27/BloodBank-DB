
    //***********************************************************
    //                       BloodBank.java                      *
    //***********************************************************
    //   Written by Edward McDowell for Computer Science 455. 
    //   Modified by Ellis Garcia to fit a Blood Bank DB
    //***********************************************************
    //  This program uses JDBC to create customers and bank     *
    //  accounts using automatically generated keys.            *
    //***********************************************************
    import java.util.*;

    public class BloodBank {
        // Open the database and process a sequence of commands.
        public static void main(String[] args) {
            Scanner kbd = new Scanner(System.in);
            DBase db = new DBase(null, null);
            String cmd;

            if (!db.isOpen()) {
                System.out.printf("Could not connect to database.%n");
                System.exit(1);
            }
            cmd = showMenu(kbd);
            while (!cmd.equals("0")) {
                if (cmd.equals("1")) db.showAddress();
                if (cmd.equals("2")) db.showBloodBank();
                if (cmd.equals("3")) db.showBloodType();
                if (cmd.equals("4")) db.showDonor();
                if (cmd.equals("5")) db.showHospital();
                if (cmd.equals("6")) db.showPatient();
                if (cmd.equals("7")) db.showBloodBank_bloodTypes();
                if (cmd.equals("8")) db.showBloodBank_Hospital();
                if (cmd.equals("9")) db.showDonor_BloodBank();
                if (cmd.equals("10")) db.showPatient_Hospital();
                if (cmd.equals("11")) db.makeAddress(kbd);
                if (cmd.equals("12")) db.makeBloodbank(kbd);
                if (cmd.equals("13")) db.makeDonor(kbd);
                if (cmd.equals("14")) db.makeHospital(kbd);
                if (cmd.equals("15")) db.makePatient(kbd);
                cmd = showMenu(kbd);
            }
            db.close();
        }

        // Display the menu and read the next command.
        public static String showMenu(Scanner kbd) {
            System.out.printf("%n");
            System.out.printf("1:  Show address.%n");
            System.out.printf("2:  Show blood banks.%n");
            System.out.printf("3:  Show blood types.%n"); 
            System.out.printf("4:  Show donors.%n");
            System.out.printf("5:  Show hospitals.%n");
            System.out.printf("6:  Show patients.%n");
            System.out.printf("7:  Blood banks and the blood types they carry%n");
            System.out.printf("8:  Dlood banks that deliver to specific hospitals.%n");
            System.out.printf("9:  Donors and the blood banks they donate to.%n");
            System.out.printf("10:  Patients in their respective hospitals.%n");
            System.out.printf("11:  Create address.%n");
            System.out.printf("12:  Create blood bank.%n");
            System.out.printf("13:  Create donor.%n");
            System.out.printf("14:  Create hospital.%n");
            System.out.printf("15:  Create patient.%n");
            System.out.printf("0:  Exit.%n");
            System.out.printf("%n");
            System.out.printf("Command? ");
            return kbd.next();
        }
    }



  