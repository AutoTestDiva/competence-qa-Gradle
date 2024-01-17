package org.ait.competence.fwRA;

import org.ait.competence.dto.NewUserDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;
public class UserHelperRA extends BaseHelperRA{
    public UserHelperRA() {
    }
    public static String loginDataEncoded(String email, String password) {
        String encodedMail;
        String encodedPassword;
        try {
            encodedMail = URLEncoder.encode(email, "UTF-8");
            encodedPassword = URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return "username=" + encodedMail + "&password=" + encodedPassword;
    }

    public static Response loginUserRA(String email, String password) {
        return given()
                .contentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                .body(loginDataEncoded(email, password))
                .when()
                .post("/api/login");
    }
    public Cookie getLoginCookie(String email, String password){
        Response response = given()
                .contentType(ContentType.fromContentType("application/x-www-form-urlencoded"))
                .body(loginDataEncoded(email, password))
                .when()
                .post("/api/login");

        return response.getDetailedCookie("JSESSIONID");
    }
    public Response registerUser(String email,String password){
        NewUserDto user = NewUserDto.builder()
                .email(email)
                .password(password)
                .build();

        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/register");
    }
    public String getUserIdByEmail(String email) throws SQLException {
        String userId;
        try{
            userId = db.requestSelect("SELECT id FROM users WHERE email = \"" + email + "\";")
                    .getString(1);
        } catch (SQLException e){
            userId = null;
            System.out.println("The user is not found" + e);
        }
        return userId;
    }
    private void deleteUserById(String userId) throws SQLException {

        db.requestDelete("DELETE FROM users_roles WHERE users_id = " + userId + ";");
        db.requestDelete("DELETE FROM users_aud WHERE id = " + userId + ";");
        db.requestDelete("DELETE FROM users WHERE id = " + userId + ";");

    }

    public void deleteUser(String email) throws SQLException {
        String userId = getUserIdByEmail(email);
        if(userId != null){
            deleteUserById(userId);
        }
    }
}
