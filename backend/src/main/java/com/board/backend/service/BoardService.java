package com.board.backend.service;

import com.board.backend.model.BoardDto;

public interface BoardService {
    BoardDto.Response get(long id);
    BoardDto.Response insert(BoardDto.Create form);
    BoardDto.Response update(long id, BoardDto.Update form);
    BoardDto.Response delete(long id);
}
