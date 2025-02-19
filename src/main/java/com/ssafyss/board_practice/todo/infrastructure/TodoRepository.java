package com.ssafyss.board_practice.todo.infrastructure;

import com.ssafyss.board_practice.todo.dto.ReadTodoResponse;
import com.ssafyss.board_practice.todo.dto.UpdateTodoRequest;
import com.ssafyss.board_practice.todo.entity.Todo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoRepository {
    void insert(Todo todo);

    List<ReadTodoResponse> findByUserId(Long userId);

    int deleteById(Long id);

    void update(UpdateTodoRequest request);
}
