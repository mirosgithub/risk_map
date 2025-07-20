# SOFTENG 281 - Assignment 3: Risk Map

## Overview

This project implements a command-line interface (CLI) system for finding optimal routes between countries on a Risk-style world map. The system allows users to query country information and find the shortest path between any two countries, calculating fuel costs and continent statistics for the journey.

## Project Structure

```
src/
├── main/java/nz/ac/auckland/se281/
│   ├── Continent.java              # Continent enum with fuel cost mapping
│   ├── Country.java                # Country entity with name, continent, and fuel cost
│   ├── Graph.java                  # Generic graph implementation with BFS pathfinding
│   ├── InvalidCountryNameException.java # Custom exception for invalid country names
│   ├── Main.java                   # CLI interface (provided)
│   ├── MapEngine.java              # Main system logic and route calculation
│   ├── MessageCli.java             # Message constants (provided)
│   └── Utils.java                  # Utility methods (provided)
├── main/resources/
│   ├── adjacencies.csv             # Country adjacency data (provided)
│   └── countries.csv               # Country information data (provided)
└── test/java/nz/ac/auckland/se281/
    ├── MainTest.java               # Test cases (provided)
    └── SysCliTest.java             # System test framework (provided)
```

## My Implementation

I was responsible for implementing the following classes, using key Object-Oriented Programming concepts and Data Structures:

### Core Entity Classes
- **`Country.java`** - Country entity with name, continent association, and fuel cost
- **`Continent.java`** - Continent enum with name mapping and string conversion

### Graph Data Structure
- **`Graph.java`** - Generic graph implementation with adjacency list representation
  - Supports adding/removing nodes and edges
  - Implements breadth-first search for shortest path finding
  - Generic design allows for different node types

### Exception Handling
- **`InvalidCountryNameException.java`** - Custom exception for handling invalid country names

### Main System Logic
- **`MapEngine.java`** - Core routing logic implementing all CLI commands
  - Loads map data from CSV files
  - Handles country information queries
  - Calculates optimal routes with fuel costs and continent statistics

## OOP Concepts Demonstrated

### Encapsulation
- **Private fields**: All entity classes use private fields with public getter methods
- **Information hiding**: Internal implementation details are hidden from external classes
- **Data validation**: Country name validation with custom exception handling

### Inheritance & Polymorphism
- **Generic Graph**: The `Graph<T>` class demonstrates generic programming
- **Exception hierarchy**: Custom exception extends the standard exception class

### Composition
- **Country-Continent relationship**: Countries contain references to their continents
- **Graph composition**: MapEngine manages the graph structure and country collections

## Data Structures Used
- **HashMap**: Efficient country lookup and graph adjacency storage
- **HashSet**: Visited node tracking in pathfinding
- **LinkedList**: Queue implementation for BFS
- **ArrayList**: Dynamic list management for routes and neighbours

## Key Features

### Task 1: Country Information
- Query detailed information about a country
- Display country name, continent, fuel cost, and neighbouring countries
- Input validation with case-insensitive country name matching
- Error handling for invalid country names

### Task 2: Route Finding
- Find shortest path between any two countries using breadth-first search
- Calculate total fuel cost for the journey (excluding source and destination)
- Display route information with step-by-step country sequence
- Handle edge cases (same source and destination)

### Task 3: Journey Statistics
- **Continent Analysis**: Track which continents are visited during the journey
- **Fuel Cost Analysis**: Calculate fuel consumption per continent
- **Highest Fuel Continent**: Identify the continent with highest fuel consumption
- **Route Optimisation**: Ensure shortest path with minimal fuel cost

## Data Management

### CSV Data Loading
- **Countries Data**: Loads country names, continents, and fuel costs
- **Adjacency Data**: Establishes country connections for graph construction
- **Error Handling**: Graceful handling of invalid data in CSV files

### Graph Construction
- **Adjacency List**: Efficient representation of country connections
- **Bidirectional Edges**: Countries can travel in both directions
- **Dynamic Loading**: Graph built from CSV data at runtime

## Testing

The project includes comprehensive test suites for all tasks:
- **Task 1 Tests**: Country information and query functionality
- **Task 2 Tests**: Route finding and path calculation
- **Task 3 Tests**: Journey statistics and fuel cost analysis

## Provided Components

The following classes were provided by the course:
- `Main.java` - CLI interface and command processing
- `MessageCli.java` - Message constants and formatting
- `Utils.java` - Utility methods for input/output and file reading
- `MainTest.java` - Comprehensive test cases
- `SysCliTest.java` - System test framework
- `countries.csv` - Country data with names, continents, and fuel costs
- `adjacencies.csv` - Country adjacency data for graph construction
