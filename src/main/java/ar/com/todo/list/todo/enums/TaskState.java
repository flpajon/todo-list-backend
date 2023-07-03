package ar.com.todo.list.todo.enums;

public enum TaskState {
    TASK_CREATED,
    TASK_IN_PROGRES,
    TASK_FINISHED,
    TASK_CANCELED;

    public static TaskState getTaskState(String state) {
        for (TaskState _state : values())
            if (_state.name().equalsIgnoreCase(state)) return _state;
        return TASK_IN_PROGRES;
    }
}
