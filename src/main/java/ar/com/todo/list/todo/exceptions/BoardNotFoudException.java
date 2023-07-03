package ar.com.todo.list.todo.exceptions;

public class BoardNotFoudException extends Exception {
    private final static String BOARD_EMPTY = "empty";
    private final static String BOARD_NOT_FOUND_MESSAGE = "board with id %s not found";

    public BoardNotFoudException(String boardId) {
        super(String.format(BOARD_NOT_FOUND_MESSAGE, boardId.isEmpty() ? BOARD_EMPTY : boardId));
    }
}
