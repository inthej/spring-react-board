package com.board.backend.controller;

import com.board.backend.common.ErrorCode;
import com.board.backend.model.BoardDto;
import com.board.backend.model.BoardCommentDto;
import com.board.backend.model.ResponseModel;
import com.board.backend.service.BoardCommentService;
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

    @Autowired
    private BoardCommentService boardCommentService;

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
        if (response == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
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

    @GetMapping("/{id}/comments")
    public ResponseEntity comments(@PathVariable long id, BoardCommentDto.RequestList form) {
        final BoardDto.Response boardResponse = boardService.get(id);
        if (boardResponse == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final BoardCommentDto.ResponseList list = boardCommentService.list(id, form);
        final ResponseModel<BoardCommentDto.ResponseList> responseModel = ResponseModel.success(list);
        return ResponseEntity.ok().body(responseModel);
    }

    @GetMapping("/{id}/comment/{cid}")
    public ResponseEntity getComment(@PathVariable long id, @PathVariable long cid) {
        final BoardDto.Response boardResponse = boardService.get(id);
        if (boardResponse == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final BoardCommentDto.Response response = boardCommentService.get(cid);
        if (response == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_COMMENT_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final ResponseModel<BoardCommentDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.ok().body(responseModel);
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity createComment(@PathVariable long id, @Valid @RequestBody BoardCommentDto.Create form) {
        final BoardDto.Response boardResponse = boardService.get(id);
        if (boardResponse == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final BoardCommentDto.Response response = boardCommentService.insert(id, form);
        final ResponseModel<BoardCommentDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }

    @PutMapping("/{id}/comment/{cid}")
    public ResponseEntity updateComment(@PathVariable long id, @PathVariable long cid, @Valid @RequestBody BoardCommentDto.Update form) {
        final BoardDto.Response boardResponse = boardService.get(id);
        if (boardResponse == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final BoardCommentDto.Response response = boardCommentService.update(cid, form);
        if (response == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_COMMENT_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final ResponseModel<BoardCommentDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.ok().body(responseModel);
    }

    @DeleteMapping("/{id}/comment/{cid}")
    public ResponseEntity deleteComment(@PathVariable long id, @PathVariable long cid) {
        final BoardDto.Response boardResponse = boardService.get(id);
        if (boardResponse == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final BoardCommentDto.Response response = boardCommentService.delete(cid);
        if (response == null) {
            final ResponseModel responseModel = ResponseModel.failure(ErrorCode.BOARD_COMMENT_NOT_FOUND);
            return ResponseEntity.ok().body(responseModel);
        }
        final ResponseModel<BoardCommentDto.Response> responseModel = ResponseModel.success(response);
        return ResponseEntity.ok().body(responseModel);
    }
}
