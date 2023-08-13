package com.board.backend.controller;

import com.board.backend.model.BoardDto;
import com.board.backend.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody BoardDto.Create form) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody BoardDto.Update form) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok().build();
    }
}
