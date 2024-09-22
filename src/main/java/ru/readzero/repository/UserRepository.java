package ru.readzero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.readzero.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
