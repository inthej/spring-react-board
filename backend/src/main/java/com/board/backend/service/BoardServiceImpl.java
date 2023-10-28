package com.board.backend.service;

import com.board.backend.common.SqlOrderBuilder;
import com.board.backend.exception.PasswordMismatchException;
import com.board.backend.mapper.BoardCommentMapper;
import com.board.backend.mapper.BoardMapper;
import com.board.backend.model.BoardDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private BoardCommentMapper boardCommentMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BoardDto.ResponseList list(BoardDto.RequestList form) {
        PageHelper.startPage(form.getPage(), form.getSize());

        final SqlOrderBuilder order = SqlOrderBuilder.create(form.getOrder(), form.getDirection());
        PageHelper.orderBy(order.getSql());

        final List<BoardDto.ListItem> list = boardMapper.selectBoardsWithKeyword(form.getKeyword());
        final PageInfo<BoardDto.Response> pageInfo = new PageInfo<>(list);

        return new BoardDto.ResponseList(pageInfo.getTotal(), pageInfo.getPages(), list);
    }

    @Override
    public BoardDto.Response get(long no, boolean incrementViewCount) {
        final BoardDto.Response response = boardMapper.selectBoardById(no);
        if (response == null) {
            return null;
        }

        if (incrementViewCount) {
            boardMapper.incrementViewCountById(no);
        }

        response.setView_count(response.getView_count() + 1);
        return response;
    }

    @Override
    public BoardDto.Response insert(BoardDto.Create form) {
        boardMapper.insertBoard(form);
        return modelMapper.map(form, BoardDto.Response.class);
    }

    @Override
    public BoardDto.Response update(long no, BoardDto.Update form) {
        final BoardDto.Detail detail = boardMapper.selectBoardDetailById(no);
        if (detail == null) {
            return null;
        }

        if (!form.getPassword().equals(detail.getPassword())) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }

        boardMapper.updateBoardById(no, form);
        modelMapper.map(form, detail);
        return modelMapper.map(detail, BoardDto.Response.class);
    }

    @Override
    public BoardDto.Response delete(long no) {
        final BoardDto.Response response = boardMapper.selectBoardById(no);
        boardCommentMapper.deleteAllByBoardId(no);
        boardMapper.deleteBoardById(no);
        return response;
    }
}
