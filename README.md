# DSL interne (SQL) en Java
* Base de données : HSQLDB
* DSL interne : JOOQ

## Auteur
LHOMME Anthony

## Instalation
### Importation du projet
Sous eclipse importer le projet en tant que maven project `File -> Import... -> Existing Maven Projects`
### Lancement
1. Lancer la base de donnée via le script run-hsqldb-server.sh
2. Sous eclipse exécuter la classe Main en tant que Java Application

## Configuration HSQLDB
La base de données HSQLDB est gérée via 2 script:
* run-hsqldb-server.sh : script de lancement du server de base de données
* show-hsqldb.sh : script pour acceder à la base via une interface graphique
### Création
La base de données est enregistrée dans le repertoire data.
Il n'est donc pas necessaire de recréer la base.
Si la base est inexistante ou corrompue, le fichier database.sql contient le code SQL pour la création de la base
### Accès
Après avoir lancé le serveur via le script run-hsqldb-server.sh, il est possible d'acceder à la base de données avec le script show-hsqldb.sh.
Puis dans `Type` selectionner `HSQL Database Engine Server`
