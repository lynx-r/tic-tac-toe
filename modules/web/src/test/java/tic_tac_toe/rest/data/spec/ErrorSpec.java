package tic_tac_toe.rest.data.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import tic_tac_toe.rest.tools.common.Fields;

import static org.hamcrest.Matchers.is;

public class ErrorSpec {

    public static ResponseSpecification applyErrorSpec(String errorName, String message) {
        return new ResponseSpecBuilder()
                .expectBody(Fields.Error.ERROR_NAME, is(errorName))
                .expectBody(Fields.Error.USER_MESSAGE, is(message))
                .build();
    }
}
