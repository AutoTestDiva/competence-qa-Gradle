package org.ait.competence.tests.restAssuredTests;

import io.restassured.http.Cookie;
import org.ait.competence.dto.AuthResponseDto;
import org.ait.competence.dto.LogoutResponseDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static io.restassured.RestAssured.given;

public class AuthenticationTestsRA extends TestBaseRA{

    private Cookie cookie;

    @BeforeMethod
    public void precondition() throws SQLException {
        user.registerUser("vasja.pupkin@competa.test", "userPass007!");
        cookie = user.getLoginCookie("vasja.pupkin@competa.test", "userPass007!");
    }


    @Test
    public void loginAsUserPositiveTest() {
        AuthResponseDto responseDto = user.loginUserRA("vasja.pupkin@competa.test", "userPass007!")
                .then()
                .assertThat().statusCode(200)//;
                .extract().response().as(AuthResponseDto.class);
        System.out.println(responseDto.getMessage());
    }
    @Test
    public void loginAsUserWithIncorrectPasswordTest() {
        AuthResponseDto responseDto = user.loginUserRA("vasja.pupkin@competa.test", "userPass007@")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(AuthResponseDto.class);
        System.out.println(responseDto.getMessage());
    }

    @Test
    public void logoutAsUserPositiveTest() {
        LogoutResponseDto response = given().cookie(cookie).when().post("/api/logout")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(LogoutResponseDto.class);
        System.out.println(response.getMessage());
    }

    /*@AfterMethod
    public void postCondition() throws SQLException {
        user.deleteUser("pnata_78@ukr.net");
    }*/
}
