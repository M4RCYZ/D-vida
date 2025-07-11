# 💸 Dívidas - App de Controle Financeiro Pessoal

Um aplicativo Android simples e com design minimalista, desenvolvido em Java, para ajudar no controle de pequenas dívidas e empréstimos pessoais entre múltiplas pessoas.

Este projeto foi criado como um exercício prático, aplicando conceitos modernos de desenvolvimento Android, desde a persistência de dados local com relacionamentos até a criação de uma interface de usuário reativa e multiusuário.

## ✨ Funcionalidades

* 👥 **Gerenciamento de Múltiplos Usuários:** O aplicativo suporta perfis de usuário separados, permitindo que várias pessoas controlem suas dívidas de forma independente.
* 👤 **Criação e Seleção de Usuário:** Uma interface simples permite criar novos usuários e um menu na tela principal facilita a alternância entre eles.
* 🌗 **Suporte a Modo Escuro (Dark Mode):** O aplicativo responde automaticamente ao tema do sistema, alternando entre um layout claro e um escuro para maior conforto visual.
* 📊 **Visualização Filtrada e em Tempo Real:** A lista de dívidas e o card de total são atualizados instantaneamente para mostrar **apenas** os dados do usuário selecionado no momento.
* 💸 **Adição Rápida de Dívidas:** Um diálogo claro permite registrar novas dívidas (com descrição e valor) para o usuário ativo.
* 🗑️ **Deleção Intuitiva com Confirmação:** Cada item da lista possui um ícone para remoção, com um diálogo de confirmação para evitar exclusões acidentais.
* 💾 **Persistência de Dados:** As informações de usuários e dívidas são salvas localmente usando o banco de dados **Room**.
* 🎨 **Design Minimalista:** Interface para uma experiência de usuário limpa e agradável.

## 🛠️ Tecnologias e Arquitetura

* **Linguagem:** Java
* **Arquitetura:** **MVVM (Model-View-ViewModel)**, promovendo uma clara separação de responsabilidades entre a UI, a lógica de apresentação e a camada de dados.
* **Design e UI:**
  * **XML Layouts:** Construção de interfaces com suporte a temas.
  * **Theming:** Uso de temas `DayNight` e diretórios de recursos qualificados (`values-night`) para alternância automática de tema.
  * **Material Components:** Para elementos visuais como `FloatingActionButton` e `AlertDialog`.
  * **RecyclerView:** Para a exibição eficiente e otimizada da lista de dívidas.
* **Componentes do Android Jetpack:**
  * **Room Persistence Library:** Utilizado para criar e gerenciar um banco de dados SQLite local, com múltiplas entidades e relacionamentos.
  * **LiveData:** Para criar fluxos de dados observáveis que atualizam a UI reativamente (`Transformations.switchMap` foi um componente chave).
  * **ViewModel:** Componente central da arquitetura para preservar o estado da UI.
* **Build System:** **Gradle** com **Version Catalogs** (`libs.versions.toml`) para um gerenciamento de dependências moderno e centralizado.


## 🚀 O que vem por aí? (Ideias Futuras)

* [ ] Implementar funcionalidade de **editar** uma dívida existente.
* [ ] Adicionar **categorias** para as dívidas (lazer, alimentação, etc.).
* [ ] Criar uma tela de **relatórios** com gráficos simples.