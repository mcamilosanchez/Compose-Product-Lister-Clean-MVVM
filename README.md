# Compose Product Lister Clean MVVM

**ComposeProductLister_CleanMVVM** is an Android application developed in Kotlin, using Jetpack Compose exclusively for the user interface. The app allows users to explore and search for products efficiently and attractively, applying Clean Architecture combined with the MVVM pattern while following Android development best practices to ensure a robust and crash-free user experience.

## Key Features

- **API Integration**: The application fetches data from the internet using APIs, powered by **Retrofit**, to load products and their respective categories, ensuring up-to-date and relevant information.
- **Product Listing**: Products are automatically listed based on their ratings, prioritizing those with the highest ratings.
- **Advanced Filters**: Users can apply filters to sort products by price, discount, category, rating, stock, and brand, customizing their search experience.
- **Product Search**: Integrated search functionality allows users to quickly find desired products.

## Technologies and Architecture

- **Language**: Developed in Kotlin, leveraging the modern and safe features of the language.
- **Jetpack Compose**: The entire application is built using Jetpack Compose, ensuring a declarative, modern, and highly customizable UI.
- **Database**: Room is implemented for local database management, ensuring efficient storage and quick data access.
- **API Networking**: **Retrofit** is used for seamless and efficient API calls, ensuring reliable communication with external services.
- **Architecture**: Clean Architecture is applied to maintain a clear separation of concerns and easy-to-maintain code, combined with the MVVM pattern for UI and presentation logic management.
- **Dependency Injection**: Hilt is used for dependency injection, enabling modularity and scalability of the project.
- **Flows**: Reactive data handling is implemented using Flows, ensuring efficient and smooth UI updates.
- **UX/UI**: User interface design follows Material3 guidelines, offering a visually appealing and consistent experience. Great emphasis has been placed on delivering an intuitive and hassle-free user experience.

## Reliability

- **Crash-Free**: The app is optimized to be stable and crash-free, ensuring a smooth and uninterrupted user experience.
