package com.springboot.curso.sprinboot.apirest.repository;

import com.springboot.curso.sprinboot.apirest.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
    List<Usuario> buscarPorNome(String name);
}
