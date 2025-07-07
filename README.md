# 💸 Dívidas - App de Controle Financeiro Pessoal

Um aplicativo Android simples e com design minimalista, desenvolvido em Java, para ajudar no controle de pequenas dívidas e empréstimos pessoais.

Este projeto foi criado como um exercício prático, aplicando conceitos modernos de desenvolvimento Android, desde a persistência de dados local até a criação de uma interface de usuário limpa e reativa.

## ✨ Funcionalidades

* **Adicionar Dívidas:** Adicione novas dívidas facilmente através de uma caixa de diálogo, informando a descrição e o valor.
* **Visualização Clara:** A lista principal mostra todas as dívidas pendentes, com data, descrição e valor.
* **Total em Tempo Real:** Um card no topo da tela exibe a soma total de todas as dívidas, atualizada automaticamente.
* **Deleção Intuitiva:** Remova uma dívida "paga" simplesmente clicando no ícone de lixeira ao lado dela.
* **Persistência de Dados:** As informações são salvas localmente no celular usando o banco de dados Room, garantindo que os dados não se percam ao fechar o app.
* **Design Minimalista:** Interface inspirada nos princípios de design do iOS para uma experiência de usuário limpa e agradável.

## 🛠️ Tecnologias e Arquitetura

Este projeto foi construído utilizando as seguintes tecnologias e bibliotecas do ecossistema Android:

* **Linguagem:** **Java**
* **Arquitetura:** **MVVM (Model-View-ViewModel)**, separando a lógica de UI da lógica de negócios e dados.
    * **UI Layer:** Activities, XML Layouts, RecyclerView, Adapter.
    * **ViewModel:** `androidx.lifecycle.ViewModel` para gerenciar o estado da UI de forma consciente ao ciclo de vida.
    * **Data Layer:** Padrão de Repositório (`Repository`) para mediar o acesso aos dados.
* **Persistência de Dados:** **Room** (`androidx.room`) para criar e gerenciar um banco de dados SQLite local de forma robusta.
* **Componentes Reativos:** **LiveData** (`androidx.lifecycle.LiveData`) para criar fluxos de dados observáveis que atualizam a UI automaticamente quando os dados mudam.
* **Componentes de UI:** **Material Components** para componentes visuais como o `FloatingActionButton` e caixas de diálogo.
* **Build System:** **Gradle** com **Version Catalogs** (`libs.versions.toml`) para um gerenciamento de dependências moderno e centralizado.

## 🚀 O que vem por aí? (Ideias Futuras)

* [ ] Implementar funcionalidade de **editar** uma dívida existente.
* [ ] Adicionar **categorias** para as dívidas (lazer, alimentação, etc.).
* [ ] Criar uma tela de **relatórios** com gráficos simples.
* [ ] Adicionar um tema escuro (Dark Mode).