# Atelier 5 - API REST, tests Postman et documentation Swagger

## A. Endpoints

| Contrôleur | Verbe | Route | Service appelé | Code de succès | Codes d'erreur |
|---|---|---|---|---|---|
| AgenceController | GET | /agences | IAgence.getAllAgences() | 200 OK | - |
| AgenceController | GET | /agences/{id} | IAgence.getAgenceById(id) | 200 OK | 404 Not Found |
| AgenceController | POST | /agences | IAgence.addAgence(agence) | 201 Created | 400 Bad Request (attribut obligatoire vide) |
| AgenceController | PUT | /agences/{id} | IAgence.updateAgence(agence) | 200 OK | 404 Not Found, 400 Bad Request (attribut obligatoire vide) |
| AgenceController | DELETE | /agences/{id} | IAgence.deleteAgence(id) | 204 No Content | 404 Not Found |
| EmployeController | GET | /employes | IEmploye.getAllEmployes() | 200 OK | - |
| EmployeController | GET | /employes/{id} | IEmploye.getEmployeById(id) | 200 OK | 404 Not Found |
| EmployeController | POST | /employes | IEmploye.addEmploye(employe) | 201 Created | 400 Bad Request (nom vide) |
| EmployeController | PUT | /employes/{id} | IEmploye.updateEmploye(employe) | 200 OK | 404 Not Found, 400 Bad Request (nom vide) |
| EmployeController | DELETE | /employes/{id} | IEmploye.deleteEmploye(id) | 204 No Content | 404 Not Found |
| VehiculeController | GET | /vehicules | IVehicule.getAllVehicules() | 200 OK | - |
| VehiculeController | GET | /vehicules/{id} | IVehicule.getVehiculeById(id) | 200 OK | 404 Not Found |
| VehiculeController | POST | /vehicules | IVehicule.addVehicule(vehicule) | 201 Created | 400 Bad Request (valeur numérique négative) |
| VehiculeController | PUT | /vehicules/{id} | IVehicule.updateVehicule(vehicule) | 200 OK | 404 Not Found, 400 Bad Request (valeur numérique négative) |
| VehiculeController | DELETE | /vehicules/{id} | IVehicule.deleteVehicule(id) | 204 No Content | 404 Not Found |
| EquipementController | GET | /equipements | IEquipement.getAllEquipements() | 200 OK | - |
| EquipementController | GET | /equipements/{id} | IEquipement.getEquipementById(id) | 200 OK | 404 Not Found |
| EquipementController | POST | /equipements | IEquipement.addEquipement(equipement) | 201 Created | 400 Bad Request (libellé vide) |
| EquipementController | PUT | /equipements/{id} | IEquipement.updateEquipement(equipement) | 200 OK | 404 Not Found, 400 Bad Request (libellé vide) |
| EquipementController | DELETE | /equipements/{id} | IEquipement.deleteEquipement(id) | 204 No Content | 404 Not Found |
| ClientController | GET | /clients | IClient.getAllClients() | 200 OK | - |
| ClientController | GET | /clients/{id} | IClient.getClientById(id) | 200 OK | 404 Not Found |
| ClientController | POST | /clients | IClient.addClient(client) | 201 Created | 400 Bad Request (attribut d'identification vide) |
| ClientController | PUT | /clients/{id} | IClient.updateClient(client) | 200 OK | 404 Not Found, 400 Bad Request (attribut d'identification vide) |
| ClientController | DELETE | /clients/{id} | IClient.deleteClient(id) | 204 No Content | 404 Not Found |
| ReservationController | GET | /reservations | IReservation.getAllReservations() | 200 OK | - |
| ReservationController | GET | /reservations/{id} | IReservation.getReservationById(id) | 200 OK | 404 Not Found |
| ReservationController | POST | /reservations | IReservation.addReservation(reservation) | 201 Created | 400 Bad Request (date de fin antérieure) |
| ReservationController | PUT | /reservations/{id} | IReservation.updateReservation(reservation) | 200 OK | 404 Not Found, 400 Bad Request (date de fin antérieure) |
| ReservationController | DELETE | /reservations/{id} | IReservation.deleteReservation(id) | 204 No Content | 404 Not Found |
| ContratController | GET | /contrats | IContrat.getAllContrats() | 200 OK | - |
| ContratController | GET | /contrats/{id} | IContrat.getContratById(id) | 200 OK | 404 Not Found |
| ContratController | POST | /contrats | IContrat.addContrat(contrat) | 201 Created | - |
| ContratController | PUT | /contrats/{id} | IContrat.updateContrat(id, contrat) | 200 OK | 404 Not Found, 400 Bad Request (montantTotal négatif) |
| ContratController | DELETE | /contrats/{id} | IContrat.deleteById(id) | 204 No Content | 404 Not Found |
| PaiementController | GET | /paiements | IPaiement.getAllPaiements() | 200 OK | - |
| PaiementController | GET | /paiements/{id} | IPaiement.getPaiementById(id) | 200 OK | 404 Not Found |
| MaintenanceController | GET | /maintenances | IMaintenance.getAllMaintenances() | 200 OK | - |
| MaintenanceController | GET | /maintenances/{id} | IMaintenance.getMaintenanceById(id) | 200 OK | 404 Not Found |
| MaintenanceController | POST | /maintenances | IMaintenance.addMaintenance(maintenance) | 201 Created | 400 Bad Request (coût négatif / date de fin antérieure) |
| MaintenanceController | PUT | /maintenances/{id} | IMaintenance.updateMaintenance(maintenance) | 200 OK | 404 Not Found, 400 Bad Request (coût négatif / date de fin antérieure) |
| MaintenanceController | DELETE | /maintenances/{id} | IMaintenance.deleteMaintenance(id) | 204 No Content | 404 Not Found |

## B. Annotations Jackson

### Association et annotation retenue
| Relation | Annotation retenue | Justification |
|---|---|---|
| Contrat → Paiement (1:N) | `@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)` + `@JoinColumn(name = "contrat_id")` sur Contrat, `@ManyToOne` + `@JoinColumn(name = "contrat_id")` + `@JsonBackReference` sur Paiement | Permet la cascade (persist/remove) depuis le Contrat vers ses Paiements. `@JsonManagedReference` sur Contrat.paiements et `@JsonBackReference` sur Paiement.contrat évitent la récursion infinie lors de la sérialisation JSON tout en maintenant le lien bidirectionnel. |
| Agence → Vehicules/Employes | `@OneToMany(mappedBy = ...)` côté inverse, `@JsonIgnore` parfois utilisé selon les besoins | Évite la sérialisation récursive et l'explosion du JSON en limitant les références circulaires. |
| Reservation → Contrat / Vehicule / Client | Relations simples avec `@ManyToOne/@OneToOne` | Gérées selon le besoin d'affichage dans Postman/Swagger. |

### Différence entre `@JsonBackReference` et `@JsonIgnore`
- **`@JsonBackReference`** : Indique que cette propriété est la "back" (arrière) d'une relation gérée. Elle est ignorée lors de la sérialisation, mais conservée lors de la désérialisation. Utile pour les relations bidirectionnelles où l'on veut serialiser uniquement le côté géré (managed).
- **`@JsonIgnore`** : Ignore complètement la propriété à la fois pour la sérialisation et la désérialisation. Plus radical, empêche totalement l'accès à cette propriété dans JSON. À utiliser quand on ne veut jamais exposer ce champ.

## C. Postman et Swagger

- **Postman** : Outil de test manuel d'API. Permet d'organiser les requêtes par dossiers (nominales, 404, 400), de gérer les variables ({{baseUrl}}, {{contratId}}), de tester les différents verbes HTTP et de valider les codes de statut/corps de réponse.
- **Swagger UI** (Springdoc/OpenAPI) : Documentation interactive générée automatiquement. Permet de visualiser tous les endpoints, schémas des entités, d'essayer les requêtes directement depuis le navigateur sans Postman. Fournit aussi une vue unifiée du contrat API.

**Exemple concret rencontré dans l'atelier :**
Lors de la création d'un contrat avec ses paiements (`POST /contrats` avec le tableau `paiements` dans le corps), Postman a retourné 201 Created et les paiements sont apparus dans `GET /paiements` sans création séparée, grâce à `CascadeType.ALL` avec `@JoinColumn`. L'erreur 405 initiale sur PUT était due à l'absence du contrôleur dans le bon package, résolue en le plaçant dans `web/controller`.

## D. Anomalies SonarQube

| Règle | Explication | Correction |
|---|---|---|
| **java:S2259 - Null pointer dereference** | Dans certains contrôleurs/services, on accède à des propriétés sans vérifier null (ex. `contrat.getMontantTotal().compareTo(...)` sans s'assurer que l'objet/niveau existe). | Ajouter des vérifications null explicites ou utiliser `Objects.nonNull()`. Dans nos contrôleurs, nous avons ajouté des contrôles pour les cas requis (404, 400). |
| **java:S1488 - Local Variables should not be declared and then immediately returned** | Déclarer une variable locale juste pour la retourner immédiatement (ex. `Contrat saved = service.add(...); return new ResponseEntity<>(saved, HttpStatus.CREATED);`) dans certains endroits moins critique, mais peut être simplifié. | Retourner directement : `return new ResponseEntity<>(contratService.addContrat(contrat), HttpStatus.CREATED);` ou simplement `return ResponseEntity.status(HttpStatus.CREATED).body(...)`. À adapter selon les conventions du projet. |

## E. Questions de compréhension

### 1. Quel composant reçoit toutes les requêtes, et comment retrouve-t-il la méthode du contrôleur à appeler ?
Le **DispatcherServlet** (Front Controller de Spring MVC) reçoit toutes les requêtes HTTP. Il utilise le **HandlerMapping** pour trouver le handler (méthode du contrôleur) correspondant à l'URL, la méthode HTTP et les paramètres. Ensuite, il passe par le **HandlerAdapter** qui invoque la méthode du contrôleur avec les arguments résolus, et gère la réponse.

### 2. Pourquoi ContratController dépend-il de IContratService et non de ContratRepository ni de ContratServiceImpl ?
Pour respecter le **principe d'inversion de dépendance (DIP)** et le **couplage faible**. Dépendre de l'interface (`IContrat`) permet de :
- Changer facilement l'implémentation sans modifier le contrôleur
- Faciliter les tests unitaires avec des mocks
- Respecter l'architecture en couches (Contrôleur → Service → Repository)

### 3. Pourquoi un POST renvoie-t-il 201 et un DELETE 204 ? Pourquoi une ressource absente donne-t-elle 404 et non 500 ?
- **POST → 201 Created** : Indique qu'une ressource a été créée avec succès sur le serveur.
- **DELETE → 204 No Content** : Indique que la suppression a réussi, mais il n'y a pas de corps de réponse à renvoyer.
- **404 Not Found** : C'est une erreur **client** (ressource inexistante) et non une erreur **serveur**. Un 500 Internal Server Error signifierait un bug côté serveur. Un 404 est donc plus sémantique et approprié quand la ressource demandée n'existe pas.

### 4. Pourquoi PaiementController n'expose-t-il que des GET ? Quelle réponse reçoit un POST /api/paiements ?
**PaiementService est en lecture seule** car c'est le **Contrat qui porte ses paiements** via `cascade = ALL`. Les paiements sont créés automatiquement lors de la création d'un Contrat avec la liste `paiements`. Si on envoie un `POST /paiements` sans méthode dans le contrôleur, Spring MVC renverra **405 Method Not Allowed** car aucune méthode `@PostMapping` n'est définie.

### 5. Quels risques présente l'exposition directe des entités JPA ? Pourquoi Contrat.paiements utilise-t-il @JsonManagedReference alors que Agence.vehicules utilise @JsonIgnore ?
**Risques de l'exposition directe des entités JPA :**
- **Boucles infinies** lors de la sérialisation JSON (relations bidirectionnelles)
- **LazyLoadingException** / problèmes de sessions Hibernate
- **Exposition de données sensibles** non voulues
- **Couplage fort** entre API et modèle de persistance
- **Problèmes de performance** (chargement excessif de relations)

**Différenciation :**
- **Contrat.paiements** utilise `@JsonManagedReference` / `@JsonBackReference` car on veut **garder le lien bidirectionnel fonctionnel** pour le **cascade** et pour pouvoir naviguer entre Contrat et Paiement. On veut serialiser les paiements quand on récupère un contrat (et vice-versa de manière contrôlée) pour répondre aux besoins du TP (création en cascade).
- **Agence.vehicules** utilise `@JsonIgnore` (ou approche similaire) pour **rompre complètement la boucle** et éviter l'explosion JSON. On privilégie ici la simplicité en ignorant la relation inverse, car l'objectif est souvent d'afficher l'agence ou les véhicules séparément sans sérialisation récursive excessive.
