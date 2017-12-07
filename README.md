O projeto é composto das seguintes entidades e relacionamentos entre elas:

![alt text](https://github.com/tiagowanke/nasa-robots/blob/master/src/main/resources/static/img/class-diagram.png)

As classes possuem seus respectivos javadocs.

Motivações para a estrutura desenvolvida:

Classe abstrata Terrain: Para contér todos atributos/métodos necessários para um terreno. Hoje temos apenas Mars, porém amanhã podemos ter outros terrenos com limites diferentes. Para isso basta herdarmos a classe Terrain e definir os limites.

Interface Movable: Todo o objecto que poderá se mover sobre um terreno deverá implementar essa interface. Dessa forma não nos limitamos a escrever nosso código para Robots, podemos ter outros tipos de objectos que podem se mover sobre Terrain com suas características de movimento específicas.


Classe que contém o SpringApplication.run: ApplicationConfiguration.

Cobertura de código:

![alt text](https://github.com/tiagowanke/nasa-robots/blob/master/src/main/resources/static/img/coverage.png)
