package com.example.demo.service.impl;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import com.example.demo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public String testService() {
        TodoEntity entity = TodoEntity.builder()
                .title("my first todo item")
                .build();

        todoRepository.save(entity);

        TodoEntity savedEntity = todoRepository.findById(entity.getId()).get();

        return savedEntity.getTitle();
    }

    @Override
    public List<TodoEntity> create(final TodoEntity entity) {

        //validations
        validate(entity);
        todoRepository.save(entity);

        log.info("Entity Id : {} is saved", entity.getId());

        return todoRepository.findByUserId(entity.getUserId());
    }
    private void validate(TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user.");
        }
    }

    public List<TodoEntity> retrieve(final String userId) {
        return todoRepository.findByUserId(userId);
    }

    @Override
    public List<TodoEntity> update(TodoEntity entity) {
        validate(entity);

        final Optional<TodoEntity> original = todoRepository.findById(entity.getId());

        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            todoRepository.save(todo);
        });

        return retrieve(entity.getUserId());
    }

    @Override
    public List<TodoEntity> delete(TodoEntity entity) {
        validate(entity);

        try {
            todoRepository.delete(entity);
        } catch (Exception e){
            log.error("error deleting entity ={}, {}", entity.getId(), e);
            throw new RuntimeException("error deleting entity " + entity.getId());
        }

        return retrieve(entity.getUserId());
    }
}
