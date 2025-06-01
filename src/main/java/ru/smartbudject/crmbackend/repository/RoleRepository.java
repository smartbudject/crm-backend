package ru.smartbudject.crmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.smartbudject.crmbackend.model.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
