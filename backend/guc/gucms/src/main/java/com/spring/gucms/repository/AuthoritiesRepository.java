package com.spring.gucms.repository;

import com.spring.gucms.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities,Long> {
    //public Role findByRoleName(String roleName);
}
