# Architecture

MVVM with UseCases and DTO pattern is used. Contains Data, Domain and Presentation layers. Data layer is responsible with communicating with the Api. Domain layer holds the UseCases and Repository protocols. Finally the Presentation layer contains the Composables, ViewModels. Basically all of the UI related stuff.

## Tech Stack

* Jetpack Compose is used for faster UI implementation.
* Dagger - Hilt is used for Dependenct Injection. Making the code cleaner, more testable and memory efficient.
* Retrofit
* Kotlin Coroutines & Flows
* Compose Navigation for passing data between pages and managing backstacks.

## State in Jetpack Compose

In Compose, a composable shouldn't hold its own state objects. These objects should be hoisted to other composables or state holder classes. This "State hoisting" makes composables reusable and testable.

## Unit Testing

* Only the UseCases were covered in this project. With a fake repository created by implementing the StationRepository interface.

## Modules

Project contains a submodule called Core. Core module contains the base url and a base dependency injection object that provides core network instances such as Gson, OkHttp and Retrofit.

## Important Files & Folders

### * BaseViewModel 

An abstract class that contains shared variables, states and functions used by all of the ViewModels. Such as loading indicator state, error messages and util methods for collecting the flows provided from UseCases.

### * Dto Folder

Data Transfer Object classes are defined in this folder. Each one has a transformation method for mapping DTOs to Models that will be used in the app. This object seperation is made for making the application independent from the very first object that is fetched and serialized from the Api. With this way the project will be safe even if the DTO has to change in the feature.

### * UseCases

2 UseCases were defined in the project for getting all of the stations from the Api and booking a trip. UseCases take the repository as a constructor parameter which is provided by Hilt. Some of the logic is hoisted to usecases for making ViewModels more simple and readable.
