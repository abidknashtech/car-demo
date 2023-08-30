import argparse

import mysql.connector

parser = argparse.ArgumentParser()

# Set up required arguments this script
parser.add_argument('host', type=str)
parser.add_argument('user', type=str)
parser.add_argument('password', type=str)
parser.add_argument('database', type=str)
parser.add_argument('createDdl', type=str)

# Parse the given arguments
args = parser.parse_args()
mydb = mysql.connector.connect(
    host=args.host,
    user=args.user,
    password=args.password,
    database=args.database
)

mycursor = mydb.cursor()
mycursor.execute(args.createDdl)

