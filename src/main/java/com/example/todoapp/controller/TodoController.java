package com.example.todoapp.controller;

import com.example.todoapp.dto.TodoRequest;
import com.example.todoapp.entity.Todo;
import com.example.todoapp.service.TodoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Todo> createTodo(
            @Valid @RequestBody TodoRequest request) {

        Todo todo = todoService.createTodo(request);

        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {

        return ResponseEntity.ok(todoService.getAllTodos());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(
            @PathVariable Long id) {

        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoRequest request) {

        return ResponseEntity.ok(
                todoService.updateTodo(id, request)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long id) {

        todoService.deleteTodo(id);

        return ResponseEntity.noContent().build();
    }
}
