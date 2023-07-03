package ar.com.todo.list.todo.enums;

public enum BoardState {
    BOARD_ACTIVE,
    BOARD_INACTIVE;

    public static BoardState getBoardState(String state) {
        for (BoardState _state : values())
            if (_state.name().equalsIgnoreCase(state)) return _state;
        return BOARD_INACTIVE;
    }

}
