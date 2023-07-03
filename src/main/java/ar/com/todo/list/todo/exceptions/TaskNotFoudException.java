package ar.com.todo.list.todo.exceptions;

public class TaskNotFoudException extends Exception {
    private final static String TASK_EMPTY = "empty";
    private final static String TASK_NOT_FOUND_MESSAGE = "task with id %s not found";

    public TaskNotFoudException(String taskId) {
        super(String.format(TASK_NOT_FOUND_MESSAGE, taskId.isEmpty() ? TASK_EMPTY : taskId));
    }
}
