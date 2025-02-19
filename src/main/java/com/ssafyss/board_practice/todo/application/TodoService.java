package com.ssafyss.board_practice.todo.application;

import com.ssafyss.board_practice.todo.dto.CreateTodoRequest;
import com.ssafyss.board_practice.todo.dto.DeleteTodoRequest;
import com.ssafyss.board_practice.todo.dto.ReadTodoRequest;
import com.ssafyss.board_practice.todo.dto.ReadTodoResponse;
import com.ssafyss.board_practice.todo.dto.UpdateTodoRequest;
import java.util.List;

public interface TodoService {
    void createTodo(CreateTodoRequest request);

    List<ReadTodoResponse> readTodos(ReadTodoRequest request);

    void deleteTodo(DeleteTodoRequest request);

    void updateTodo(UpdateTodoRequest request);
}
