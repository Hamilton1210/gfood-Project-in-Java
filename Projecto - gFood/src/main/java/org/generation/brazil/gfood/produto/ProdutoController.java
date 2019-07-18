package org.generation.brazil.gfood.produto;

import org.generation.brazil.gfood.cliente.Cliente;
import org.generation.brazil.gfood.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProdutoController {


    @Autowired
    private ProdutoRepository repository;


    @GetMapping("/Produtos")
    public List<Produto> findAll (){
        // "SELECT * FROM produtos";
        return repository.findAll();

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/Produtos")
    public Produto save(@RequestBody Produto produtosAngola){

        return repository.save(produtosAngola);
    }
    @PutMapping("/Produtos/{id}")
    public Produto saveOrUpdate(@PathVariable Long id, @RequestBody Produto produtoPablo) {
// "UPDATE cliente SET ... WHERE ..."
        return repository.findById(id).map(P -> {
            P.setNome(produtoPablo.getNome());
            P.setDescricao(produtoPablo.getDescricao());
            return repository.save(P);
        }).orElseGet(() -> {
            produtoPablo.setId(id);
            return repository.save(produtoPablo);
        });
    }

    @DeleteMapping("/Produtos/{id}")
    public void delete(@PathVariable Long id) {
        // "DELETE FROM cliente WHERE id = ..."
        repository.deleteById(id);
    }
        }
