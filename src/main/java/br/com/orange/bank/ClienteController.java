package br.com.orange.bank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ({"/clientes"})
public class ClienteController {

    private ClienteRepository repository;

    ClienteController (ClienteRepository clienteRepository) {
        this.repository = clienteRepository;
    }

//Métodos CRUD:

//Criar um novo cliente (POST /clientes)
@PostMapping
public Cliente create (@RequestBody Cliente cliente) {
    return repository.save(cliente); }

//Listar todos os clientes (GET /clientes)
@GetMapping
public List findAll() {
    return repository.findAll(); }

//Obter um cliente específico pelo id (GET/clientes/{id})
@GetMapping(path = {"/{id}"})
public ResponseEntity findById(@PathVariable long id){
        return (ResponseEntity) repository.findById (id)
                .map (record -> ResponseEntity.ok ().body (record))
                .orElse (ResponseEntity.notFound ().build ()); }

//Atualizar um cliente (PUT /clientes)
@PutMapping(value="/{id}")
public ResponseEntity update(@PathVariable("id") long id,
                             @RequestBody Cliente cliente) {
    return repository.findById (id)
            .map (record -> {
                record.setNome (cliente.getNome ());
                record.setEmail (cliente.getEmail ());
                record.setCPF (cliente.getCPF ());
                record.setDataNasc (cliente.getDataNasc ());
                Cliente updated = repository.save (record);
                return ResponseEntity.ok ().body (updated);
            }).orElse (ResponseEntity.notFound ().build ()); }

//Deletar um cliente pelo id (DELETE/clientes/{id})
@DeleteMapping(path = {"/{id}"})
public ResponseEntity <?> delete(@PathVariable long id) {
        return repository.findById(id)
               .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build()); }
}





