# ProjetTER

Table des matières (A modifier)
* Introduction
* Présentation des bases de données

|    |     Judilibre       |  Piratage |
|----------|:-------------:|------:|
| Description |  Projet concernant les décisions de justice.Données récupérées depuis le site de la 'Cour de cassation'  | projet concernant le piratage. Base de donnée contenant toutes les informations regroupés dans le cadre de l'étude sur l'évolution du cyber-crime |
| Modèle de BD |    Mongo DB   |  PostgreSql  |
| Données | 2 Collections |    18 tables (relationnels) |

* MongoDB v/s PostgreSQL
* Similarités
* Différences
* Résumé 
* Critères de sélection d’une base de données
* Procédure d’accès aux différentes bases de données 
* Etudes des BD et résultats obtenus (Tests)
* Requêtes
* Temps d’exécution
* Mémoire utilisée
* Avantages et inconvénients sur le long terme
* Poids et taille des données (au fil du temps)
* Présentation des solutions possibles:
* Custom Code: écrire un code personnalisé pour extraire les données des deux bases et les fusionner en une seule base de données. (Demande connaissance en programmation + temps)
* Utiliser des outils ETL(Extract, Transform, Load) tels que Apache Nifi pour extraire les données des deux bases de données, les transformer dans un format commun, puis les charger dans une nouvelle base de données. Cette approche nécessite beaucoup d'efforts pour cartographier et transformer les données provenant de différentes BD.
* Outil tiers : tel que Pentaho Data Integration qui peut nous aider à intégrer des données provenant de plusieurs bases de données. Ces outils offrent une interface "drag and drop" pour mapper les données de différentes sources et simplifier le processus d'intégration.
* Conclusion : Quelle est la meilleure Solution?
