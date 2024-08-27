# ComposeProductLister_CleanMVVM

**ComposeProductLister_CleanMVVM** es una aplicación de Android desarrollada en Kotlin utilizando exclusivamente Jetpack Compose para la interfaz de usuario. La aplicación permite a los usuarios explorar y buscar productos de manera eficiente y atractiva, aplicando Clean Architecture combinada con el patrón MVVM, y siguiendo las mejores prácticas de desarrollo de Android para asegurar una experiencia de usuario robusta y sin fallos.

## Características principales

- **Consumo de APIs**: La aplicación consume recursos de internet mediante APIs para cargar productos y sus respectivas categorías, asegurando información actualizada y relevante.
- **Listar productos**: Los productos se listan automáticamente según su rating, mostrando primero aquellos con mayor calificación.
- **Filtros avanzados**: Los usuarios pueden aplicar filtros para ordenar los productos por precio, descuento, categoría, rating, stock y marca, personalizando su experiencia de búsqueda.
- **Búsqueda de productos**: Funcionalidad de búsqueda integrada para encontrar rápidamente los productos deseados.

## Tecnologías y Arquitectura

- **Lenguaje**: Desarrollada en Kotlin, aprovechando las características modernas y seguras del lenguaje.
- **Jetpack Compose**: Toda la aplicación está desarrollada utilizando Jetpack Compose, lo que garantiza una UI declarativa, moderna y altamente personalizable.
- **Base de datos**: Implementación de Room para el manejo de la base de datos local, asegurando un almacenamiento eficiente y acceso rápido a los datos.
- **Arquitectura**: Aplicación de Clean Architecture para mantener una clara separación de responsabilidades y un código fácil de mantener, combinado con el patrón MVVM para la gestión de la UI y la lógica de presentación.
- **Inyección de dependencias**: Uso de Hilt para la inyección de dependencias, facilitando la modularidad y escalabilidad del proyecto.
- **Flows**: Implementación de Flows para el manejo reactivo de datos, asegurando una actualización eficiente y fluida de la UI.
- **UX/UI**: Diseño de interfaz de usuario siguiendo las guías de Material3, proporcionando una experiencia visualmente atractiva y consistente. Se ha puesto un fuerte énfasis en ofrecer una UX intuitiva y sin complicaciones.

## Fiabilidad

- **Sin caídas**: La aplicación está optimizada para ser estable y no crashear, garantizando una experiencia de usuario fluida y sin interrupciones.
