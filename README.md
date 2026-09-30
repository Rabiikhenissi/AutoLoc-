# AutoLoc

## Description du projet

**AutoLoc** est une plateforme de gestion de location de véhicules multi-agences.

L'objectif du projet est de permettre la gestion des véhicules, des agences, des clients et des locations à travers une application basée sur **Java / Spring Boot**.

La plateforme permettra notamment de gérer les véhicules disponibles, les réservations et locations, les clients ainsi que les différentes agences et leurs utilisateurs.

## Objectifs

* Gérer les différentes agences de location.
* Gérer les véhicules disponibles dans chaque agence.
* Gérer les clients.
* Gérer les réservations et les locations de véhicules.
* Permettre le suivi des disponibilités des véhicules.
* Assurer la gestion des utilisateurs selon leurs rôles.
* Fournir une API REST pour les fonctionnalités de la plateforme.

## Acteurs

Le système identifie les acteurs suivants :

### Client

Le client peut :

* Consulter les véhicules disponibles.
* Rechercher un véhicule selon ses critères.
* Effectuer une réservation.
* Consulter ses réservations.
* Gérer ses informations personnelles.
* Consulter l'historique de ses locations.

### Agent d'agence

L'agent d'agence peut :

* Gérer les clients.
* Gérer les réservations.
* Gérer les locations.
* Gérer la disponibilité des véhicules de son agence.
* Effectuer les opérations liées à la prise en charge et au retour des véhicules.

### Responsable d'agence / Manager

Le responsable d'agence peut :

* Gérer les véhicules de son agence.
* Superviser les agents de l'agence.
* Consulter les réservations et locations.
* Suivre l'activité de son agence.
* Consulter les informations relatives aux véhicules et aux clients de son agence.

### Administrateur

L'administrateur peut :

* Gérer les utilisateurs de la plateforme.
* Gérer les agences.
* Superviser l'ensemble de la plateforme.
* Administrer les données globales du système.
* Gérer les paramètres généraux de l'application.

## Cas d'utilisation

Les principaux cas d'utilisation identifiés sont :

* Authentification et gestion des utilisateurs.
* Gestion des clients.
* Gestion des véhicules.
* Gestion des agences.
* Consultation de la disponibilité des véhicules.
* Recherche de véhicules.
* Réservation d'un véhicule.
* Gestion des locations.
* Prise en charge d'un véhicule.
* Retour d'un véhicule.
* Consultation de l'historique des locations.
* Gestion des utilisateurs et des rôles.
* Supervision des agences.

## Technologies prévues

* **Java 17+**
* **Spring Boot**
* **Spring Data JPA**
* **Spring MVC**
* **Spring AOP**
* **Spring Scheduler**
* **Maven**
* **MySQL**
* **JUnit 5 / Mockito**
* **Postman**
* **Git / GitHub**
* **IntelliJ IDEA**

## Équipe

Projet réalisé dans le cadre du projet intégré ESPRIT.


