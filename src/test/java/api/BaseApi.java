package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseApi {
    public static RequestSpecification baseApi(){
        return new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("https://dev.k8s.ponimayu.qtim.ru/").build();
    }
}