# Desafio - Total vendas

De modo geral, a solução para o desafio é baseada em um middleware auxiliado por um sistema de cache. Uma vez requisitada uma consulta sobre o total de vendas em um determinado período, o cálculo do total de vendas é executado, retornado e posteriormente salvo em cache. Toda vez que uma consulta é requisitada, o middleware intercepta a requisição e verifica existência da consulta em cache. Se a consulta já foi realizada uma vez, ela estará em cache e sua resposta é retornada rapidadamente. Se a consulta não está em cache, a requisição é retornada para o controlador responsável.

1) Pré-requisito para execução da aplicação
    * <strong>Gradle 4.0</strong> instalado e configurado.
    * <strong>Java JDK 8</strong> instalado e configurado (configurar também o JAVA_HOME).

2) Executar a aplicação
    * Para executar a aplicação é necessário primeiramente fazer a build do projeto:
      * Dentro do diretório do projeto execute o comando <strong>gradle build</strong> no terminal.
      * Obs: Se alguma dependência falhar no download, você pode utilizar o comando <strong>gradle build --refresh-dependencies</strong> para fazer o download das dependencias novamente.
    * Ao final completo da build, execute a aplicação:
      * Dentro do diretório do projeto execute o comando <strong>gradle bootrun</strong> no terminal.

3) Teste
   * Testes automatizados foram criados através do Junit.
   * Para testes manuais, você pode utilizar os seguintes links:
      * Recupera uma lista com todas as vendas: http://localhost:9000/totalvendas/getVendas
      * Recupera o total de vendas em um determinado período: http://localhost:9000/totalvendas/getTotalVendas?data_inicio=01-07-15&data_fim=30-06-16

4) Frameworks utilizados
   * Springboot: Para configuração de todos os componentes básicos de um projeto java web. Como:
      * Injeção de dependencia
      * Webservices
      * Cache
   * Junit: Para testes automatizados
   * Gson: Para conversão de objetos java em json.
   
5) Banco de dados
   * Foi utilizado o banco de dados H2, que é um banco de dados relacionado em memória já fornecido pelo Springboot, que não precisa ser previamente instalado.
   * Obs: Para esta solução, o ideal é utilizar um banco de dados Nosql, como o Redis. Mas como era necessário realizar sua instalação e configuração em uma máquina, foi preferível utilizar para este protótipo um banco em memória.
