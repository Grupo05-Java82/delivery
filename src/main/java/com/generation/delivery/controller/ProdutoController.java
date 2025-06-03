package com.generation.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.delivery.model.Produto;
import com.generation.delivery.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;

//	@Autowired
//	private CategoriaRepository categoriaRepository;

//	@Autowired
//	private UsuarioRepository usuarioRepository;

	@GetMapping()
	private ResponseEntity<List<Produto>> getAllProducts() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	@GetMapping("/{id}")
	private ResponseEntity<Produto> getProductById(@PathVariable long id) {
		if (produtoRepository.existsById(id)) {
			return ResponseEntity.ok(produtoRepository.findById(id).get());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("nomeProduto/{nomeProduto}")
	private ResponseEntity<List<Produto>> getAllProductsByName(@PathVariable String nomeProduto) {
		return ResponseEntity.ok(produtoRepository.findAllProductsByNomeContainsIgnoreCase(nomeProduto));
	}

	@GetMapping("precoMenorQue/{preco}")
	private ResponseEntity<List<Produto>> getAllProductsLessThen(@PathVariable Double preco) {

		if (preco == null) {
			return ResponseEntity.badRequest().build();
		}

		List<Produto> result = produtoRepository.findAllByPrecoIsLessThan(preco);

		return ResponseEntity.ok(result);
	}

	@GetMapping("precoMaiorQue/{preco}")
	private ResponseEntity<List<Produto>> getAllProductsMoreThen(@PathVariable Double preco) {

		if (preco == null) {
			return ResponseEntity.badRequest().build();
		}

		List<Produto> result = produtoRepository.findAllByPrecoIsGreaterThan(preco);

		return ResponseEntity.ok(result);
	}

//	@PostMapping()
//	private ResponseEntity<Produto> create(@Valid @RequestBody Produto produto) {
//		if (produto.getId() == null) {
//			if (categoriaRepository.existsById(produto.getId_categoria().getId())) {
//				if (usuarioRepository.existsById(produto.getId_usuario).getId()) {
//					return ResponseEntity.ok(produtoRepository.save(produto));
//				} else {
//					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//							"O id do usuário vinculado ao produto que esta sendo criado não existe", null);
//				}
//
//			} else {
//				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//						"a categoria que você esta tentando vincular o produto não existe ou nao foi encontrada tente novamente!",
//						null);
//			}
//
//		} else {
//			return ResponseEntity.badRequest().build();
//		}
//
//	}

//	@PutMapping()
//	private ResponseEntity<Produto> put (@Valid @RequestBody Produto produto) {
//		if(produto.getId() != null) {
//			if(produtoRepository.existsById(produto.getId())) { // verifica se o produto tentando ser alterado existe
//				if(categoriaRepository.existsById(produto.getId_categoria().getId()) {  //se a categoria que estamos tentando vincular este produto existir
//					if(usuarioRepository.existsById(produto.getId_usuario().getId()) { //se tambem o usuario que estamos tentando vincular existir
//						return ResponseEntity.ok(produtoRepository.save(produto)); //ai sim persistimos os dados de produto.
//					}
//			}
//		}
//		return ResponseEntity.notFound().build(); //se entrar tiver id na requisição mas uma dessas validações acima nao der certo retorna notFound.
//	}else {  //se o usuario nao mandar id, ja retorna erro de badrequest nesse metodo de ALTERAÇÃO
//		return ResponseEntity.badRequest().build();
//	}
//		
//		
//	
//}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void delete(@PathVariable long id) {
		if (produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
			return;
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}
