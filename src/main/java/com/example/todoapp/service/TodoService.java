package com.example.todoapp.service;

import com.example.todoapp.dto.TodoRequest;
import com.example.todoapp.entity.Todo;
import com.example.todoapp.repository.TodoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // CREATE
    public Todo createTodo(TodoRequest request) {

        Todo todo = new Todo();

        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCompleted(request.isCompleted());

        return todoRepository.save(todo);
    }

    // READ ALL
    public List<Todo> getAllTodos() {

        return todoRepository.findAll();
    }

    // READ BY ID
    public Todo getTodoById(Long id) {

        return todoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Todo not found with id: " + id
                        )
                );
    }

    // UPDATE
    @Transactional
    public Todo updateTodo(Long id, TodoRequest request) {

        Todo todo = getTodoById(id);

        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCompleted(request.isCompleted());

        return todoRepository.save(todo);
    }

    // DELETE
    public void deleteTodo(Long id) {

        Todo todo = getTodoById(id);

        todoRepository.delete(todo);
    }
}
