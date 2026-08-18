# Battleship — Java Technical Challenge

A console-based implementation of the classic Battleship game, developed in Java as part of my continued practice and reinforcement of Java fundamentals and Object-Oriented Programming concepts.

The project was built progressively across multiple stages, evolving from a simple game board into a modular two-player game with ship validation, shooting mechanics, fog of war, and win conditions.

## Features

- 10x10 game board
- Ship placement and validation
- Horizontal and vertical ship positioning
- Ship collision and adjacency detection
- Coordinate validation
- Fog of war
- Hit and miss detection
- Ship sinking detection
- Win condition
- Two-player gameplay
- Turn management

## Concepts Practiced

- Object-Oriented Programming
- Classes and objects
- Encapsulation
- Separation of responsibilities
- Arrays and multidimensional arrays
- Enums
- Input validation
- Control flow
- Method decomposition
- State management
- Basic game logic design

## Project Structure

The application was organized into separate classes to keep responsibilities isolated:

- `Main` — application entry point
- `Game` — game flow and turn management
- `Player` — player state
- `GameField` — board state and shooting logic
- `Ship` — ship state and sinking logic
- `Coordinate` — coordinate parsing and validation
- `ShotResult` — possible shooting outcomes

## Tech Stack

- Java 17

## Purpose

This repository is part of my Java technical challenges collection.

The goal is to reinforce and deepen core Java and OOP concepts through practical implementations, progressively increasing the complexity of the solution while keeping the code modular and maintainable.
