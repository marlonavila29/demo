package services;

import domain.Categoria;
import domain.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CategoriaRepository;
import repositories.LivroRepository;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;

    public void instanciaBaseDeDados(){

        Categoria cat1 =  new Categoria(null, "Informática", "Livros de TI");
        Livro l1 =  new Livro(null, "Clean Code", "Robert Marlin","Lerem Ipsun", cat1);

        cat1.getLivros().addAll(Arrays.asList(l1));

        this.categoriaRepository.saveAll(Arrays.asList(cat1));
        this.livroRepository.saveAll(Arrays.asList(l1));

    }
}
