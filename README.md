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

| Metod | Endpoint | Parametrar                                               | Beskrivning |
|---|---|----------------------------------------------------------|---|
| POST | `/customers` | `name`, `phone`                                          | Skapa kund |
| GET | `/customers` | –                                                        | Hämta alla kunder med deras fordon |
| GET | `/customer-id` | `name`                                                   | Hämta kund-ID via namn |
| POST | `/vehicles` | `registrationNumber`, `brand`, `model`, `productionYear` | Skapa fordon |
| GET | `/vehicles` | –                                                        | Hämta alla fordon |
| GET | `/vehicles-by-brand` | `brand`                                                  | Hämta fordon efter märke |
| POST | `/addvehicletocustomer` | `customerId`, `vehicleId`                                | Koppla fordon till kund |
| PUT | `/vehicles/{id}/brand` | `id` (i sökvägen), `brand`, `model`                      | Uppdatera fordonets märke och modell |
| GET | `/vehicle-brand-info` | –                                                        | Hämta registreringsnummer och märke |

