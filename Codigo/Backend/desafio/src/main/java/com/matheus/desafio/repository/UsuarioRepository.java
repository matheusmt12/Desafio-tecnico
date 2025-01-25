package com.matheus.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.matheus.desafio.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM USER WHERE USUARIO = :usuario")
    Usuario findByUsuario (@Param("usuario") String usuario);


    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO USER(usuario, senha) VALUES(:usuario,:senha)")
    void insertUsuario(@Param("usuario") String usuario, @Param("senha") String senha);
}
