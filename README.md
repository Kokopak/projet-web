# Projet Web ISIS
Chataignon Corentin, Douarche Clara, Ghozali Yasmine

## Introduction

Pour notre second semestre, nous avons été amené à réaliser un projet web consistant à créer une application Web 
sur la base de données d'échantillon de Netbeans.

## Sujet
L'application est destinée à deux catégories d'utilisateurs : Les clients et l'administrateur.
### Pour les clients :
Le client doit s'authentifier pour accéder à l'édition des bons de commande.
La base sample ne contenant pas de mots de passe, on utilisera les champs suivants de la table CUSTOMER
pour l'authentification :

* login : EMAIL
* password : CUSTOMER_ID

Une fois connecté, l'application doit permettre au client l'édition complète de ses commandes (ajout,
modification, suppression).
### Pour l'administrateur :
L'administrateur s'authentifie en utilisant un login / password prédéfini dans l'application.
Une fois authentifié, l'administrateur a accès à une série de tableaux de bord graphiques qui lui permettent de
visualiser des statistiques sur les commandes saisies :

* Visualiser les chiffres d'affaires par catégorie d'article, en choisissant la période (date de début / date
de fin) sur laquelle doit porter la statistique.
* Visualiser les chiffres d'affaires par zone géographique, en choisissant la période (date de début / date
de fin) sur laquelle doit porter la statistique.
* Visualiser les chiffres d'affaires par client, en choisissant la période (date de début / date de fin) sur
laquelle doit porter la statistique.

## Répartition des groupes

Pour réaliser ce projet nous avons décidé de couper le projet en 2 parties :

* Partie frontend (Yasmine & Clara)
* Partie backend (Corentin)

## Partie Frontend

La partie frontend consistait à réaliser des maquettes en HTML/CSS/JS que l'on inclurait par la suite dans des JSP. Pour simplifier
un peu le travail, nous avons décidé d'utiliser le framework CSS [Miligram.css](https://milligram.io/). Ce framework nous
fournissait un CSS de base qui nous permettait de bien structurer notre page grâce à un système de grille tout en ayant des
composants stylisés.

Par ailleurs, pour la partie administrateur nous avions besoin de tracer des graphiques et pour cela nous avons utilisé la 
librairie Javascript [Chart.JS](http://www.chartjs.org/)


## Partie Backend

Pour le backend nous avons décidé de faire notre projet en respectant la structure MVC (Modèle - Vue - Contrôleur). 

### Modèle

Nous avons réalisé une DAO pour notre projet afin de faire le lien entre le programme Java et la base de données SQL (Derby).
Le DAO contient donc ainsi toutes les méthodes nécessaires à notre programme.

### Vue 

Les vues sont faites en JSP ce qui nous permettait "d'écrire du code Java dans du HTML" ainsi nous avons pu afficher dans la page
les variables Java envoyées par le contrôleur.

### Contrôleur

Les contrôleurs en Java se font par l'intermédiaire de Servlet, c'est dans les contrôleurs que l'on fait le lien entre le Modèle et
la Vue. En effet dans le contrôleur, les accès à la base de données sont réalisés et sont envoyés directement à la vue associée
au contrôleur.




