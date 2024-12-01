package ru.readzero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.readzero.entity.session.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}
