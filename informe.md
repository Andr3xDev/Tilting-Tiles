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
Son una *Tile* que no tiene ninguna característica extra, pues es la **base** para todas las demás. En esta, se planteo de la siguiente manera
- **Moverse con el Tilt**: sin restricciones mas allá de las eliminaciones/caídas producidas por un hueco. 
- **Caída en los huecos**: eliminan la casilla.
- **Relocalización**: es posible mover su posición a otra deseada por el usuario.
- **Eliminación**: puede ser destruida si el usuario lo quiere.
- **Pegamento**: Es capaz de recibir y ser afectada por cualquier tipo de pegamento.
<br>

### Fixed
Son una *Tile* cuya caracteristica es el no poder reubicarse ni eliminarse. En esta, se heredo de la clase de *Tile* original y se planteo de la siguiente manera
- **Moverse con el Tilt**: sin restricciones mas allá de las eliminaciones/caídas producidas por un hueco. 
- **Caída en los huecos**: eliminan la casilla.
- **Relocalización**: No es posible relocalizar, para esto sobreescribimos el metodo *RelocateTile()* para que en lugar de trasladar la pieza lance una excepción.
- **Eliminación**: No es posible eliminar, para esto sobreescribimos el metodo *DeleteTile()* para que en lugar de eliminar la pieza lance una excepcion
- **Pegamento**: Es capaz de recibir y ser afectada por cualquier tipo de pegamento.
</br>

### Rough
Son una *Tile* cuya caracteristica es el no poder desplazarse con el metodo *Tilt()*. En esta, se heredo de la clase de *Tile* original y se planteo de la siguiente manera
- **Moverse con el Tilt**: No es posible cambiar su ubicacion con este metodo, para ello lo sobreescribimos para que en lugar de tener un comportamiento lance una excepcion. 
- **Caída en los huecos**: eliminan la casilla.
- **Relocalización**: es posible mover su posición a otra deseada por el usuario.
- **Eliminación**: puede ser destruida si el usuario lo quiere.
- **Pegamento**: Es capaz de recibir y ser afectada por cualquier tipo de pegamento.

</br>

### Flying
Son una *Tile* cuya caracteristica que al desplazarse con el metodo *Tilt()* ignore los huecos. En esta, se heredo de la clase de *Tile* original y se planteo de la siguiente manera
- **Moverse con el Tilt**: sin restricciones incluso queda por encima de los huecos. 
- **Caída en los huecos**: No cae en los huecos por ende no se elimina y se superpone a ellos
- **Relocalización**: es posible mover su posición a otra deseada por el usuario.
- **Eliminación**: puede ser destruida si el usuario lo quiere.
- **Pegamento**: Es capaz de recibir y ser afectada por cualquier tipo de pegamento.
</br>

### Freelance
Son una *Tile* cuya caracteristica es no ser afectada por pegamento. En esta, se heredo de la clase de *Tile* original y se planteo de la siguiente manera
- **Moverse con el Tilt**: sin restricciones mas allá de las eliminaciones/caídas producidas por un hueco. 
- **Caída en los huecos**: eliminan la casilla.
- **Relocalización**: es posible mover su posición a otra deseada por el usuario.
- **Eliminación**: puede ser destruida si el usuario lo quiere.
- **Pegamento**: No es capaz de recibir y ser afectada por cualquier tipo de pegamento.
</br>

### Temporal
Son una *Tile* cuya caracteristica es que al usar un *Tilt()* son eliminadas. En esta, se heredo de la clase de *Tile* original y se planteo de la siguiente manera
- **Moverse con el Tilt**: mas allá de las eliminaciones/caídas producidas por un hueco, solo se puede utilizar una vez por que posterior a esto la pieza es eliminada. 
- **Caída en los huecos**: eliminan la casilla.
- **Relocalización**: es posible mover su posición a otra deseada por el usuario.
- **Eliminación**: puede ser destruida si el usuario lo quiere.
- **Pegamento**: Es capaz de recibir y ser afectada por cualquier tipo de pegamento.
</br>
</br>
</br>

## Holes
Son agujeros que se aplican a una baldosa del tablero y provoca que las baldosas que sean desplazadas hacia el caigan y por ende se desaparecen del tablero exceptuando las Tiles de tipo Flying.

</br>
</br>

## Glues
Es un tipo de pegamento especial que se aplica en una baldosa específica y provoca que esta y sus baldosas vecinas se adhieran entre sí, 
formando una conexión. La clase Glues gestiona estas instancias de pegamento y ofrece la función recognizeGlue para manejar la lógica 
de adhesión entre baldosas.

</br>

### Normal
Este pegamento simplemente hara de base para el resto de pegamentos y su comportamiento es tan simple como adherir las piezas circundantes

<br>

### Fragil
Este pegamento es muy similar al normal, el tema es que solamente aguanta 1 tilt. Es decir, cuando se aplica tiene un comportamiento normal,
pero al realizar un movimiento, este pierde sus cualidades de pegante y tecnicamente se destruiria en el siguiente.
</br>
Para poder implementar este tipo de pegamento, la clase heredada solo tuvo que añadir un contador interno que verificara la cantidad de
movimientos realizados por el pegante, en el momento que llegase a 2 arrancando desde 1
</br>
### Super
Este pegamento a diferencia del normal no se aplica solamente a la casilla original si no que se aplica en todas las piezas interconectadas al bloque especifico
</br>


