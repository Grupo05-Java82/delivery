package com.generation.delivery.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 1, max = 50, message = "O nome do produto deve conter entre 1 e 50 caracteres") // Adicionado min
	@NotBlank(message = "O Atributo Nome da tabela produto é Obrigatório!")
	private String nome;
	
	@Column(precision = 10, scale = 2) // Garante 10 dígitos no total, 2 após a vírgula
	@NotNull(message = "O preço do produto é obrigatório")
	@DecimalMin(value = "0.0", inclusive = false, message = "O preço do produto deve ser maior que zero.")
	private BigDecimal preco;
	
	
	@Size(max = 5000, message = "O tamanho maximo do atributo imagem é de 5000 caracteres")
	private String imagem;
	

	@Size(min=1, max=1, message = "O atributo nutriscore deve ser A,B,C,D,E") // Ajustado tamanho para mais flexibilidade
	private char nutriScore;
	
	@Size(min = 1, max=255, message = "O atributo ingrediente deve ter entre 1 e 255 caracteres") // Adicionado min
	@NotBlank(message = "O Atributo ingrediente é obrigatório para sabermos a descrição do produto")
	private String ingrediente;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria") // Nome da coluna da chave estrangeira
	@JsonIgnoreProperties("produto") // Corrigido para corresponder à lista em Usuario
	private Categoria id_categoria;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario") // Nome da coluna da chave estrangeira
	@JsonIgnoreProperties("produto") // Corrigido para corresponder à lista em Usuario
	private Usuario id_usuario;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}


	public char getNutriScore() {
		return nutriScore;
	}

	public void setNutriScore(char nutriScore) {
		this.nutriScore = nutriScore;
	}

	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Categoria getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Categoria id_categoria) {
		this.id_categoria = id_categoria;
	}

	public Usuario getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.id_usuario = id_usuario;
	}


}
