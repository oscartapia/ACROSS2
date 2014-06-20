*******************************************************
Readme de la Tarea Nº3 realizada para la asignatura ELO329.

Tarea 3: Applet para Simulación Bolas, Puntos fijos, Resortes y Osciladores como Objetos de Software.

Integrantes: Oscar Tapia; Camilo Barra; Roberto Cifuentes. 
*******************************************************
Archivos que contiene:

Programa 4:
	PhysicsLab.java \
	PhysicsLabApplet.java \
	BallView.java \
	Ball.java \
	SimpleAudioPlayer.java \
	FixedHookView.java \
	FixedHook.java \
	Oscillator.java \
	OscillatorView.java \
	SpringAttachable.java \
	SpringView.java \
	Spring.java \
	LabMenuListener.java \
	MouseListener.java \
	NextKeyListener.java \
	PhysicsElement.java \
	Simulateable.java \
	MyWorldView.java \
	MyWorld.java 



*******************************************************
Instrucciones: Dentro del archivo se dejó los ultimos codigos realizados correspondiente a la Tarea 3, y dentro de esta carpeta se encuentran los archivos .java que poseen los códigos, y el Makefile que puede servir para la compilación y ejecución de la Tarea.
Dentro del Makefile estan descritos los tipos de compilacion que se pueden realizar en la etapa, ya sea para crear los .class de cada .java, o para borrarlos, para ejecutar el programa creado en su totalidad, para crear el documento javadoc, ejecutar el applet del programa, o el .jar.


Para la compilacion y ejecucion del programa dentro de cada etapa correspondiente, se utiliza el Makefile.

1- Desde terminal, ejecutar el programa y aparezca la simulación gráfica, en Makefile se usa el comando
	
	$make run

La compilación y ejecución de los datos se realizarán de forma automática. Los parámetros de simulación serán los que están por defecto.

2- Desde terminal, para crear solamente los .class de cada archivo .java de la etapa, en Makefile se usa el comando
	
	$make classes

3- Desde terminal, para realizar la documentacion JavaDoc, en Makefile se usa el comando

	$make doc

Se creara la documentacion propia de JavaDoc en la misma carpeta donde se ejecutó el Makefile.

4- Desde terminal, para inicial la aplicacion en applet, en Makefile se usa el comando

	$make runApplet

Se iniciara el programa compilado, en modo applet.

5- Desde Terminal, para iniciar la aplicacion desde el .jar, en Makefile se usa el comando

	$make jar
 

Para realizar la compilación manualmente, desde terminal, dirigirse a la carpeta donde se encuentra la etapa correspondiente e ingresar los siguentes codigos:

	$javac PhysicsLab.java
	$java PhysicsLab

El primer código corresponde a la compilación de la Etapa.
El segundo código corresponde a la ejecucion de la Etapa. 

Una vez ejecutado el código, se abre la ventana gráfica del codigo, realizando las acciones correspondientes de la tarea.

Para la ejecucion de la applet de forma manual, se deben crear los .class correspondientes, principalmente de PhysicsLabApplet.java, luego se ingresa en terminal

	$appletviewer PhysicsLab.html

Lo que iniciaria la aplicacion desde applet.

Para ejecucion del programa desde el .jar, en terminal se ingresa el siguiente comando

	$java -jar Tarea3.jar

Para la creacion de la documentacion JavaDoc desde terminal de forma manual, se ingresa a la etapa correspondiente, y se escribe el siguiente comando

	$javadoc -d PhysicsLabDoc *.java
