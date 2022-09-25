package com.springboot.curso.sprinboot.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.curso.sprinboot.apirest.model.Usuario;
import com.springboot.curso.sprinboot.apirest.repository.UsuarioRepository;


@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String teste(){
        return "SpringBoot";
    }

    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome){

        Usuario usuario = new Usuario();
        usuario.setNome("Fabiano Teixeira");
        usuarioRepository.save(usuario);

        return "Ola mundo" + nome;
    }

    @GetMapping(value = "listartodos")
    @ResponseBody
    public  ResponseEntity<List<Usuario>> listar(){

        List<Usuario> usuario = usuarioRepository.findAll();
        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }

    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Usuario> usuario(@RequestBody Usuario usuario){

        Usuario usu = usuarioRepository.save(usuario);
        return new ResponseEntity<Usuario>(usu, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<String> delete(@RequestParam Long idUser) {

        usuarioRepository.deleteById(idUser);
        return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "buscarPorId")
    @ResponseBody
    public ResponseEntity<Usuario> buscarPorId(@RequestParam (name = "idUser") Long idUser){

        Usuario usuario = usuarioRepository.findById(idUser).get();
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PutMapping(value = "atualizar")
    @ResponseBody
    //Quando tiver ? pode retornar qualquer coisa, pode ser uma String, objeto*/
   public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){

        if(usuario.getId() == null){
            return new ResponseEntity<String>("Usuario não atualizado", HttpStatus.NOT_FOUND);
        }

        Usuario usu = usuarioRepository.saveAndFlush(usuario);
        return new ResponseEntity<Usuario>(usu, HttpStatus.OK);
    }

    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){

        List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
}