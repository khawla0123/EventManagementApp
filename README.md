Event Management App – Mini plateforme interne
1️⃣ Présentation du projet

Ce projet est une mini plateforme interne de gestion d’événements, développé en Java Spring Boot avec MySQL.
Il permet de gérer les utilisateurs, créer et suivre des événements selon un workflow métier précis, avec authentification sécurisée et gestion des rôles.

2️⃣ Technologies utilisées

Java 17 + Spring Boot

Spring Security (JWT ou Session)

MySQL

Maven

Git pour le versioning

Lombok pour simplifier le code

Postman / Swagger pour tester les APIs

3️⃣ Architecture

Le projet suit l’architecture MVC / séparation claire des responsabilités :

/src
  /controller   -> gestion des endpoints REST
  /service      -> logique métier
  /repository   -> interaction avec la base de données
  /model        -> entités (User, Event, Log)
  /config       -> configuration sécurité et JWT
4️⃣ Diagramme des entités
User
- id
- name
- email
- password
- role (ADMIN, MANAGER, USER)
- created_at

Event
- id
- title
- description
- event_date
- status (draft, submitted, approved, rejected, closed)
- created_by (User)
- assigned_to (User)
- created_at

Log
- id
- event_id
- old_status
- new_status
- changed_by (User)
- timestamp
5️⃣ Rôles et permissions
Rôle	Actions autorisées
USER	Créer un événement (draft)
MANAGER	Passer draft → submitted
ADMIN	Passer submitted → approved/rejected, changer rôle des utilisateurs, voir tous les utilisateurs

💡 Les routes sont protégées selon le rôle grâce à Spring Security.

6️⃣ Workflow des événements

draft → Créé par un USER

submitted → VALIDÉ par un MANAGER

approved / rejected → VALIDÉ par un ADMIN

closed → Aucun changement possible

Règles métier importantes :

Impossible de sauter une étape

Impossible de modifier un événement closed

Chaque changement de statut est enregistré dans Log

7️⃣ Historique des actions

Chaque changement de statut d’un événement est stocké dans l’entité Log :

old_status, new_status, changed_by, timestamp

Permet de retracer toutes les modifications d’un événement.

8️⃣ Instructions pour lancer le projet

Cloner le projet :

git clone https://github.com/khawla0123/EventManagementApp.git
cd EventManagementApp

Configurer la base MySQL dans src/main/resources/application.properties :

spring.datasource.url=jdbc:mysql://localhost:3306/eventdb
spring.datasource.username=root
spring.datasource.password=ton_mot_de_passe
spring.jpa.hibernate.ddl-auto=update

Lancer l’application :

mvn spring-boot:run

Tester les endpoints via Postman ou Swagger :

POST /auth/register

POST /auth/login

GET /users (ADMIN uniquement)

POST /events (USER / MANAGER / ADMIN selon rôle)

PUT /events/{id}/status

GET /events/{id}/logs

9️⃣ Difficultés rencontrées

Gestion du workflow métier pour éviter les sauts d’étape

Sécurisation des routes selon rôle

Enregistrement automatique des logs à chaque changement

🔟 Améliorations possibles

Ajouter une interface web pour la gestion des événements

Notifications par email lors du changement de statut

Pagination et filtres pour les utilisateurs et événements
