1. DESCRIPCIÓN DEL SISTEMA

El sistema cuenta con una interfaz gráfica desarrollada mediante Java Swing y se divide en tres secciones principales:

Torneo: permite visualizar la tabla de posiciones, consultar los puntos de cada equipo y agregar nuevos clubes.

Jugadores: permite consultar los jugadores registrados, sus clubes, goles, números de camiseta y tarjetas. Incluye un buscador y la posibilidad de agregar jugadores.

Partidos: permite visualizar los resultados y acontecimientos de los encuentros disputados.

El programa utiliza archivos de texto (.txt) para almacenar y recuperar información mediante las clases de entrada y salida de Java (java.io).

2. REQUISITOS DEL SISTEMA

Requisitos de software:

Entorno de desarrollo compatible con Java

No requiere bibliotecas externas para la interfaz gráfica.

Archivos necesarios:

Código fuente de FutbolStats.

clubes.txt

jugadores.txt

resultados.txt

Otros archivos de datos utilizados por el proyecto.

3. INSTRUCCIONES DE EJECUCIÓN

Descargar y descomprimir la carpeta del proyecto FutbolStats.

Abrir el proyecto en un entorno de desarrollo Java.

Configurar el JDK correspondiente.

Verificar que los archivos .txt estén ubicados en el directorio de trabajo del programa.

Buscar la clase Main del proyecto.

Ejecutar el método main.

Una vez abierta la ventana principal, presionar el botón "Ingresar al sistema".

Utilizar las pestañas Torneo, Jugadores y Partidos para navegar entre las funciones.

4. FUNCIONAMIENTO GENERAL

En la sección Torneo se muestra la tabla de posiciones con los equipos ordenados por puntos.

En la sección Jugadores se presenta una tabla con las estadísticas individuales. El usuario puede buscar por nombre o club

Los nuevos clubes y jugadores se almacenan en sus respectivos archivos de texto para conservar la información entre ejecuciones.