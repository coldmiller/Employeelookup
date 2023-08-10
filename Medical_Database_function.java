/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medical_database_function;

import java.sql.SQLException;

/**
 *
 * @author Killrun
 */
public class Medical_Database_function {
    
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
     
    //Sometimes we have to Reee really hard to make this work well, otherwise praise to Lord Emperor, Our undying Lord or Our Undying Lord will declare Exterminatus
    public static  void main(String[] args) throws SQLException { 
        // create a class, called database_connection
        database_Connection db = new database_Connection();
        
        //Connect the database *I hope*
        db.connection();
        
        // Where Doctor do their login
        db.doctor_validator("","");
        
        //Patient login and Patient validator
        db.validator("Kaua Sousa Pinto","test123");
        if(db.getValidator() == false){
            System.out.println("Name or Password is wrong, Try again!");
        }
        
        db.insert_patient_user("test","test");
        //Test print to see if you get out some information
        //db.print();
    }    
}
