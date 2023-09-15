package com.board.backend.service;

import com.board.backend.model.BoardCommentDto;

public interface BoardCommentService {
    BoardCommentDto.ResponseList list(long bid, BoardCommentDto.RequestList form);
    BoardCommentDto.Response get(long id);
    BoardCommentDto.Response insert(long bid, BoardCommentDto.Create form);
    BoardCommentDto.Response update(long id, BoardCommentDto.Update form);
    BoardCommentDto.Response delete(long id);
    void deleteAll(long bid);
}
