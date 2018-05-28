# Desafio - Total vendas

1) Pré-requisito para execução da aplicação
    * Gradle 4.0 ou superior instalado e configurado.
   	* Java JDK 8 instalado e configurado.

2) Executar a aplicação
    * Para executar a aplicação é necessário primeiramente fazer a build do projeto:
      * Dentro do diretório do projeto execute o comando <strong>gradle build</strong> no terminal.
      * Obs: Se alguma dependencia falhar, você pode utilizar o comando <strong>gradle build --refresh-dependencies</strong> para fazer o download das dependencias novamente.
    * Ao final completo da build, execute a aplicação:
      * Dentro do diretório do projeto execute o comando <strong>gradle bootrun</strong> no terminal.

3) Frameworks utilizados
   * Springboot: Para configuração de todos os componentes básicos de um projeto java web. Como:
      * Injeção de dependencia
      * Webservices
      * Cache
   * Junit: Para testes automatizados
   * Gson: Para conversão de objetos java em json.
   
4) Banco de dados
   * Foi utilizado o banco de dados H2, que é um banco de dados relacionado em memória já fornecido pelo Springboot, que não precisa ser previamente instalado.
   * Obs: Para esta solução, o ideal é utilizar um banco de dados Nosql, como o Redis. Mas como era necessário realizar sua instalação e configuração em uma máquina, foi preferível utilizar para este protótipo um banco em memória.
