1. Diagrama UML de clases de la solución.
2. Breve descripción de los procesos de carga y realización de reportes.
3. Medición de eficiencia de la aplicación. Para ello se deberá indicar, tanto para la
carga como para la ejecución de los reportes:
a. Cantidad de memoria RAM consumida
b. Tiempo de ejecución promedio.

un archivo README.txt describiendo los procesos de carga, las decisiones tomadas
y el consumo de memoria en cada reporte.

----BREVE DESCRIPCIÓN DE LAS FUNCIONES----

En un principio, cuando se ejecuta el programa "Main", se ejecuta la función getCsvInfo(), la cual se utiliza para extraer la información correspondiente del documento dado por la letra.
Esta información es procesada y clasificada en "user", "tweet" y "hashtag" según corresponda. Además, se realiza una LinkedList de cada uno.

Las fechas fueron ingresadas utilizando el tipo de dato DateTime.

1- Reporte 1:
Llama a la función top15UsersWithMoreTweets().
Esta, crea una linked list llamada "topUsers" donde se van a guardar aquellos usuarios que tengan más tweets.
Se recorre una linked list de los usuarios, comparando su cantidad de tweets con aquellos que se encuentran en la lista topUsers. Cuando encuentra uno donde la cantidad de tweets del usuario evaluado es mayor a la cantidad del que forma parte del top 15, lo agrega ahi; esto hace que queden ordenados.

En un principio, a los primeros 15 usuarios evaluados se los agrega directamente a topUsers.
Cuando la lista de topUsers tiene más de 15 users, se elimina el último, que al estar ordenados, no es parte del top 15.

Cuando termina de recorrer todos los usuarios, imprime los resultados obtenidos en topUsers.

2- Reporte 2:
Llama a la función differentHashTagsForADay(LocalDate date).
Primero, crea una lista donde los diferentes hashtags se van a guardar.
Se recorre la lista de tweets, filtrandolos por fecha.
Aquellos que corresponden al día ingresado:
- chequea que no sea igual a ningún hashtag que ya este en la lista de hashtags
- si no está, lo agrega.

Una vez que termina, imprime los resultados.

