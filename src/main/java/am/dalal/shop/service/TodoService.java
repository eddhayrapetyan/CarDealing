package am.dalal.shop.service;

import am.dalal.shop.entity.TodoEntity;
import am.dalal.shop.entity.UserEntity;
import am.dalal.shop.models.Todo;
import am.dalal.shop.repository.TodoRepository;
import am.dalal.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepo;

    @Autowired
    private UserRepository userRepo;

    public Todo createTodo(TodoEntity todo, Long userId) {
        UserEntity user = userRepo.findById(userId).get();
        todo.setUser(user);
        return Todo.toModel(todoRepo.save(todo));
    }

    public Todo complete(Long id) {
        TodoEntity todo = todoRepo.findById(id).get();
        todo.setCompleted(!todo.getCompleted());
        return Todo.toModel(todoRepo.save(todo));
    }
}