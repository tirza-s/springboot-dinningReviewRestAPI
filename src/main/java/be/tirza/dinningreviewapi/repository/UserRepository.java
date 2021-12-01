package be.tirza.dinningreviewapi.repository;

import be.tirza.dinningreviewapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
