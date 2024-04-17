package com.vocco.api.domain.administrador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    List<Administrador> findAllByAtivoTrue();
}
