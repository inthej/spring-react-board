package com.board.backend.service;

import com.board.backend.model.BoardDto;

public interface BoardService {

    BoardDto.ResponseList list(BoardDto.RequestList form);
    BoardDto.Response get(long no, boolean incrementViewCount);
    BoardDto.Response insert(BoardDto.Create form);
    BoardDto.Response update(long no, BoardDto.Update form);
    BoardDto.Response delete(long no);
}
