# Delivery - API Backend

<br />

<div align="center">
   <img src="https://i.imgur.com/6BZsRoI.png" title="source: imgur.com" /> 
</div>

<br /><br />

## Equipe Desenvolvedora

* [**Lucas Manrick Teodoro da Fonseca**](https://github.com/lucasmanrick)
* [**Luiz Henrique dos Santos**](https://github.com/luizsantos7)
* [**Pablo Casagrande**](https://github.com/Pablo-Casagrande)
* [**Carlos Moroni**](https://github.com/carlosmoronisud)
* [**Bruno Alves**](https://github.com/BrunoAlves-tech)
* [**Natan Macedo**](https://github.com/natanmac)
* [**Murilo Mattos**](https://github.com/Matttosz)

---

## 1. Descrição

O **Delivery** é um sistema (API) desenvolvido para cadastrar e buscar produtos alimentícios. Sua principal função é armazenar e organizar dados essenciais, como informações pessoais do usuário, histórico de interações e perfil de consumo, proporcionando uma visão completa de produtos e suas caracteristicas.

---

## 2. Sobre esta API

A API desenvolvida atua como o **núcleo** da aplicação **Connect**, realizando a gestão eficiente de dados dos produtos, categorias e usuários. Com um conjunto completo de operações CRUD (Create, Read, Update, Delete), ela permite que as informações trafeguem pelo sistema de maneira organizada e segura, implementando a parte de security com login, documentação Swagger e deploy na aplicação via Render.



### 2.1. Principais Funcionalidades

**🔍 CONSULTAS (GET)**

1. **Todos os produtos e categorias** — permite consultar todos os produtos e suas respectivas categorias.

2. **produto/categoria pelo ID** — permite consultar um produtos específico com base em seu ID (identificador único).

3. **Produto/categoria pelo nome** — permite consultar um produtos ou categorias pelo seu nome.

4. **Todos as Usuários** — permite consultar todos os usuários.

5. **Usuários pelo ID** — permite consultar um usuário específico.
   
   <br>

**✏️ REGISTROS (POST)**

1. **Cadastro de usuário** — permite o cadastro de novos usuários.
2. **Cadastro de Produtos** — permite o registro de novos produtos de comida.
3. **Cadastro de categoria** — permite adicionar novas categorias ao sistema.



**🔄 ATUALIZAÇÕES (PUT)**

1. **Atualizar produto pelo ID** — permite a atualização de dados de um produto através de seu ID.
2. **Atualizar categoria pelo ID** — permite a atualização de informações de uma categoria.
3. **Atualizar dados de um usuário pelo ID** — permite a atualização de dados de um usuário.



**🗑️ DELETAR (DELETE)**

1. **Excluir um cliente pelo ID** — permite remover um cliente do sistema com base em seu ID.
2. **Excluir uma oportunidade pelo ID** — permite remover uma oportunidade do sistema.
   
   

---

## 3. Diagrama de Classes



<div align="center">
   <img src="https://i.imgur.com/4WrtHM3.png" alt="Diagrama de Classe"/>
</div>

---

## 4. Tecnologias Utilizadas

| Item                          | Descrição |
| ----------------------------- | --------- |
| **Servidor**                  | JAVA      |
| **Linguagem de Programação**  | JAVA      |
| **Framework**                 | SPRING    |
| **ORM**                       | HIBERNATE |
| **Banco de Dados Relacional** | MySQL     |

---

## 5. Configuração e Execução

1. Clone o repositório:
   
   ```bash
   git clone https://github.com/Grupo05-Java82/crm_connect.git
   ```