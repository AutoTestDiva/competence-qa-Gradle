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
//        user.registerUser("pnata_78@ukr.net", "Pnata1978!");
        cookie = user.getLoginCookie("pnata_78@ukr.net", "Pnata1978!");
    }


    @Test
    public void loginAsUserPositiveTest() {
        AuthResponseDto responseDto = user.loginUserRA("pnata_78@ukr.net", "Pnata1978!")
                .then()
                .assertThat().statusCode(200)//;
                .extract().response().as(AuthResponseDto.class);
        System.out.println(responseDto.getMessage());
    }
    @Test
    public void loginAsUserWithIncorrectPasswordTest() {
        AuthResponseDto responseDto = user.loginUserRA("pnata_78@ukr.net", "Pnata1978@")
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
