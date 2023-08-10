
package medical_database_function;

import java.sql.SQLException;
import java.util.ArrayList;


class database_Connection {

    private db_connection connection;
    private Boolean validator = false;
    private Boolean doc_vali = false;
    private String collection;
    private ArrayList test;

    public void connection() {
        //Connecting to database, so we can reee about that for amoment
        this.connection = new db_connection();
    }

    public void validator(String name, String pw) throws SQLException {
        //login validator if the user have typed correct name and password, otherwise reee
        if (name != null && pw != null) {
            this.validator = this.connection.login_validator(name, pw);
            //if the validator is true, start to collect all data from treatment table
            if (this.validator == true) {
                this.connection.treatment_collection();
            }
        }
    }

    public Boolean getValidator() {
        return validator;
    }

    //Test print
    public void print() {
        System.out.println("Patient: " + this.connection.getPatient_name());
        for (int i = 0; i < 2; i++) {
            System.out.println("Date: " + this.connection.getDate_visit().get(i).toString());
            System.out.println("reason to visit: " + this.connection.getReason_visit().get(i));
            System.out.println("Treatment: " + this.connection.getTreatment().get(i));
            System.out.println("Next date to visit: " + this.connection.getFuture_date().get(i).toString());
            System.out.println("Doctors name: " + this.connection.getDoctor_name().get(i));
            System.out.println("\n\n");
        }
    }

    public void doctor_validator(String docName, String docpw) {
        if (docName != null && docpw != null) {
            this.doc_vali = this.connection.login_doc_validator(docName, docpw);
        }
    }

    //When patient register
    public void insert_patient_user(String name, String password) {
        if (name != null && password != null) {
            this.connection.insert(name, password);
        }

    }
}
