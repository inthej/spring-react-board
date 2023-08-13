package com.board.backend.controller;

import com.board.backend.model.BoardDto;
import com.board.backend.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
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
        if (ObjectUtils.isEmpty(response)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody BoardDto.Create form) {
        final BoardDto.Response response = boardService.insert(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody BoardDto.Update form) {
        BoardDto.Response response = boardService.update(id, form);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        final BoardDto.Response response = boardService.delete(id);
        if (ObjectUtils.isEmpty(response)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok().body(response);
    }
}
