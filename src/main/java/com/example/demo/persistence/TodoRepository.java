package com.example.demo.persistence;

import com.example.demo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//인터페이스 끼리 상속가능
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    List<TodoEntity> findByUserId(String userId);

//    @Query("select * from Todo t where t.userId = ?1")
//    List<TodoEntity> findByUserId2(String userId);
}
