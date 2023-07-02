package dev.angelcruzl.authsystem.repository;

import dev.angelcruzl.authsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
