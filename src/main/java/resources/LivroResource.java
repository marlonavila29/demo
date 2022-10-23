package resources;

import domain.Livro;
import dtos.LivroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import services.LivroServices;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/Livros")
public class LivroResource {

    @Autowired
    LivroServices livroServices;
    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id){
        Livro obj = livroServices.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public  ResponseEntity<List<LivroDTO>> findAll( @RequestParam(value = "categoria", defaultValue = "0") Integer id_cat){
        List<Livro> list = livroServices.findAll(id_cat);
        List<LivroDTO> listDto = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    @PostMapping
    public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat,@Valid @RequestBody Livro obj){
        Livro newObj = livroServices.create(id_cat,obj);


        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Livro> uptade(@PathVariable Integer id,@Valid @RequestBody Livro obj){
        Livro newObj = livroServices.uptade(id,obj);
        return ResponseEntity.ok().body(newObj);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Livro> uptadePath(@PathVariable Integer id,@Valid @RequestBody Livro obj){
        Livro newObj = livroServices.uptade(id,obj);
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        livroServices.delete(id);
        return ResponseEntity.noContent().build();

    }



}
