/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.example.PDF.PDFViewAndPrintWithJS.testrun;

/**
 *
 * @author VERNON
 */
public class Person {
    private String firstName;
       private String lastName;
       private String email;
 
       /* Person(String fName, String lName, String email) {
            this.firstName = new String(fName);
            this.lastName = new String(lName);
            this.email = new String(email);
        }*/

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
       
       

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
       
}
