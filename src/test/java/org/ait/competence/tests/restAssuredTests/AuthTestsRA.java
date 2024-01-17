package org.ait.competence.tests.restAssuredTests;

import org.ait.competence.dto.ExistEmailResponseDto;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.sql.SQLException;

public class AuthTestsRA extends TestBaseRA {

   @Test()
    public void registerUserPositiveTest() throws SQLException {
        user.registerUser("pnata_78@ukr.net", "Pnata1978!")
                .then()
                .assertThat().statusCode(200);
    }

    @Test()
    public void registerUserWithExistEmailTest() throws SQLException {
        ExistEmailResponseDto existEmail = user.registerUser("vasja.pupkin@competa.test", "userPass007!")
                .then()
                .assertThat().statusCode(400)
                .extract().response().as(ExistEmailResponseDto.class);
        System.out.println(existEmail.getMessage());
    }

    @AfterMethod
    public void postCondition() throws SQLException {
        user.deleteUser("pnata_78@ukr.net");
    }
}
