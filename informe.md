<a id="informe-top"></a>
<br>
<div align="center">

<h1 align="center">Tilting Tiles</h1>

  <p align="center">
    Informe de diseño
    <br />
  </p>
</div>

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#resume">Resume</a>
    </li>
    <li>
      <a href="#board">Board</a>
    </li>
    <li>
      <a href="#tiles">Tiles</a>
      <ul>
        <li><a href="#normal">Normal</a></li>
        <li><a href="#fixed">Fixed</a></li>
        <li><a href="#rough">Rough</a></li>
        <li><a href="#flying">Flying</a></li>
        <li><a href="#freelance">Freelance</a></li>
        <li><a href="#temporal">Temporal</a></li>
      </ul>
    </li>
    <li>
      <a href="#holes">Holes</a>
    </li>
    <li>
      <a href="#glues">Glues</a>
      <ul>
        <li><a href="#normal-glue">Normal</a></li>
        <li><a href="#fragil">Fragil</a></li>
        <li><a href="#super">Super</a></li>
      </ul>
    </li>
  </ol>
</details>

</br>
</br>



## RESUME
**Tilting Tiles** es un sistema que emula un tablero interactivo, el cual, utiliza diferentes tipos de baldosas (*Tiles*) y elementos como huecos (*Holes*) y pegamentos especiales (*Glues*) para crear un entorno dinámico y versátil. El proyecto se estructura en torno a un **Board** principal, que organiza y gestiona estos componentes, y permite una experiencia de interacción basada en el "*tilt*" o **inclinación** de las baldosas, que resulta en diferentes comportamientos dependiendo del tipo de baldosa o pegamento aplicado.
<br>
<br>

## Board
El tablero es el área principal donde se organizan las baldosas y se aplican los movimientos de inclinación. Proporciona la **base gráfica y funcional** para los distintos tipos de interacción entre baldosas y otros elementos. En este caso, se decidió por utilizar una matriz de dimensiones variables o fijas por cada instancia. De este modo, podemos organizar las *Tiles* en base a un par de ordenadas xy en un tablero, en otro podemos organizar los *Holes* y lo mismo con los *Glue*, es decir, cada tipo de objeto que tenemos en el proyecto tiene su propia matriz representante, todas ellas conviviendo al mismo tiempo para no tener afecciones gráficas.
<br>
<br>

## Tiles
Existen múltiples tipos de baldosas, cada una con propiedades y comportamientos únicos. Pero en general comparten algunas características; todas se identifican por su instancia y posiciones *xy* en el tablero, se llaman desde una función propia para crear cada una de ellas, todas ocupan un lugar en el tablero y ninguna tiene forma de perdurar o mantenerse durante todo el juego. Las diferencias entre estas son las siguientes:

<br>

### Normal
Son una *Tile* que no tiene ninguna característica extra, pues es la **base** para todas las demás. En esta, se planteo lo pueden como lo siguiente
- **Moverse con el Tilt**: sin restricciones mas allá de las eliminaciones/caídas producidas por un hueco. 
- **Caída en los huecos**: eliminan la casilla.
- **Relocalización: es posible mover su posición a otra deseada por el usuario.
- **Eliminación**: puede ser destruida si el usuario lo quiere.
- **Pegamento**: Es capaz de recibir y ser afectada por cualquier tipo de pegamento.
<br>

### Fixed

</br>

### Rough

</br>

### Flying

</br>

### Freelance

</br>

### Temporal

</br>
</br>

## Holes

</br>
</br>

## Glues
Es un tipo de pegamento especial que se aplica en una baldosa específica y provoca que esta y sus baldosas vecinas se adhieran entre sí, 
formando una conexión. La clase Glues gestiona estas instancias de pegamento y ofrece la función recognizeGlue para manejar la lógica 
de adhesión entre baldosas.

</br>

### Normal
Este pegamento simplemente hara que el comportamiento del tilt sea mucho mas 

<br>

### Fragil
Este pegamento es muy similar al normal, el tema es que solamente aguanta 1 tilt. Es decir, cuando se aplica tiene un comportamiento normal,
pero al realizar un movimiento, este pierde sus cualidades de pegante y tecnicamente se destruiria en el siguiente.
</br>
Para poder implementar este tipo de pegamento, la clase heredada solo tuvo que añadir un contador interno que verificara la cantidad de
movimientos realizados por el pegante, en el momento que llegase a 2 arrancando desde 1, 

### Super

