package com.pokemon.pokedex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemon.pokedex.entity.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{

    Boolean existsByName(String name);

    Pokemon findByName(String name);

    Optional<Pokemon> deleteByName(String name);
}
