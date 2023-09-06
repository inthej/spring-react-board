package com.board.backend.service;

import com.board.backend.model.BoardReplyDto;

public interface BoardReplyService {
    BoardReplyDto.ResponseList list(long bid, BoardReplyDto.RequestList form);
    BoardReplyDto.Response get(long id);
    BoardReplyDto.Response insert(long bid, BoardReplyDto.Create form);
    BoardReplyDto.Response update(long id, BoardReplyDto.Update form);
    BoardReplyDto.Response delete(long id);
}
