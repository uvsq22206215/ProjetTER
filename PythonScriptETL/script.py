import psycopg2
import pymongo
import pandas as pd  # for handling the data in a tabular format

#*********************** CONNECTION TO POSTGRES *****************************
# Connect to the source database
# conn_src = psycopg2.connect(
#     host="pg.adam.uvsq.fr",
#     database="piratage",
#     user="piratage",
#     password="f0f89cee55"
# )

# query = "SELECT * FROM population"
# data = pd.read_sql(query, conn_src)
# print(data)

# Connect to the destination database
conn_dest = psycopg2.connect(
    host="localhost",
    database="piratage_judilibre",
    user="user_ter",
    password="terUserPwd"
)

# Create a cursor object for the source database
# cur_src = conn_src.cursor()

# Create a cursor object for the destination database
cur_dest = conn_dest.cursor()

#*********** COPY OF THE POSTGRES REMOTE DB TO A LOCALLY CREATED ONE **************
# A executer les commandes suivante sur le terminal
# Create a backup of the source database
# "pg_dump -U piratage -h pg.adam.uvsq.fr -d piratage -f piratage.dump"

# Restore the dump file to the destination database
# "psql -U user_ter -h localhost -d piratage_judilibre -f piratage.dump"

# Close the cursor objects and database connections
# cur_src.close()
# cur_dest.close()
# conn_src.close()
# conn_dest.close()

#*********** TRANSFORMING THE MONGODB COLLECTIONS TO POSTGRES TABLES **************
# Creation of the tables

create_amende_table_query = '''CREATE TABLE jl_amende
          (ID INT PRIMARY KEY NOT NULL,
          ANNEE DATE, MONTANT TEXT, __V INT); '''

create_clustermin_table_query = '''CREATE TABLE jl_cluster_minibatchkmeans
          (ID INT PRIMARY KEY NOT NULL,
          CLUSTER TEXT); '''

create_cluster100_table_query = '''CREATE TABLE jl_cluster100
          (ID INT PRIMARY KEY NOT NULL,
          CLUSTER TEXT); '''

create_cluster200_table_query = '''CREATE TABLE jl_cluster200
          (ID INT PRIMARY KEY NOT NULL,
          CLUSTER TEXT); '''

create_cluster50_table_query = '''CREATE TABLE jl_cluster50
          (ID INT PRIMARY KEY NOT NULL,
          CLUSTER TEXT); '''

create_ferme_table_query = '''CREATE TABLE jl_ferme
          (ID INT PRIMARY KEY NOT NULL,
          ANNEE DATE, DUREE TEXT, __V INT); '''

create_peine_table_query = '''CREATE TABLE jl_peine
          (ID INT PRIMARY KEY NOT NULL,
            DATA INT, AVERAGE FLOAT, __V INT); '''

create_sursis_table_query = '''CREATE TABLE jl_sursis
          (ID INT PRIMARY KEY NOT NULL,
            ANNEE DATE, DUREE TEXT, __V INT); '''

create_wordmin_table_query = '''CREATE TABLE jl_word_minibatchkmeans
          (ID INT PRIMARY KEY NOT NULL,
            CLUSTER TEXT, WORDS TEXT); '''

# ***************** CREATE QUERIES FOR SUPPLEMENTARY TABLES (obtained from decision collection) ***********************
create_number_table_query = '''CREATE TABLE jl_number
          (ID INT PRIMARY KEY NOT NULL,
            NUMBER TEXT); '''

create_contested_table_query = '''CREATE TABLE jl_contested
          (ID INT PRIMARY KEY NOT NULL,
            DATE TEXT, TITLE TEXT, IDNUMBER INT REFERENCES jl_number(ID)); '''

create_publicaton_table_query = '''CREATE TABLE jl_publication
          (ID INT PRIMARY KEY NOT NULL,
            PUBLICATION TEXT); '''

create_theme_table_query = '''CREATE TABLE jl_theme
          (ID INT PRIMARY KEY NOT NULL,
            THEME TEXT); '''

create_visa_table_query = '''CREATE TABLE jl_visa
          (ID INT PRIMARY KEY NOT NULL,
            TITLE TEXT); '''

create_rap_table_query = '''CREATE TABLE jl_rapprochement
          (ID INT PRIMARY KEY NOT NULL,
            TITLE TEXT); '''

create_timeline_table_query = '''CREATE TABLE jl_timeline
          (ID INT PRIMARY KEY NOT NULL,
            DATE TEXT, TITLE TEXT, JURISDICTION TEXT,
            CHAMBER TEXT, SOLUTION TEXT, NUMBER TEXT,
            URL TEXT); '''

# *********************************************************************************

# The queries below needs to be executed after all other queries since they contain foreign keys
create_decision_table_query = '''CREATE TABLE jl_decision
          (ID INT PRIMARY KEY NOT NULL,
          IDCONT INTEGER REFERENCES jl_contested(ID),
          SOURCE TEXT, TEXTDECISION TEXT, CHAMBER TEXT, 
          DECISION_DATE DATE, JURISDICTION TEXT, NUMBER TEXT,
          SOLUTION TEXT, TYPE TEXT, SUMMARY TEXT, UPDATE_DATE DATE,
          FORWARD TEXT, __V INT); '''

# The tables created below are the association tables related to decision
create_numDecision_table_query = '''CREATE TABLE jl_number_decision
            (IDNUM INTEGER REFERENCES jl_number(ID),
            IDDECISION INTEGER REFERENCES jl_decision(ID),
            PRIMARY KEY (IDNUM, IDDECISION)); '''

create_pubDecision_table_query = '''CREATE TABLE jl_pub_decision
            (IDPUB INTEGER REFERENCES jl_publication(ID),
            IDDECISION INTEGER REFERENCES jl_decision(ID),
            PRIMARY KEY (IDPUB, IDDECISION)); '''

create_themeDecision_table_query = '''CREATE TABLE jl_theme_decision
            (IDTHEME INTEGER REFERENCES jl_theme(ID),
            IDDECISION INTEGER REFERENCES jl_decision(ID),
            PRIMARY KEY (IDTHEME, IDDECISION)); '''

create_visaDecision_table_query = '''CREATE TABLE jl_visa_decision
            (IDVISA INTEGER REFERENCES jl_visa(ID),
            IDDECISION INTEGER REFERENCES jl_decision(ID),
            PRIMARY KEY (IDVISA, IDDECISION)); '''

create_rapDecision_table_query = '''CREATE TABLE jl_rap_decision
            (IDRAP INTEGER REFERENCES jl_rapprochement(ID),
            IDDECISION INTEGER REFERENCES jl_decision(ID),
            PRIMARY KEY (IDRAP, IDDECISION)); '''

create_timelineDecision_table_query = '''CREATE TABLE jl_timeline_decision
            (IDTL INTEGER REFERENCES jl_timeline(ID),
            IDDECISION INTEGER REFERENCES jl_decision(ID),
            PRIMARY KEY (IDTL, IDDECISION)); '''

# cur_dest.execute(create_amende_table_query + create_clustermin_table_query + create_cluster100_table_query +
#                     create_cluster200_table_query + create_cluster50_table_query +
#                     create_ferme_table_query + create_peine_table_query + create_sursis_table_query +
#                     create_wordmin_table_query + create_number_table_query + create_contested_table_query +
#                     create_publicaton_table_query + create_theme_table_query + create_visa_table_query +
#                     create_rap_table_query + create_timeline_table_query + create_decision_table_query +
#                     create_numDecision_table_query + create_pubDecision_table_query + create_themeDecision_table_query +
#                     create_visaDecision_table_query + create_rapDecision_table_query + create_timelineDecision_table_query)

# conn_dest.commit()

#*********************** CONNECTION TO MONGODB *****************************
# Connect to MongoDB
mongo_client = pymongo.MongoClient("mongodb+srv://Zeyphax:zeyphax00@bd-decisions.eok3p.mongodb.net/")
mongo_db = mongo_client["mshclemi"]
#------ fetching only one collection at a time: ------
amende_collection = mongo_db["amende"]
cluster_min_collection = mongo_db["cluster-minibatchkmeans"]
cluster_100_collection = mongo_db["cluster100"]
cluster_200_collection = mongo_db["cluster200"]
cluster_50_collection = mongo_db["cluster50"]
decision_collection = mongo_db["decision"]
ferme_collection = mongo_db["ferme"]
peine_collection = mongo_db["peine"]
sursis_collection = mongo_db["sursis"]
word_collection = mongo_db["word-minibatchkmeans"]

# Extract data from MongoDB
mongo_data = amende_collection.find()

# mongo_data = mongo_collection.find({},{ "_id": 1, "annee": 1, "montant": 1 })

# for x in mongo_data:
#   print(x)

# ********************** INSERTING THE MONGO DATA TO CREATED TABLES *********************
pg_data = []
for doc in mongo_data:
    pg_doc = {
        "annee": doc["annee"],
        "montant": doc["montant"],
        "__v": doc["__v"]
    }
    pg_data.append(pg_doc)

#*********** LOADING THE TRANSFORMED DATA TO POSTGRES DB *******************************
# Load data to PostgreSQL
c = 0
for pg_doc in pg_data:
    c += 1
    cur_dest.execute(
        "INSERT INTO jl_amende (id, annee, montant, __v) VALUES (%s, %s, %s, %s)",
        (c, pg_doc["annee"], pg_doc["montant"], pg_doc["__v"])
    )
conn_dest.commit()

# # Close connections
mongo_client.close()
cur_dest.close()
conn_dest.close()