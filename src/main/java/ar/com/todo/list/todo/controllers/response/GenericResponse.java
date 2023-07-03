package ar.com.todo.list.todo.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class GenericResponse<T> {
    private final static String ERROR_CODE = "500";
    private final static String SUCCESS_CODE = "200";
    private final static String SUCCESS_MESSAGE = "The operation was successful";
    @NonNull
    private String code;
    @NonNull
    private String message;
    private T result;

    public static <T> GenericResponse<T> Success(T _result) {
        return new GenericResponse<>(SUCCESS_CODE, SUCCESS_MESSAGE,_result);
    }

    public static <T> GenericResponse<T> Error(String message) {
        return new GenericResponse<>(ERROR_CODE, message);
    }
}
