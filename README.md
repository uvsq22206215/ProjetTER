# ProjetTER

Table des matières (A modifier)
* Introduction
* Présentation des bases de données

|    |     Judilibre       |  Piratage |
|----------|:-------------:|------:|
| Description |  Projet concernant les décisions de justice.Données récupérées depuis le site de la 'Cour de cassation'  | projet concernant le piratage. Base de donnée contenant toutes les informations regroupés dans le cadre de l'étude sur l'évolution du cyber-crime |
| Modèle de BD |    Mongo DB   |  PostgreSql  |
| Données | 10 Collections |    19 tables (relationnels) |

* Comparaison des deux modèles: MongoDB v/s PostgreSQL
* Procédure d’accès aux différentes bases de données 
* Etudes des BD et résultats obtenus (Tests)
  * Similarités
  * Différences
  * Requêtes
  * Résumé 
  * Temps d’exécution
  * Mémoire utilisée
  * Avantages et inconvénients sur le long terme
  * Poids et taille des données (au fil du temps)

* Critères de sélection d’une base de données

***Présentation des solutions possibles:***

**Migration de PostgreSQL Vers MongoDB**

Pour migrer correctement, nous devrions probablement concevoir un nouveau modèle de données qui tire parti des qualités de la base de données NoSQL vers laquelle nous nous dirigeons et qui résout le problème qui nous a poussés à migrer. nous voudrons probablement dénormaliser dans une certaine mesure - nous devons donc décider comment. nous devrons nous préoccuper des choses que la base de données relationnelle prenait en charge et que la nouvelle pourrait ne pas prendre en charge : les transactions ACID ou les contrôles d'intégrité relationnelle, par exemple.

Il se peut que nous ayons simplement une base de données et que vous souhaitiez "essayer NoSQL" avec elle. Même dans ce cas, si nous voulons apprendre quelque chose d'utile, nous devons effectuer le changement correctement et concevoir le modèle non relationnel comme il se doit. C'est le point le plus délicat.

Enfin, nous devrons tenir compte des clients qui utilisent déjà la base de données - devons-nous fournir une nouvelle API aux logiciels qui accèdent aux données ? Votre ancienne base de données SQL ne fonctionnera pas sur votre nouvelle base de données NoSQL.

**Migration de MongoDB Vers PostgreSQL**
La migration des données de MongoDB vers PostgreSQL peut avoir un certain nombre d'implications et de complications potentielles. Certaines de ces implications et complications peuvent inclure

* Différences dans les types de données et les schémas : MongoDB et PostgreSQL ont des types de données et des modèles de données différents, ce qui peut entraîner des différences dans la conception des schémas. Par conséquent, la migration des données peut nécessiter des changements significatifs au niveau du schéma et peut exiger une planification minutieuse pour s'assurer que les données sont correctement traduites entre les deux systèmes.

* Différences dans le langage d'interrogation : MongoDB utilise un langage de requête basé sur les documents, tandis que PostgreSQL utilise un langage de requête basé sur SQL. Cela signifie que les requêtes écrites pour MongoDB peuvent devoir être réécrites pour fonctionner avec PostgreSQL. Cela peut prendre du temps et nécessiter une expertise importante dans les deux systèmes.

* Différences de performances : MongoDB et PostgreSQL ont des caractéristiques de performance différentes, ce qui peut entraîner des différences dans le temps d'exécution des requêtes et l'utilisation des ressources. Cela signifie que les requêtes qui fonctionnent bien sur MongoDB peuvent ne pas fonctionner aussi bien sur PostgreSQL, et vice versa.

* Considérations de sécurité : MongoDB et PostgreSQL ont des modèles de sécurité et des mécanismes de contrôle d'accès différents. Cela signifie qu'il peut être nécessaire d'évaluer soigneusement les considérations de sécurité lors de la migration des données entre les deux systèmes.

* Implications en termes de coûts : La migration des données de MongoDB vers PostgreSQL peut être coûteuse, car elle nécessite beaucoup de temps et de ressources. En outre, l'utilisation d'outils de migration ou le recours à des experts pour superviser le processus de migration peuvent entraîner des frais de licence.

En résumé, la migration des données de MongoDB vers PostgreSQL peut avoir un certain nombre d'implications et de complications potentielles. Une planification minutieuse et la prise en compte de ces facteurs peuvent contribuer à la réussite du processus de migration.

**Integration des données**
* Custom Code: écrire un code personnalisé pour extraire les données des deux bases et les fusionner en une seule base de données. (Demande connaissance en programmation + temps)
* Utiliser des outils ETL(Extract, Transform, Load) tels que Apache Nifi pour extraire les données des deux bases de données, les transformer dans un format commun, puis les charger dans une nouvelle base de données. Cette approche nécessite beaucoup d'efforts pour cartographier et transformer les données provenant de différentes BD.
* Outil tiers : tel que Pentaho Data Integration qui peut nous aider à intégrer des données provenant de plusieurs bases de données. Ces outils offrent une interface "drag and drop" pour mapper les données de différentes sources et simplifier le processus d'intégration.

**Conclusion** : Quelle est la meilleure Solution?
