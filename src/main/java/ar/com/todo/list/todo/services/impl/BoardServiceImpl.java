package ar.com.todo.list.todo.services.impl;

import ar.com.todo.list.todo.enums.BoardState;
import ar.com.todo.list.todo.exceptions.BoardNotFoudException;
import ar.com.todo.list.todo.models.Board;
import ar.com.todo.list.todo.repositories.BoardRepository;
import ar.com.todo.list.todo.repositories.entities.BoardEntity;
import ar.com.todo.list.todo.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<Board> getBoards() {
        return StreamSupport.stream(boardRepository.findAll().spliterator(), false)
                .toList().stream().map(Board::from).collect(Collectors.toList());
    }

    @Override
    public Board getBoard(String boardId) throws BoardNotFoudException {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoudException(boardId));
        return Board.from(boardEntity);
    }

    @Override
    public Board saveBoard(Board board) {
        BoardEntity boardEntity = boardRepository.save(BoardEntity.from(board));
        return Board.from(boardEntity);
    }

    @Override
    public Board updateBoard(Board board) throws BoardNotFoudException {
        BoardEntity boardEntity = boardRepository.findById(board.getId()).orElseThrow(() -> new BoardNotFoudException(board.getId()));
        boardEntity.setName(board.getName());
        boardEntity.setState(BoardState.getBoardState(board.getState()));
        return Board.from(boardRepository.save(boardEntity));
    }
}
