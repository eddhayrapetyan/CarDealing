package am.dalal.shop.repository;

import am.dalal.shop.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<TodoEntity, Long> {
}
