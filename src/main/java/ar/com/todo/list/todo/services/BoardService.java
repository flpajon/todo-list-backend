package ar.com.todo.list.todo.services;

import ar.com.todo.list.todo.exceptions.BoardNotFoudException;
import ar.com.todo.list.todo.models.Board;

import java.util.List;

public interface BoardService {

    List<Board> getBoards();

    Board getBoard(String boardId) throws BoardNotFoudException;

    Board saveBoard(Board board);

    Board updateBoard(Board board) throws BoardNotFoudException;
}
