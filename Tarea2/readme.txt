*******************************************************
Readme de la Tarea Nº1 realizada para la asignatura ELO329.

Tarea 2: Bolas, Resortes y Puntos de Apoyo como Objetos de Software.

Integrantes: Oscar Tapia; Camilo Barra; Roberto Cifuentes. 
*******************************************************
Archivos que contiene:

Etapa 4:
	PhysicsLab.java \
	BallView.java \
	Ball.java \
	FixedHookView.java \
	FixedHook.java \
	SpringAttachable.java \
	SpringView.java \
	Spring.java \
	LabMenuListener.java \
	MouseListener.java \
	PhysicsElement.java \
	Simulateable.java \
	MyWorldView.java \
	MyWorld.java 
	



*******************************************************
Instrucciones: Dentro del archivo se dejó la última etapa correspondiente a la Tarea 2 (a pedido del profesor, que se dejara la última etapa a la que se logró llegar), y dentro de esta Etapa se encuentran los archivos .java que poseen los códigos, y el Makefile que puede servir para la compilación y ejecución de la Etapa.
Dentro de cada Makefile estan descritos los tipos de compilacion que se pueden realizar en la etapa, ya sea para crear los .class de cada .java, o para borrarlos, para ejecutar el programa creado en su totalidad, o para crear el documento javadoc


Para la compilacion y ejecucion del programa dentro de cada etapa correspondiente, se utiliza el Makefile.

1- Desde terminal, ejecutar el programa y aparezca la simulación gráfica, en Makefile se usa el comando
	
	$make run

La compilación y ejecución de los datos se realizarán de forma automática. Los parámetros de simulación serán los que están por defecto.

2- Desde terminal, para crear solamente los .class de cada archivo .java de la etapa, en Makefile se usa el comando
	
	$make classes

3- Desde terminal, para realizar la documentacion JavaDoc, en Makefile se usa el comando

	$make doc

Se creara la documentacion propia de JavaDoc en la misma carpeta donde se ejecutó el Makefile.

 

Para realizar la compilación manualmente, desde terminal, dirigirse a la carpeta donde se encuentra la etapa correspondiente e ingresar los siguentes codigos:

	$javac PhysicsLab.java
	$java PhysicsLab

El primer código corresponde a la compilación de la Etapa.
El segundo código corresponde a la ejecucion de la Etapa. 

Una vez ejecutado el código, se abre la ventana gráfica del codigo, realizando las acciones correspondientes de la tarea.

Para la creacion de la documentacion JavaDoc desde terminal de forma manual, se ingresa a la etapa correspondiente, y se escribe el siguiente comando

	$javadoc -d PhysicsLabDoc *.java
