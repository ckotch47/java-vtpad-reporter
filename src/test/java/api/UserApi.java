package api;

import io.restassured.response.Response;
import pages.DataForLoginApi;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseApi{
    static public Response loginUser(String email, String password){
        DataForLoginApi dataForLoginApi = new DataForLoginApi(email, password);
        return given()
                .spec(baseApi())
                .header("Content-type", "application/json")
                .and()
                .body(dataForLoginApi)
                .when()
                .post("/api/auth/login");
    }
    public static Response getProfile(String accessToken){
        return given()
                .spec(baseApi())
                .header("Authorization", accessToken)
                .when()
                .get("/api/users/profile");
    }
    public static Response deleteProfile(String id, String accessToken){
        return given()
                .spec(baseApi())
                .header("Authorization", accessToken)
                .when()
                .delete("/api/users/" + id);
    }
}