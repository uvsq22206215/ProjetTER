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

**Solutions implémentées**

***Fusion**
Pour mettre en œuvre cette solution, nous avons implémenté un code/scripte en python personnalisé pour extraire les
données des deux bases et les fusionner en une seule base de données. L'implémentation du scripte suit les étapes suivantes:
- Choix d’un modèle de BD adapté : PostgreSQL

- Analyse des schémas de données : 
  * La première étape consiste à analyser les schémas de données des deux bases de données et à identifier les données à fusionner. Ici on migre les données de la BD mongoDB vers la BD relationnelle, PostgreSQL.
  * Installation de Python et des librairies à utiliser
  * Création d'une base de donnée en local s'appelant "piratage_judilibre" & Création d'un utilisateur en loca (user_ter) ayant accès à cette base (la solution a été créé pour faire l'intégration en local)
  * Exportation des données : Les données à fusionner doivent être exportées depuis les deux bases de données. Il existe
  plusieurs outils d'exportation de données disponibles pour MongoDB et PostgreSQL, tels que mongodump pour MongoDB et pgdump pour PostgreSQL. Nous avons utilisé pgdump pour avoir les données de la base Piratage et nous n’avions qu'à exécuter le fichier .dump généré, sur la base de données cible comme ils étaient tous les deux du même modèle(avec la commande psql). 
  * Puis nous avons utilisé la bibliothèque ‘pymongo’ pour pouvoir collecter les données des différentes collections de Judilibre en format ‘clé-valeur’(illustré ci-dessous).


- Conversion/Transformation des données : Comme les données exportées depuis MongoDB sont dans un format semi-structuré, il a donc été nécessaire de les convertir dans un format plus compatible au modèle postgreSQL. Bien qu’il soit possible d'utiliser des outils de conversion tels que Talend, ou Pentaho, nous avons choisi de s’occuper de la transformation à la main, en utilisant nos connaissances acquises dans le cours de conception de BD. Nous avons un peu procédé à un ‘reverse engineering’ à partir du modèle NoSQL de mongoDB vers un modèle entités associations puis vers un modèle relationnelle de table postgreSQL.

* Les tables créées(avec un prefixe ‘jl_’):
amende(), 
cluster_minibatchkmeans, 
cluster100, 
cluster200, 
cluster50, 
decision, 
ferme, 
peine, 
sursis, 
word_minibatchkmeans. 

* D’autres tables qui pourraient être utilisées pour les associations:
contested, 
number, 
numberDecisions, 
publication, 
pubDecision, 
theme, 
themeDecision, 
visa, 
visaDecision, 
rapprochement, 
rapDecision, 
timeline, 
timelineDecision


**Argument du type de transformation choisi** 
Il existe effectivement d’autre types de transformations qui auraient pu être faits: par exemple, les structures de champs ‘String array’ en Mongodb pourraient être transformées en ‘TEXT[]’ en PostgreSQL. De plus, les ‘Object Array’ en Mongodb pourraient être transformées en ‘JSONB[]’. Néanmoins nous avons opté pour la simplicité et n’avons pas risqué d’utiliser des notions que l'on ne maîtrise pas vraiment. 

- Fusion des données : Une fois les données converties (les tables créées dans la nouvelle base cible), il a été temps de les fusionner en utilisant les facilités offertes par les librairies utilisées sur python comme: pymongo, pandas ou psycopg2
- Importation des données fusionnées : Enfin, les données fusionnées peuvent être importées dans une nouvelle base de données ou dans une des bases de données existantes.
- Vérification des données : Une fois les données fusionnées et importées, il est important de vérifier leur qualité et leur intégrité. La vérification des données a été effectuée à l'aide de l’outil pgAdmin sur nos machines en locale.

Néanmoins, la fusion a quand même un certain nombre d'implications et de complications potentielles, qui demande du temps et de la recherche supplémentaire. Certaines de ces implications et complications sont:
    * Différences dans la conception des schémas: la migration des données a nécessité des changements significatifs au niveau du schéma. Cette solution peut exiger une planification minutieuse pour s'assurer que les données sont correctement traduites entre les deux systèmes.
    * Différences dans le langage d'interrogation : MongoDB utilise un langage de requête basé sur les documents, tandis que PostgreSQL utilise un langage de requête basé sur SQL. Cela signifie que les requêtes écrites pour MongoDB peuvent devoir être réécrites pour fonctionner avec PostgreSQL. Cela peut prendre du temps et nécessiter une expertise importante dans les deux systèmes.
    * Différences de performances : MongoDB et PostgreSQL ont des caractéristiques de performance différentes, ce qui peut entraîner des différences dans le temps d'exécution des requêtes et l'utilisation des ressources. Cela signifie que les requêtes qui fonctionnent bien sur MongoDB peuvent ne pas fonctionner aussi bien sur PostgreSQL, et vice versa.
    * Considérations de sécurité : MongoDB et PostgreSQL ont des modèles de sécurité et des mécanismes de contrôle d'accès différents. Cela signifie qu'il peut être nécessaire d'évaluer soigneusement les considérations de sécurité lors de la migration des données entre les deux systèmes.
    * La migration des données de MongoDB vers PostgreSQL peut avoir un certain nombre d'implications et de complications potentielles mais une planification minutieuse et la prise en compte de ces facteurs peuvent contribuer à la réussite du processus de migration.

***Solution médiateur**
    **Présentation d’une solution médiateur en CustomCode codée en java**
L’autre solution que nous avons mise en place est l'implémentation d’une application qui sert de lien entre la base MongoDB et PostgreSQL. Les données restent là où elles sont et l’utilisateur peut via une application médiateur choisir la base sur laquelle il veut travailler. Cette solution nous a semblé évidente pour plusieurs raisons. Premièrement le fait que les deux bases n’ont strictement rien à voir et auront surement des utilisations et des traitements différents, donc nous doutons fort qu’elles seront utilisées en parallèle dans un usage commun et tourné vers le même objectif. On peut très bien imaginer deux profils d’utilisateurs différents qui auront des objectifs différents vis à vis de la base de données souhaitée et cette approche considère que fusionner les données au même endroit n’a pas de sens et amène juste plus de complexité dans le traitement de l’information que l’utilisateur voudra faire, étant donné que les données des deux bases sont drastiquement différentes. De nombreux autres facteurs peuvent nous faire pencher faire cette solution comme : 
1. Flexibilité : En utilisant une solution médiateur, on peut laisser les données dans leur base respective (PostgreSQL et MongoDB) sans avoir à les transférer ou les convertir d'une base à l'autre. Cela permet de préserver la structure et les fonctionnalités spécifiques de chaque base de données, tout en permettant d'accéder aux données de manière transparente.
2. Hétérogénéité des données : PostgreSQL et MongoDB sont deux bases de données différentes, avec des modèles de données différents. PostgreSQL est une base de données relationnelle, tandis que MongoDB est une base de données orientée document. En utilisant une solution médiateur, on peut exploiter les avantages de chaque modèle de données et accéder à des fonctionnalités spécifiques à chaque base de données.
3. Réutilisabilité du code : En développant une application commune en Java qui utilise des drivers pour communiquer avec les bases de données, on peut réutiliser le code existant et éviter la duplication. Cela simplifie la maintenance et les mises à jour, car les changements ne doivent être effectués qu'à un seul endroit.
4. Facilité d'accès aux données : Avec une solution médiateur, on peut exécuter des requêtes sur les deux bases de données via une application commune. Cela facilite l'accès aux données, car les utilisateurs n'ont pas besoin de connaître les spécificités de chaque base de données. De plus, cela permet d'effectuer des opérations de jointure et de combinaison de données provenant des deux bases de données.
5. Scalabilité : Une solution médiateur peut offrir une évolutivité en permettant l'ajout de nouvelles bases de données à intégrer facilement. Par exemple, si nous souhaitons ajouter une autre base de données NoSQL, nous pouvons étendre la solution médiateur existante sans avoir à modifier l'application commune.
6. Coût : En évitant la nécessité de migrer toutes les données d'une base à l'autre, nous pouvons réduire les coûts associés à la conversion et à la maintenance de deux bases de données distinctes. De plus, en utilisant une application commune, nous pouvons économiser sur les ressources nécessaires pour développer et maintenir deux applications distinctes pour chaque base de données.

En résumé, l'utilisation d'une solution médiateur avec des drivers Java pour intégrer une base de données PostgreSQL et MongoDB offre une flexibilité, une réutilisabilité du code, une facilité d'accès aux données, une scalabilité et une réduction des coûts. Cela permet de tirer parti des avantages spécifiques de chaque base de données tout en simplifiant le développement et la maintenance de l'application globale.


**Passons à la présentation de l’application.**

L’implémentation globale utilisera le design pattern Commande. Via une hashmap, nous stockons les chaînes de caractères et les objets associés qui serviront à lancer la commande que l’utilisateur écrira dans la ligne de commande. L’utilisateur devra alors rentrer “PostgreSQL” ou “MongoDB” pour se connecter à la base qu’il veut. La possibilité de quitter l’application existe via la commande “EXIT”. Que l’utilisateur se connecte à MongoDB ou PostgreSQL, une instance de la classe connecterCommande sera créée avec la bonne base associée ( ici on a mis une chaine de caractère).

Toutes les actions que l’utilisateur pourra écrire au clavier auront une classe associée comme celle ci, on aura besoin d’un objet de type receiver qui comportera toutes les implémentations des différentes actions que peut faire l’utilisateur. Pour connecterCommande on a pas aussi un String qui représente la base.
Je ne montrerai pas les autres classes pour les autres actions étant donné qu’elles ont toutes le même schéma, c’est à dire qu’elle ont toutes comme attribut le receiver ainsi que des objets qui seront primordiales pour le traitement des actions dans la classe Receiver qui contient l’implémentation des actions.
Voici la méthode connect dans la classe Receiver.

On se connecte à l’une ou l’autre base de données en fonction du string passé en argument. Pour la connexion à la base postgreSQL on charge le driver via Class.forName, on établit ensuite la connexion via DriverManager.getConnection en faisant attention à inclure le nom de la base et le mot de passe d’accès. Pour la connexion à la base MongoDB on utilise MongoClients.create à laquelle on passe le lien de connexion à la base et à la bonne collection. Après s’être connecté à l’une des deux bases on crée les instances associées.


Les deux classes possèdent des Hashmaps pour stocker les actions que l’utilisateur pourra faire sur les deux bases. On a essayé d’implémenter toutes les actions CRUD possibles pour qu’un utilisateur ait vraiment l’impression d’utiliser un SGBD.
Sur la base PostgreSQL, un utilisateur peut par exemple select toute la table bsa.

On stocke le résultat de la requête dans un ResultSet que l’on pourra parcourir pour y afficher chaque tuple attribut par attribut. L’utilisateur peut aussi afficher qu’un seul tuple de cette base, il peut aussi faire un update .

La variable numRowsAffected représente le nombre de tuples affectés par la requête, cela permet de savoir si la requête de l’utilisateur a eu de l’impact ou pas.
L’utilisateur peut aussi afficher le nom de toutes les tables de la base : 

La requête dans le ExecuteQuery va chercher tous les noms de tables auxquels l’utilisateur peut accéder.
L’utilisateur peut aussi insérer un tuple.

Il peut savoir si le tuple a été effectivement crée ou non grâce à numRowsAffected.
L’utilisateur peut très bien fermer la connexion pour revenir à l’interface de choix de connexion entre MongoDB et PostgreSQL.

**Passons aux méthodes de la base MongoDB**

L’utilisateur peut faire un select de la collection entière. Etant donné que la collection contient beaucoup de documents, on a choisi d’implémenter ce select en affichant les tuples petit à petit. Si l’utilisateur veut afficher les tuples suivants il appuie sur la touche entrée, s' il veut quitter l’affichage il appuie sur la touche e. On utilise un cursor sur lequel on va appliquer la méthode skip pour ne pas avoir les mêmes tuples à chaque affichage, ainsi la nouveauté des documents est garantie.

Le scanner attend le string que l’utilisateur rentrera et le stocke dans un filtre. Ce filtre servira pour limiter la méthode find aux documents qui contiennent le même id rentré par l’utilisateur.
La collection contient des décisions de cours d’appels et de cours d’assise. Après étude de la collection, nous avons remarqué que les collections ayant le champ “source” égal à jurica étaient des décisions de cour d’appel tandis que les champs “source” égaux à dila ou jurinet étaient des décisions de cours d’assises. Ainsi en spécifiant l’un des trois champs, l’utilisateur peut aussi afficher les documents associées aux décisions qu’il souhaite.

Étant donné que l’affichage contient beaucoup de documents, nous utilisons encore cette notion de skip pour afficher petit à petit les documents et quitter quand l’utilisateur en a assez vu.
L’utilisateur peut aussi supprimer un document. On utilise encore un filtre sur lequel on va appliquer la méthode deleteOne pour la suppression. L’utilisateur peut aussi insérer un document.

On peut alors entrer l’id que l’on souhaite et le document associé va alors être crée et inséré.
Nous sommes bien d’accord que l’utilisation de ces deux bases est très limitée avec notre application. En effet pour la base PostgreSQL on peut juste select la table bsa, insérer, supprimer et modifier un tuple particulier et ça c’est pareil pour MongoDB via l’insertion d’un document, on ne peut pas choisir les champs. Nous sommes conscients que pour rendre l’application complète il faudrait implémenter des actions et des méthodes pour chaque table et chaque possibilité de changements de tuples, ou alors écrire des méthodes aux instructions pré compilées que l’on pourra modifier en changeant les valeurs et les champs qui nous intéressent. Via notre proposition, nous voulons montrer que nous savons gérer les différentes actions CRUD, le manque de temps ne nous permet malheureusement pas d’implémenter l’étendue complète des traitements normalement possibles sur des SGBDs classiques.
