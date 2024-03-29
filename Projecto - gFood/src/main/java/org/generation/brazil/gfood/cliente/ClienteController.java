package org.generation.brazil.gfood.cliente;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository repository;


    @GetMapping("/clientes")
    public List<Cliente> findAll (){
        // "SELECT * FROM clientes";
        return repository.findAll();

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/clientes")
    public Cliente save(@RequestBody Cliente cliente){

        return repository.save(cliente);
    }
    @PutMapping("/clientes/{id}")
    public Cliente saveOrUpdate(@PathVariable Long id, @RequestBody Cliente cliente) {
// "UPDATE cliente SET ... WHERE ..."
        return repository.findById(id).map(c -> {
            c.setNome(cliente.getNome());
            c.setEndereco(cliente.getEndereco());
            return repository.save(c);
        }).orElseGet(() -> {
            cliente.setId(id);
            return repository.save(cliente);
        });
    }

    @DeleteMapping("/clientes/{id}")
    public void delete(@PathVariable Long id) {
        // "DELETE FROM cliente WHERE id = ..."
        repository.deleteById(id);
    }

}
