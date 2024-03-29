package org.ait.competence.tests.restAssuredTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


public class AuthTestsPositiveRegistrationRA extends TestBaseRA{
    @Test()
    public void registerUserPositiveTestRA1() throws SQLException {
        user.registerUser("nata@gmail.com", "Nata2024!")
                .then()
                .assertThat().statusCode(200);
    }

//    @Test()            //тоже рабочий тест, но взаимоисключающий с TestRA1 !!!
//    public void a_registerUserPositiveTestRA2() throws SQLException {
//        user.registerUser("nata@gmail.com", "Nata2024!")
//                .then()
//                .assertThat().statusCode(200)
//                .assertThat().body("email", containsString("nata@gmail.com"))
//                .assertThat().body("email", equalTo("nata@gmail.com"));
//    }

    @AfterMethod
    public void postConditionRA() throws SQLException {
        user.deleteUser("nata@gmail.com");
    }
}
