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
@CrossOrigin(origins = "*")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/hi")
    public ResponseEntity hi() {
        return ResponseEntity.ok("hi");
    }

    @GetMapping("/list")
    public ResponseEntity list(BoardDto.RequestList form) {
        final BoardDto.ResponseList list = boardService.list(form);
        final ResponseModel<BoardDto.ResponseList> responseModel = ResponseModel.success(list);
        return ResponseEntity.ok().body(responseModel);
    }

    @GetMapping("/{no}")
    public ResponseEntity get(@PathVariable long no) {
        final BoardDto.Response response = boardService.get(no, true);
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

    @PutMapping("/{no}")
    public ResponseEntity update(@PathVariable long no, @Valid @RequestBody BoardDto.Update form) {
        final BoardDto.Response response = boardService.update(no, form);
        if (response == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final ResponseModel<BoardDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.ok().body(responseModel);
    }

    @DeleteMapping("/{no}")
    public ResponseEntity delete(@PathVariable long no) {
        final BoardDto.Response response = boardService.delete(no);
        if (response == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final ResponseModel<BoardDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.ok().body(responseModel);
    }
}
