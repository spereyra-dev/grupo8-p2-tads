

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
Se recorre la lista de tweets, filtrándolos por fecha.
Aquellos que corresponden al día ingresado:
- chequea que no sea igual a ningún hashtag que ya este en la lista de hashtags
- si no está, lo agrega.

Una vez que termina, imprime los resultados.

3- Reporte 3:
Llama a la función mostUsedHashTagForADay(LocalDate date).

La función crea una HashTable llamada hashTagHashTable, donde se van a guardar los String hashtags(key) y las veces que este se repite(value).
Se recorre la lista de tweets para filtrarlos por fecha.
Si la fecha del tweet es la correcta, se verifica si los hashtags que este contiene ya existe en la hashtable.
Si no existen, se los agrega. (ignorando f1)
Si ya existen, les suma 1 a su value.

Para encontrar el hashtag más utilizado (el de mayor value) se utiliza la interfaz de Enumeration para las keys de la hashtable.
Se busca el máximo valor del value comparándolo con el máximo actual (se actualiza mientras se va recorriendo las diferentes keys.
Al final se imprimen los resultados.

4- Reporte 4:
LLama a la función top7UsersWithMoreFavourites().

Esta va comparando la cantidad de favoritos de la userLinkedList
Luego, coloca los 7 mejores en una linked list de tamaño 7
Imprime los resultados.

5- Reporte 5:
Llama a la función tweetsWithSpecificWordOrPhrase(String wordOrPhrase).

A través de un contador (que comienza en cero), se recorre toda la lista de tweets buscando esa palabra o frase.
Para evaluar esto último, se convierte en minúscula para igualar la lectura.
Si el tweet siendo evaluado contiene el string ingresado, el contador aumenta 1.
Imprime la cantidad de veces que esa palabra se repite en todos los tweets.

6- Reporte 6:
Llama a la función getTopTenPilots(int month, int year).

Esta crea una HashTable donde se ingresarán los divers como key y las veces que estos son mencionados en los tweets en value.
Primero, se filtra los tweets por fechas.
Luego, si la fecha corresponde, se verifica si coincide su contenido con algun nombre de la driversLinkedList. Si esto es así, se aumenta el value de key=piloto.
Se imprimen los primeros 10 pilotos del HashTable.

---MEDICIÓN DE LA EFICIENCIA---

El tiempo de ejecución de cada función y la memoria que esta ocupa, se calcula luego de la realización del reporte correspondiente.

Metodología:
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long loadStartTime = System.currentTimeMillis();
        measureMemoryAndTime(rt, total_mem, loadStartTime);

Estas líneas registran el tiempo que demora la ejecución en milisegundos y la memoria total que esta ocupa.
Estas se imprimen junto al resultado de la función.








