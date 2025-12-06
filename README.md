🚗 Car Rental System – Java (OOP Project)

This is a simple console-based Car Rental System built using Object-Oriented Programming (OOP) in Java.
It allows users to rent a car, return a car, and view rental details.

📌 Features
✔ Manage Cars

Each car has: ID, name, brand, base price, and availability status

Cars can be marked as available or rented

✔ Rent a Car

User enters name

System shows list of available cars

User selects car ID & rental days

Total cost is calculated automatically

Rental stored in system

✔ Return a Car

User enters car ID

System checks if the car is rented

Marks the car as returned

Removes rental record

✔ OOP Used

Classes: Car, Customer, Rental, RentalSystem

Concepts: Encapsulation, lists, object relations, methods

📁 Class Summary
Car

Holds car details

Manages availability

Calculates rental price

Customer

Stores customer name & auto-generated ID

Rental

Links a Customer with a Car and number of rental days

RentalSystem

Stores lists of cars, customers, and rentals

Contains the app menu() loop

Handles renting and returning

Main

Creates cars

Starts the rental system menu

▶ How It Works (Flow)

App starts

User chooses

Rent a car

Return a car

Exit

System processes based on input

Continues until user exits
