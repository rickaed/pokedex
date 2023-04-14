package com.pokemon.pokedex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.pokedex.entity.Pokemon;
import com.pokemon.pokedex.repository.PokemonRepository;

@RestController
public class PokemonController {
    

    @Autowired
    private PokemonRepository pokemonRepository;

    @PostMapping("create")
    public ResponseEntity<?> register(@RequestBody Pokemon pokemon) {
        if (pokemonRepository.existsByName(pokemon.getName())) {

            return new ResponseEntity<>("Ce pokemon existe déja, veuillez en choisir un autre", HttpStatus.BAD_REQUEST);
        }
        Pokemon newPokemon = new Pokemon();
        newPokemon.setName(pokemon.getName());
        newPokemon.setType(pokemon.getType());
        pokemonRepository.save(newPokemon);
        return new ResponseEntity<>("Nouveau pokemon ajouté au pokedex", HttpStatus.OK);
    }

    @GetMapping("pokedex")
    public ResponseEntity<?> readAllPokemon() {

        List<Pokemon> pokemonList = pokemonRepository.findAll();

        if (pokemonList.isEmpty()) {
            return new ResponseEntity<>("Le pokedex est vide", HttpStatus.OK);
        }
        return new ResponseEntity<List<Pokemon>>(pokemonList, HttpStatus.OK);
    }

    @GetMapping("{pokemonName}")
    public ResponseEntity<?> getPokemon(@PathVariable String pokemonName) {

        if (pokemonRepository.existsByName(pokemonName)) {
            Pokemon pokemon = pokemonRepository.findByName(pokemonName);
            return new ResponseEntity<Pokemon>(pokemon, HttpStatus.OK);
        }
        return new ResponseEntity<>("ce pokemon n'existe pas", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("pokemon")
    public ResponseEntity<?> updatePokemon(@RequestBody Map<String, String> mapRequest) {
        String name = mapRequest.get("name");
        String newName = mapRequest.get("newName");
        Pokemon pokemon = pokemonRepository.findByName(name);
        if (pokemonRepository.existsByName(name)) {
            pokemon.setName(newName);
            pokemonRepository.save(pokemon);

            return new ResponseEntity<>("Le nom du pokemon à été modifier", HttpStatus.OK);
        }

        return new ResponseEntity<>("ce pokemon n'existe pas", HttpStatus.BAD_REQUEST);
    }

    // @DeleteMapping("{pokemonName}")
    // public ResponseEntity<?> killPokemon(@PathVariable String pokemonName) {
    //     if (pokemonRepository.existsByName(pokemonName)) {
    //         pokemonRepository.deleteByName(pokemonName);
    //         return new ResponseEntity<>("ce pokemon a été exécuté", HttpStatus.OK);
    //     }
    //     return new ResponseEntity<>("ce pokemon est déjà mort ou n'a jamais", HttpStatus.BAD_REQUEST);
    // }

    @GetMapping("pokedex/{name}")
    public String deletePokemon(@PathVariable String name) {
        pokemonRepository.deleteByName(name);
        return "pokemon supprimé";
    }

}
