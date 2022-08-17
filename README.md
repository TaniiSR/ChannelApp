# ChannelApp


### Using Technologies ###

* Kotlin
* Dagger Hilt for Dependency Injection
* Junit
* Kotlin coroutines
* MVVM
* Glide
* TDD (Unit test)
* Repository pattern
* Jetpack Navigation component
* Kotlin DSL (Gradle files)
* Retrofit
* Gson
* ViewBinding
* Clean Architecture
* SOLID Principles

### Demonstration of Base, Repository layer, with some Custom widgets OR components ###

## Project contains three basic layers of Clean Architecture ##
* Data Layer
* Domain Layer
* Presentation Layer

Data Layer contains remote and local data repositories.

Domain layer contains a Data Repository which handles the logic of getting the data from local or repository according to certain business rules.

Basically in Domain Layer we Add USE CASES according to Clean Architecture Documentation but I'm using Repository pattern so that's why I don’t feel like introducing USE CASES in Domain Layer due to Repository. I achieve segregation through interfaces which show microservices or USE CASE following by abstraction concepts.

Data and domain layer exposes to external layer through interface. Which follow the Abstraction and loose coupling.

Presentation layer contains the UI and ViewModels.


In this application vertical scrolling with horizontal scrolling items is the most challenging part and map the data from three different API’s. I tried to follow the SOLID  principles and  Clean Architecture guidelines to build this APP.

Utils contains the Base Classes(Base Implementations for the whole app) and the Custom Component Toolbar view is just a showcase of custom widgets.
