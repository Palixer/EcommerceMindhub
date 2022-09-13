
#Challenge Java

Temática del proyecto: MARKETPLACE/E-COMMERCE.  
Debe ser una API donde el usuario pueda comprar agregando a un carrito de compras los productos de un ecommerce.
Una vez el usuario cree su cuenta e inicie sesión, podrá ir agregando al carrito de compras los productos que desee. Además, el usuario podrá indicar el numero de ítems por cada producto agregado al carrito. Es posible también eliminar productos del carrito.

Una vez los productos se agregan al  carrito, el usuario podrá pagar a través de una pasarela de pagos que debe estar integrada como mínimo con una forma de pago y terminando en un checkout. 
Adicionalmente pueden enviar un mail de resumen al usuario con lo que compró.  Pensar en dirección de entrega, costos de envío dinámicos, etc. todo suma como agregados al proyecto.
  
Condiciones técnicas y de funcionamiento que deben respetarse:
-         Se debe realizar la construcción completa de la API  (backend), para lo cual se deben entregar todos los endpoints de los servicios desarrollados y se deben probar a través de Postman.
-         Las rutas deben estar protegidas usando Spring Security.
-         Se deben validar los datos que el usuario ingresa en la creación de los diferentes objetos.
-   Debe existir un CRUD (create-read-update and delete) y sus respectivas rutas para las siguientes entidades:

•	Clientes
•	Categorías
•	Productos
-   Se debe construir un servicio que permita agregar y eliminar productos a un carrito de compras que será almacenado en sesión. Para esto, se debe crear una entidad que almacenará los siguientes datos:
•	Fecha de la compra
•	Lista de productos
•	Cliente
•	Monto total
Si se intenta agregar productos al carrito y no se cuenta con una sesión iniciada, se debe devolver un mensaje de error 403.
-   A nivel backend debe trabajarse con Java, SpringBoot, Gradle y el motor de prueba de base de datos H2.
-         El producto debe superar con éxito distintos tipos de pruebas, sin caerse en ningún momento.
-         Es necesario contar con un servicio que permita a una persona crear una cuenta y posteriormente loguearse.
- Se debe construir un servicio que permita emular un a pasarela de pago donde se apruebe la compra indicando un numero de tarjeta y la cantidad de cuotas. En caso de ser exitoso el pago, este servicio debe retornar un código de éxito 200.
-  Una vez realizado el pago, se debe generar número de ticket de compra y almacenar la información de la compra en una tabla de BD llamada compras y asociarla a los productos que son parte de la compra, además de almacenar la fecha de compra, el cliente, el monto total, etc. 
Después de generar el ticket de compra, se enviará al usuario un correo electrónico con la información detallada de la compra realizada.
-         Debe ser hosteado en Heroku 
