<a id="informe-top"></a>
<br>
<div align="center">

<h1 align="center">Tilting Tiles</h1>

  <p align="center">
    Informe Pruebas Dinamicas
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
Se configuró el plugin de JaCoCo en el archivo pom.xml de Maven para habilitar el seguimiento de cobertura de código de forma automática 
al ejecutar las pruebas. Los resultados de cobertura se generaron en un informe HTML, detallando la cobertura de métodos, líneas y ramas 
por cada clase del proyecto. Esta configuración es esencial para mantener un estándar de calidad en el código y asegurarse de que el
porcentaje de cobertura se mantenga dentro de los umbrales definidos en el equipo de desarrollo.

<br>

### Generacion del informe
Para generar el informe de cobertura de código con JaCoCo, primero se configura el plugin de JaCoCo en el archivo pom.xml de Maven,
especificando la fase de ejecución (generalmente prepare-agent o test). Luego, se ejecuta el comando mvn test en la consola, lo que 
activa la ejecución de las pruebas junto con el seguimiento de cobertura de JaCoCo. Al finalizar, se genera un informe en el formato 
especificado en la carpeta *target/site/jacoco*, que contiene detalles sobre la cobertura de líneas, métodos y ramas del 
código. Este flujo permite integrar de forma automática la verificación de cobertura en el ciclo de pruebas de Maven, contribuyendo 
a mantener un estándar de calidad en el proyecto.

<br>

### Analisis del informe
Los resultados de cobertura arrojados por JaCoCo indican que se alcanzó un porcentaje de cobertura general del 75%. A nivel de línea,
mientras que la cobertura de ramas fue de 61%. Estas métricas nos permiten identificar las áreas del código que no fueron ejercitadas 
durante las pruebas y, por lo tanto, requieren revisión adicional.

![Errores generales](/Screenshots/DTJ1.png)

Para comenzar, podemos evidenciar problemas en algunos tipos de tiles y en el puzzle, pues en estas se encuentra la mayor cantidad de 
codigos sin probar o que directamente no lo requieren

![Errores generales](/Screenshots/DTJ2.png)

Al inspeccionar mas al detalle en puzzle, vemos que la gran mayoria esta cubierta en codigo, pero tienen muchas branches que no terminan 
de ejecutar correctamente. Y en el caso del fragil glue, directamente no hay cobertura en test unitarios 

![Errores generales](/Screenshots/DTJ3.png)

Al revisar el código interno, observamos que casi todos los métodos, por dar margen a los casos excepcionales, terminan dejando de lado 
ciertas excepciones o escenarios muy específicos que no suelen presentarse. Estos son casos aislados que decidimos incluir por precaución, 
en caso de que algo fallara o surgiera un comportamiento inesperado, asegurando así una mayor robustez en la gestión de errores y 
garantizando la estabilidad del sistema en situaciones poco probables.

![Errores generales](/Screenshots/DTJ4.png)

Ahora se realizan los siguientes cambios

![Errores generales](/Screenshots/DTJ5.png)

Tras realizar algunos ajustes en el manejo de excepciones y optimizar el tratamiento de los casos específicos, hemos observado una mejora
significativa en los resultados. Las áreas que previamente presentaban una cobertura reducida ahora están mejor cubiertas, y los escenarios
excepcionales que antes no eran adecuadamente gestionados han sido correctamente implementados y testeados. Estos cambios han fortalecido
la robustez del sistema, permitiendo una mayor confiabilidad y reducción de errores en situaciones atípicas. En conclusión, los ajustes
realizados han contribuido a mejorar tanto la cobertura del código como la estabilidad general del sistema, asegurando que el proyecto
cumpla con los estándares de calidad esperados.
