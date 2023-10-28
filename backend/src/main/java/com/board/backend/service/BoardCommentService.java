package com.board.backend.service;

import com.board.backend.model.BoardCommentDto;

public interface BoardCommentService {
    BoardCommentDto.ResponseList list(long bno, BoardCommentDto.RequestList form);
    BoardCommentDto.Response get(long no);
    BoardCommentDto.Response insert(long bno, BoardCommentDto.Create form);
    BoardCommentDto.Response update(long no, BoardCommentDto.Update form);
    BoardCommentDto.Response delete(long no);
    void deleteAll(long bno);
}
