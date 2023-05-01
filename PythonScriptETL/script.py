import psycopg2
import pymongo
import pandas as pd  # for handling the data in a tabular format

#*********************** CONNECTION TO MONGODB *****************************
# Connect to MongoDB
mongo_client = pymongo.MongoClient("mongodb+srv://Zeyphax:zeyphax00@bd-decisions.eok3p.mongodb.net/")
mongo_db = mongo_client["mshclemi"]
mongo_collection = mongo_db["amende"]

# Extract data from MongoDB
mongo_data = mongo_collection.find()
# mongo_data = mongo_collection.find({},{ "_id": 1, "annee": 1, "montant": 1 })
# for x in mongo_data:
#   print(x)

#*********************** CONNECTION TO POSTGRES *****************************
# Connect to the source database
conn_src = psycopg2.connect(
    host="pg.adam.uvsq.fr",
    database="piratage",
    user="piratage",
    password="f0f89cee55"
)

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
cur_src = conn_src.cursor()

# Create a cursor object for the destination database
cur_dest = conn_dest.cursor()

#*********** COPY OF THE POSTGRES REMOTE DB TO A LOCALLY CREATED ONE **************
# A executer les commandes suivante sur le terminal
# Create a backup of the source database
# "pg_dump -U piratage -h pg.adam.uvsq.fr -d piratage -f piratage.dump"

# Restore the dump file to the destination database
# "psql -U user_ter -h localhost -d piratage_judilibre -f piratage.dump"

# Close the cursor objects and database connections
cur_src.close()
cur_dest.close()
conn_src.close()
conn_dest.close()

#*********** TRANSFORMING THE MONGODB DATA TO POSTGRES MODEL **************
# Transform data to PostgreSQL model
# pg_data = []
# for doc in mongo_data:
#     pg_doc = {
#         "id": doc["_id"],
#         "annee": doc["annee"],
#         "montant": doc["montant"]
#     }
#     pg_data.append(pg_doc)

#*********** LOADING THE TRANSFORMED DATA TO POSTGRES DB **************
# Load data to PostgreSQL
# for pg_doc in pg_data:
#     pg_cursor.execute(
#         "INSERT INTO mytable (id, name, age) VALUES (%s, %s, %s)",
#         (pg_doc["id"], pg_doc["name"], pg_doc["age"])
#     )
# pg_conn.commit()

# # Close connections
# pg_cursor.close()
# pg_conn.close()
mongo_client.close()