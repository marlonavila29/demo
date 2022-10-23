package services;

import domain.Categoria;
import domain.Livro;
import dtos.LivroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import repositories.LivroRepository;
import services.excepitons.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServices {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private CategoriaServices categoriaServices;

    public Livro findById(Integer id){
        Optional<Livro> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não foi encontrado! Id: "+ id + "Tipo: " + Livro.class.getName() ));
    }
    public List<Livro> findAll(Integer id_cat){
        categoriaServices.findById(id_cat);
        return repository.findAllByCategoria(id_cat);
    }

    public Livro create(Integer id_cat,Livro obj){
        obj.setId(null);
        Categoria cat = categoriaServices.findById(id_cat);
        obj.setCategoria(cat);
        return repository.save(obj);
    }


    public Livro uptade(Integer id, Livro obj){
        Livro newObj = findById(id);
        uptadeData(newObj, obj);

        return repository.save(newObj);
    }
    public void uptadeData(Livro newObj, Livro obj){
        newObj.setTexto(obj.getTexto());
        newObj.setNomeAutor(obj.getNomeAutor());
        newObj.setTitulo(obj.getTitulo());
    }

    public void delete(Integer id) {
        Livro obj = findById(id);
       // try{
        repository.delete(obj);
        //}catch (DataIntegrityViolationException e){
        //throw  new services.excepitons.DataIntegrityViolationException("Livro não pode ser deletada! Possui livro associados.");
        //}


    }
}
