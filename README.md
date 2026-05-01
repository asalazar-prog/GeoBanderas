# GeoBanderas - Proyecto de Certificación

## Descripción del Proyecto

**GeoBanderas** es una aplicación móvil desarrollada para la plataforma Android como parte de los requisitos de evaluación para la certificación **Android Software Developer** emitida por el **Tecnológico de Monterrey**.

El objetivo principal de esta aplicación es servir como una herramienta educativa y de consulta que permita a los usuarios (principalmente estudiantes de educación básica y media) visualizar un catálogo completo de países del mundo y consultar sus respectivas banderas. A través de este reto, se demuestra la capacidad para integrar servicios web externos, procesar datos en tiempo real y renderizar contenido multimedia de manera eficiente en un entorno móvil.

---

## Principales Tecnologías Empleadas

La aplicación está construida utilizando las herramientas y librerías modernas recomendadas por Google para el desarrollo nativo en Android (Modern Android Development - MAD):

* **Kotlin**: Lenguaje de programación principal, aprovechando su expresividad, seguridad contra nulos y soporte nativo para corrutinas.
* **Jetpack Compose**: Kit de herramientas moderno para la construcción de interfaces de usuario nativas mediante un paradigma declarativo, optimizando la creación de listas complejas y pantallas de detalle.
* **Retrofit 2**: Cliente HTTP Type-Safe para la comunicación con la API REST de [REST Countries](https://restcountries.com/v3.1/all). Se utiliza para realizar las peticiones de red, desacoplando la lógica de conexión del resto de la aplicación.
* **Coil (Coroutine Image Loader)**: Librería de carga de imágenes basada en corrutinas de Kotlin. Permite la descarga, visualización y almacenamiento en caché de las banderas en formato de imagen de manera asíncrona y fluida.

---

## Arquitectura del Software

Para garantizar la escalabilidad, testabilidad y el mantenimiento a largo plazo del proyecto, se ha implementado el patrón de arquitectura **MVVM (Model-View-ViewModel)** bajo los principios de Clean Architecture.

```
┌─────────────────────────────────────────────────────────┐
│                       UI (View)                         │
│               (Jetpack Compose Screens)                 │
└───────────────────────────┬─────────────────────────────┘
                            │ Observa el estado del UI
                            ▼
┌─────────────────────────────────────────────────────────┐
│                      ViewModel                          │
│         (Manejo de Estado y Corrutinas)                 │
└───────────────────────────┬─────────────────────────────┘
                            │ Solicita datos
                            ▼
┌─────────────────────────────────────────────────────────┐
│                 Repository / Model                      │
│        (Retrofit API Client & Data Classes)             │
└─────────────────────────────────────────────────────────┘
```
## Licencia y Derechos de Autor

Copyright © 2026 Alejandro Salazar.

Este proyecto está bajo la [Licencia MIT](LICENSE.txt). Puedes usar, modificar y distribuir el código libremente para fines educativos y personales, siempre y cuando se mantenga este aviso de derechos de autor.
