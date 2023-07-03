package ar.com.todo.list.todo.exceptions;

public class SubTaskNotFoundException extends Exception {
    private final static String SUB_TASK_EMPTY = "empty";
    private final static String SUB_TASK_NOT_FOUND_MESSAGE = "sub task with id %s not found";

    public SubTaskNotFoundException(String subTaskId) {
        super(String.format(SUB_TASK_NOT_FOUND_MESSAGE, subTaskId.isEmpty() ? SUB_TASK_EMPTY : subTaskId));
    }
}
