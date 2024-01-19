package org.ait.competence.tests.restAssuredTests;

import org.ait.competence.dto.NewPasswordEqualsOldDto;
import org.ait.competence.dto.UserNotAuthenticatedDto;
import org.ait.competence.dto.UserResponseDto;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class UserTestsRA extends TestBaseRA{
    @BeforeMethod
    public void preconditionRA() throws SQLException {
        user.registerUser("nata@gmail.com", "Nata2024!");
    }
    @Test
    public void resetUserPasswordPositiveTestRA1() {
        UserResponseDto userResponseDto = user.resetUserPasswordRA("Nata2024!", "Nata0001!")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(UserResponseDto.class);
        System.out.println(userResponseDto.getMessage());
    }
    @Test
    public void newPasswordEqualsOldTestRA1() {
        NewPasswordEqualsOldDto newPasswordEqualsOld = user.resetUserPasswordRA("Nata2024!", "Nata2024!")
                .then()
                .assertThat().statusCode(400)
                .extract().response().as(NewPasswordEqualsOldDto.class);
        System.out.println(newPasswordEqualsOld.getMessage());
    }

    @Test
    public void userNotAuthenticatedTestRA1() {
        UserNotAuthenticatedDto userNotAuthenticated = user.resetUserPasswordRA("Nata0009!", "Nata0001!")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(UserNotAuthenticatedDto.class);
        System.out.println(userNotAuthenticated.getMessage());
    }

        @AfterMethod
    public void postConditionRA() throws SQLException {
        user.deleteUser("nata@gmail.com");
    }
}
