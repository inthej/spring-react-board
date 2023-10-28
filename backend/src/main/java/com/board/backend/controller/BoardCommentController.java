package com.board.backend.controller;

import com.board.backend.common.ErrorCode;
import com.board.backend.model.BoardCommentDto;
import com.board.backend.model.BoardDto;
import com.board.backend.model.ResponseModel;
import com.board.backend.service.BoardCommentService;
import com.board.backend.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/{no}")
@CrossOrigin(origins = "*")
public class BoardCommentController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardCommentService boardCommentService;

    @GetMapping("/comments")
    public ResponseEntity list(@PathVariable long no, BoardCommentDto.RequestList form) {
        final BoardDto.Response boardResponse = boardService.get(no, false);
        if (boardResponse == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final BoardCommentDto.ResponseList list = boardCommentService.list(no, form);
        final ResponseModel<BoardCommentDto.ResponseList> responseModel = ResponseModel.success(list);
        return ResponseEntity.ok().body(responseModel);
    }

    @GetMapping("/comment/{cno}")
    public ResponseEntity get(@PathVariable long no, @PathVariable long cno) {
        final BoardDto.Response boardResponse = boardService.get(no, false);
        if (boardResponse == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final BoardCommentDto.Response response = boardCommentService.get(cno);
        if (response == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_COMMENT_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final ResponseModel<BoardCommentDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.ok().body(responseModel);
    }

    @PostMapping("/comment")
    public ResponseEntity create(@PathVariable long no, @Valid @RequestBody BoardCommentDto.Create form) {
        final BoardDto.Response boardResponse = boardService.get(no, false);
        if (boardResponse == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final BoardCommentDto.Response response = boardCommentService.insert(no, form);
        final ResponseModel<BoardCommentDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }

    @PutMapping("/comment/{cno}")
    public ResponseEntity update(@PathVariable long no, @PathVariable long cno, @Valid @RequestBody BoardCommentDto.Update form) {
        final BoardDto.Response boardResponse = boardService.get(no, false);
        if (boardResponse == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final BoardCommentDto.Response response = boardCommentService.update(cno, form);
        if (response == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_COMMENT_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final ResponseModel<BoardCommentDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.ok().body(responseModel);
    }

    @DeleteMapping("/comment/{cno}")
    public ResponseEntity delete(@PathVariable long no, @PathVariable long cno) {
        final BoardDto.Response boardResponse = boardService.get(no, false);
        if (boardResponse == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final BoardCommentDto.Response response = boardCommentService.delete(cno);
        if (response == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_COMMENT_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final ResponseModel<BoardCommentDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.ok().body(responseModel);
    }
}
