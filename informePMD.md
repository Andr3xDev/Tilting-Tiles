<a id="informe-top"></a>
<br>
<div align="center">

<h1 align="center">Tilting Tiles</h1>

  <p align="center">
    Informe PDM
    <br />
  </p>
</div>


</br>
</br>



## RESUMEN
El test PDM en IntelliJ ha sido en su mayoría exitoso, con algunos errores menores identificados. Sin embargo, 
estos errores son justificables debido a situaciones específicas del contexto de implementación y no afectan 
gravemente el rendimiento ni la funcionalidad general del sistema. Las pruebas han demostrado que el diseño del 
modelo es sólido y bien estructurado, y los errores encontrados no representan un riesgo significativo para 
el despliegue o el rendimiento, aunque se recomienda realizar ajustes menores en la configuración de 
pruebas para evitar futuros inconvenientes.

<br>

## Desarrollo
### PDM en Maven
Se optó por usar **PDM** de maven en lugar de un plugin específico debido a su capacidad para realizar análisis estático del 
código de manera más eficiente y completa. PMD ayuda a identificar problemas de calidad del código, como malas prácticas de programación, 
violaciones de convenciones y errores potenciales, lo que permite mejorar la mantenibilidad y la legibilidad del código. Ademas, 
su integración con herramientas como Maven facilita la ejecución automatizada y la generación de informes detallados sobre el estado del código.

La **migración a Maven** se realizó para gestionar las pruebas y el análisis de código de manera más estandarizada y automatizada. 
Maven, al ser una herramienta de construcción ampliamente adoptada en proyectos Java, proporciona una estructura consistente para 
la ejecución de tareas relacionadas con la compilación, prueba y análisis estático. Migrar a Maven permite integrar PMD de 
forma más fluida en el ciclo de vida del proyecto, facilitando la ejecución de análisis de código durante el proceso de construcción. 
Esto no solo mejora la calidad del código al permitir análisis continuos, sino que también optimiza la gestión de dependencias 
y la automatización de tareas, lo que hace que el proyecto sea más escalable y fácil de mantener.

<br>

### Generacion del informe
Para comenzar, suponemos que primero se configura el plugin de PMD en el archivo `pom.xml` de Maven, especificando la fase de ejecución 
y el formato del informe. Luego, se ejecuta el comando `mvn pmd:pmd` en la consola, lo que activa el análisis estático del código 
durante la fase de construcción configurada. El informe se genera en el formato especificado, ya sea en la consola o en 
archivos dentro de la carpeta `target/pmd`, dependiendo de la configuración del plugin. Este proceso permite integrar el 
análisis de calidad del código de manera automática en el flujo de trabajo de Maven.

<br>

### Analisis del informe
AL analizar el informe por encima, es posible ver nos arrojaron el informe por **orden de prioridad**, es decir, primero nos muestra
componentes que de una u otra forma pueden afectar el funcionamiento del codigo. 

![Errores generales](/Screenshots/PMD1.png)

Sin embargo, en nuestro caso decidimos ver exactamente
que linea mandaba el error, encontrando lo siguiente:

![Codigo malo](/Screenshots/PMDC.png)

Como apreciamos, esta linea de codigo esta presente en cada una de las clases que el informe dio maxima prioridad, pues en el papel
no estan siendo utilizadas por ningun metodo quedando asi como variables libres sin funcion. No obstante, al quitar la linea de codigo 
o refactorizarla se termina afecctando el comportamiento y la funcionalidad completa de las clases, es decir, sin esta linea por alguna
razon que desconocemos, no funciona el proyecto. Por lo cual, decidimos dejar las lineas quietas.

Por otro lado, vemos que nos arrojo un error relacionado al paquete de *shapes* aunque con menos prioridad. Aun asi, volvimos a 
dejar el codigo como estaba por el hecho de que no hubieron modificaciones a dicho paquete desde que nos lo dieron como la 
base grafica del proyecto. Entonces, no es un error nuestro y tampoco nos interesa arreglarlo para no afectar su perfecto funcionamiento.

![Errores de shapes](/Screenshots/PMD2.png)

En conclucion, la prueba fue exitosa gracias a un código de alta calidad que sigue los pilares fundamentales de la Programación Orientada
a Objetos (POO), como la encapsulación, la herencia, el polimorfismo y la abstracción, lo cual asegura una estructura robusta y bien
organizada. Además, al aplicar los principios SOLID, se logró un diseño modular y flexible, facilitando la escalabilidad y el mantenimiento 
del sistema. El uso de IntelliJ también fue clave, ya que sus herramientas de análisis estático, refactorización y asistencia en la
codificación permitieron optimizar el flujo de trabajo y mantener altos estándares de calidad en el código. Esta combinación de buenas
prácticas y herramientas efectivas permitió obtener un resultado perfecto en las pruebas, reflejando un sistema confiable y de fácil expansión.
