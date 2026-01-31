#  Brno Restaurant API

A REST API built with Spring Boot and MongoDB, specifically designed for discovering restaurants in **Brno, Czech Republic**.
Currently populated from json file in resources package with some restaurants from **Cerna Pole** district in Brno.

## Key Features

* **Geo-Spatial Data Modeling**: Find restaurants within a specific kilometer radius using MongoDB's `2dsphere` index.
* **Automatic Data Seeding**: Populates your database with restaurants from Cerna Pole district in Brno/
* **Type Filtering**: Case-insensitive filtering for restaurant categories (e.g., "Pub", "Cafe").
* **DTO Pattern**: Clear separation between database entities and API responses for better security and flexibility.
* **Safe Mapping**: Handles the conversion between GeoJSON coordinates `[lon, lat]` and standard user-friendly `lat/lon` fields.

---

##  API Endpoints

###  Discovery Endpoints
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/restaurants` | List all restaurants |
| `GET` | `/restaurants/{id}` | Get reastaurant by Id|
| `GET` | `/restaurants/near` | Find restaurant near to your location, specify nearest by distance |
| `GET` | `/restaurants/type/{category}` | Filter by category |
| `GET` | `/restaurants/near/type/{category}` | Find restaurant near to you location, specify nearest by distance and type |

### Management Endpoints
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/restaurants` | Add a new restaurant |
| `DELETE` | `/restaurants/{id}` | Remove a restaurant by ID |
| `PUT` | `/restaurants/{id}` | Update a restaurant by ID |

---

##  Data Structure
The API uses **GeoJSON** internally for the distance calculations.

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
