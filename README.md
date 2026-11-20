# CarWorkshop

REST API byggt med Spring Boot och Spring Data JPA för att hantera kunder och fordon.

## Starta applikationen

Öppna en terminal och navigera till projektmappen.

**Windows (CMD):**
```cmd
start.cmd
```

**Linux:**
```bash
bash start.cmd
```

Applikationen körs på `http://localhost:8080`.

## Endpoints

| Metod | Endpoint | Parametrar | Beskrivning |
|---|---|---|---|
| POST | `/customers` | `name`, `phone` | Skapa kund |
| GET | `/customers` | – | Hämta alla kunder med deras fordon |
| GET | `/customer-id` | `name` | Hämta kund-ID via namn |
| POST | `/vehicles` | `registrationNumber`, `brand`, `model`, `productionYear` | Skapa fordon |
| GET | `/vehicles` | – | Hämta alla fordon |
| GET | `/vehicles-by-brand` | `brand` | Hämta fordon efter märke |
| POST | `/addvehicletocustomer` | `customerId`, `vehicleId` | Koppla fordon till kund |
| PUT | `/vehicles/{id}/brand` | `id`, `brand`, `model` | Uppdatera märke och modell |
| GET | `/vehicle-brand-info` | – | Hämta registreringsnummer och märke |



Exempel på API-anrop:

### Kunder

**Skapa kund**

POST http://localhost:8080/customers?name=Petra&phone=0701234567

**Hämta alla kunder med deras fordon**

GET http://localhost:8080/customers

**Hämta kund-ID via namn**

GET http://localhost:8080/customer-id?name=Petra


### Fordon

**Skapa fordon**

POST http://localhost:8080/vehicles?registrationNumber=ABC123&brand=Ford&model=Mondeo&productionYear=2006

**Hämta alla fordon**

GET http://localhost:8080/vehicles

**Hämta fordon efter märke**

GET http://localhost:8080/vehicles-by-brand?brand=Ford

**Koppla fordon till kund**

POST http://localhost:8080/addvehicletocustomer?customerId=1&vehicleId=1

**Uppdatera fordonets märke och modell**

PUT http://localhost:8080/vehicles/1/brand?brand=Volvo&model=V70

**Hämta registreringsnummer och märke**

GET http://localhost:8080/vehicle-brand-info


