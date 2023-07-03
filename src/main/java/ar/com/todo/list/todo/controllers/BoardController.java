package ar.com.todo.list.todo.controllers;

import ar.com.todo.list.todo.controllers.response.GenericResponse;
import ar.com.todo.list.todo.models.Board;
import ar.com.todo.list.todo.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public ResponseEntity<GenericResponse<List<Board>>> getBoards() {
        try {
            GenericResponse.Success(boardService.getBoards());
            return new ResponseEntity<>(GenericResponse.Success(boardService.getBoards()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(GenericResponse.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{board_id}")
    public ResponseEntity<GenericResponse<Board>> getBoard(@PathVariable("board_id") String boardId) {
        try {
            return new ResponseEntity<>(GenericResponse.Success(boardService.getBoard(boardId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(GenericResponse.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<GenericResponse<Board>> saveBoard(@RequestBody Board board) {
        try {
            return new ResponseEntity<>(GenericResponse.Success(boardService.saveBoard(board)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(GenericResponse.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<GenericResponse<Board>> updateBoard(@RequestBody Board board) {
        try {
            return new ResponseEntity<>(GenericResponse.Success(boardService.updateBoard(board)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(GenericResponse.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
