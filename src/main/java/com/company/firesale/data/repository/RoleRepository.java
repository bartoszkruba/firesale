package com.company.firesale.data.repository;

import com.company.firesale.data.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findDistinctByDescription(String description);
}
