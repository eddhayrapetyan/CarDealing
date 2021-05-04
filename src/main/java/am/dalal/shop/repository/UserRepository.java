package am.dalal.shop.repository;

import am.dalal.shop.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

}
