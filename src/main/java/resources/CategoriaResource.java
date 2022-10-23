package resources;

import domain.Categoria;
import dtos.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import services.CategoriaServices;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    CategoriaServices categoriaServices;
    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id){
        Categoria obj = categoriaServices.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public  ResponseEntity<List<CategoriaDTO>> findAll(){
        List<Categoria> list = categoriaServices.findAll();
        List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    @PostMapping
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria obj){
        obj = categoriaServices.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/(id)").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> uptade(@Valid @PathVariable Integer id, @RequestBody CategoriaDTO objDto){
        Categoria newObj = categoriaServices.uptade(id,objDto);
        return ResponseEntity.ok().body(new CategoriaDTO(newObj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoriaServices.delete(id);
        return ResponseEntity.noContent().build();

    }



}
