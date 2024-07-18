package io.justina.h106javareact.adapters.repositories;

import io.justina.h106javareact.domain.entities.User;
import io.justina.h106javareact.domain.entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByIdAndActive(String id, Boolean active);
    Optional<User> findByEmailAndActive(String email, Boolean active);
    Optional<User> findByEmail(String email); //Necesario para corroborar previo a crear.

    List<User> findByRoleAndActive(Role role, Boolean active);
}

