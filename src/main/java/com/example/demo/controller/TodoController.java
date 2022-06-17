package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
@Slf4j
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
        try {
//            log.info(dto.getId());
            String temporaryUserId = "temporary-user";

            TodoEntity entity = TodoDTO.toEntity(dto);
            log.info("=======");
            log.info(entity.getId());
//            entity.setId(null);

            entity.setUserId(temporaryUserId);

            List<TodoEntity> entities = todoService.create(entity);

            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .data(dtos)
                    .build();

            return ResponseEntity.ok().body(response);
        } catch(Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .error(error)
                    .build();

            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList(){
        String temporaryUserId = "temporary-user";

        List<TodoEntity> entities = todoService.retrieve(temporaryUserId);

        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                .data(dtos)
                .build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
        String temporaryUserId = "temporary-user";

        TodoEntity entitiy = TodoDTO.toEntity(dto);

        entitiy.setUserId(temporaryUserId);

        List<TodoEntity> entities = todoService.update(entitiy);

        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                .data(dtos)
                .build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {

        try {
            String temporaryUserId = "temporary-user";

            TodoEntity entity = TodoDTO.toEntity(dto);

            entity.setUserId(temporaryUserId);

            List<TodoEntity> entities = todoService.delete(entity);

            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .data(dtos)
                    .build();

            return ResponseEntity.ok().body(response);

        } catch(Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .error(error)
                    .build();

            return ResponseEntity.badRequest().body(response);
        }

    }

}
