package com.board.backend.controller;

import com.board.backend.common.ErrorCode;
import com.board.backend.model.BoardDto;
import com.board.backend.model.ResponseModel;
import com.board.backend.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/hi")
    public ResponseEntity hi() {
        return ResponseEntity.ok("hi");
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable long id) {
        final BoardDto.Response response = boardService.get(id);
        if (response == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final ResponseModel<BoardDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.ok().body(responseModel);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody BoardDto.Create form) {
        final BoardDto.Response response = boardService.insert(form);
        final ResponseModel<BoardDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @Valid @RequestBody BoardDto.Update form) {
        final BoardDto.Response response = boardService.update(id, form);
        final ResponseModel<BoardDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.ok().body(responseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        final BoardDto.Response response = boardService.delete(id);
        if (response == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final ResponseModel<BoardDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.ok().body(responseModel);
    }
}
