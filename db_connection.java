
package medical_database_function;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;


class db_connection {

    private Connection conn = null;
    private Boolean valiator = false;
    private Boolean doc_vali = false;
    private String patient_name;
    
    private ArrayList<String> hospital_name = new ArrayList<>();
    private ArrayList<Date> date_visit = new ArrayList<>();
    private ArrayList<Date> future_date = new ArrayList<>();
    private ArrayList<String> doctor_name = new ArrayList<>();
    private ArrayList<String> reason_visit = new ArrayList<>();
    private ArrayList<String> treatment = new ArrayList<>();
    private int patientID = 0;
    private int docID = 0;

    //Dis is the connection or the Orkz will hunt you down
    public db_connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_database?zeroDateTimeBehavior=convertToNull","root", "");
            //System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Didn't connect, Error: " + ex);
        }
    }

    //closing the database connection, so don't let it open for f**ksake
    public void shutdown() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
        }
    }

    //checking if username and password are correct or not *reeee*
    public boolean login_validator(String name, String pw) {
        String query = "SELECT idPatient,Patient_name, password FROM patient WHERE Patient_name ='" + name + "'AND password ='" + pw + "'";
        String validator_name = null;
        String validator_password = null;
        try {
            Statement statement = this.conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                validator_name = result.getString("Patient_name");
                validator_password = result.getString("password");
                this.patientID = result.getInt("idPatient");
                this.patient_name = result.getString("Patient_name");

                if (validator_name.matches(name) && validator_password.matches(pw)) {
                    this.valiator = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(db_connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.valiator;
    }

    public void treatment_collection() {
        String new_query = "SELECT * FROM medical_database.treatment "
                + "INNER JOIN doctor ON treatment.idDoctor = doctor.idDoctor "
                + "INNER JOIN hospital ON treatment.idHospital = hospital.idHospital "
                + "WHERE idPatient ='" + this.patientID + "'";

        try {
            Statement statement = this.conn.createStatement();
            ResultSet res = statement.executeQuery(new_query);
            while (res.next()) {
                Date date_visit = res.getDate("date_to_visit");
                Date future_visit = res.getDate("Future_date");
                String hospital_name = res.getString("hospital.Hospital_name");
                String doctor_name = res.getString("doctor.Doctor_name");
                String reason_visit = res.getString("reason_to_visit");
                String treatment = res.getString("treatment_to_visitor");
                
                this.date_visit.add(date_visit);
                this.future_date.add(future_visit);
                this.doctor_name.add(doctor_name);
                this.hospital_name.add(hospital_name);
                this.treatment.add(treatment);
                this.reason_visit.add(reason_visit);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(db_connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Return value from treatment table or Declare Exterminatus

    public ArrayList<String> getHospital_name() {
        return hospital_name;
    }

    public ArrayList<Date> getDate_visit() {
        return date_visit;
    }

    public ArrayList<Date> getFuture_date() {
        return future_date;
    }

    public ArrayList<String> getDoctor_name() {
        return doctor_name;
    }

    public ArrayList<String> getReason_visit() {
        return reason_visit;
    }

    public ArrayList<String> getTreatment() {
        return treatment;
    }

    public String getPatient_name() {
        return patient_name;
    }

    //Doctor login validator
    public Boolean login_doc_validator(String docName, String docpw) {
       String query = "SELECT idDoctor,Doctor_name, doctor_password FROM doctor WHERE Doctor_name ='" + docName + "'AND doctor_password ='" + docpw + "'";
        String validator_name = null;
        String validator_password = null;
        try {
            Statement statement = this.conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                validator_name = result.getString("Doctor_name");
                validator_password = result.getString("doctor_password");
                this.patientID = result.getInt("idPatient");

                if (validator_name.matches(docName) && validator_password.matches(docpw)) {
                    this.valiator = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(db_connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.doc_vali; 
    }

    public void insert(String name, String password) {
        String insert_query = "INSERT INTO patient (idPatient,Patient_name,password)"+"VALUES(NULL,?, ?)";
        try{
            PreparedStatement insert_prep = this.conn.prepareStatement(insert_query);
            insert_prep.setString(1, name);
            insert_prep.setString(2, password);
            insert_prep.execute();
        } catch (SQLException ex) {
            Logger.getLogger(db_connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
