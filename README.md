Here is the full content for your README.md file in raw Markdown. You can copy this directly into your project.

Markdown
#  Brno Restaurant API

![Spring Boot](https://img.shields.io/badge/Spring_Boot-F21444?style=for-the-badge&logo=spring-boot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

A REST API built with Spring Boot and MongoDB, specifically designed for discovering restaurants in **Brno, Czech Republic**. This project implements **Geo-Spatial queries** to provide location-aware recommendations.


## Key Features

* **Location-Based Search**: Find restaurants within a specific kilometer radius using MongoDB's `2dsphere` index.
* **Automatic Data Seeding**: Populates your database with local Brno data (ERA Café, etc.) on the first run.
* **Type Filtering**: Case-insensitive filtering for restaurant categories (e.g., "Pub", "Cafe").
* **DTO Pattern**: Clear separation between database entities and API responses for better security and flexibility.
* **Safe Mapping**: Handles the conversion between GeoJSON coordinates `[lon, lat]` and standard user-friendly `lat/lon` fields.

---

##  Prerequisites

* **Java 21** or higher
* **MongoDB** (Local instance on `27017` or Atlas URI)

---

##  Setup & Installation

1.  **Clone the Repository**
    ```bash
    git clone [https://github.com/yourusername/brno-restaurants-api.git](https://github.com/yourusername/brno-restaurants-api.git)
    cd brno-restaurants-api
    ```

2.  **Configure Application Properties**
    Update `src/main/resources/application.properties`:
    ```properties
    spring.data.mongodb.uri=mongodb://localhost:27017/brno_restaurants
    spring.data.mongodb.auto-index-creation=true
    ```

3.  **Run the Project**
    ```bash
    ./mvnw spring-boot:run
    ```

---

##  API Endpoints

###  Discovery Endpoints
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/restaurants` | List all restaurants in Brno |
| `GET` | `/api/restaurants/near` | Search by location (`lat`, `lon`, `distanceKm`) |
| `GET` | `/api/restaurants/type/{category}` | Filter by category (e.g. `Pub`) |

### 🛠 Management Endpoints
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/restaurants` | Add a new restaurant |
| `DELETE` | `/api/restaurants/{id}` | Remove a restaurant by ID |

---

##  Data Structure
The API uses **GeoJSON** internally for the most accurate distance calculations.

**Example POST Body:**
```json
{
  "name": "Kachna café",
  "type": "Cafe",
  "address": "Kopečná 241/15, Brno",
  "latitude": 49.1906,
  "longitude": 16.6044,
  "rating": 4.8
}
