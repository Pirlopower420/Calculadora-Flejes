# Examen-Final

Módulos Desarrollados
1.	Módulo Interfaz de Usuario (InterfazUsuario):
o	Este módulo es responsable de interactuar con el usuario. Proporciona las opciones de menú, solicita las entradas del usuario, y muestra los resultados y errores. Además, gestiona la entrada de los datos necesarios para crear flejes y calcular pagos.
2.	Módulo Fleje (Fleje):
o	Representa el modelo de datos para un fleje. Contiene los atributos de un fleje como sus dimensiones (lados), la cantidad, el tipo de varilla, y el precio por unidad. Además, calcula el pago de un fleje utilizando una fórmula que depende de la suma de sus lados y otros parámetros.
3.	Módulo de Cálculo de Fleje (FlejeService):
o	Es responsable de la creación de un fleje utilizando la información proporcionada por el usuario. Además, calcula el valor total de los flejes y gestiona las condiciones de la varilla utilizada.
4.	Módulo de Almacenamiento de Flejes (FlejeRepository):
o	Permite almacenar los flejes creados en una lista, actuando como una base de datos temporal. Este módulo es útil para tener un seguimiento de los flejes procesados en la sesión.
5.	Módulo Principal (Main):
o	Es el punto de entrada del sistema. Controla el flujo de trabajo principal del sistema, interactúa con el usuario, ejecuta la lógica de negocio y coordina la creación de flejes y la visualización de resultados.
