package senac.edu.engsoft.meuproduto.controller;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//@ApiResponses(value = {
//        @ApiResponse(code = 200, message = "OK"),
//        @ApiResponse(code = 401, message = "You are not authorized access the resource"),
//        @ApiResponse(code = 404, message = "The resource not found")
//})
public @interface ApiResponsesCommon {

}
