import argparse

import gcpresources.connector

parser = argparse.ArgumentParser()


def list_of_databases(arg):
    return arg.split(',')


# Set up required arguments this script
parser.add_argument('--host', type=str)
parser.add_argument('--user', type=str)
parser.add_argument('--password', type=str)
parser.add_argument('--databases', type=list_of_databases)

# Parse the given arguments
args = parser.parse_args()
dataBases = args.databases

for dataBase in dataBases:

    mydb = gcpresources.connector.connect(
        host=args.host,
        user=args.user,
        password=args.password,
        database=dataBase
    )
    mycursor = mydb.cursor()
    if dataBase == "order_db":
        mycursor.execute("CREATE TABLE car_orders (orderId VARCHAR(255) PRIMARY KEY, productId VARCHAR(255),"
                         " addressId VARCHAR(255), userId VARCHAR(255), orderStatus VARCHAR(255), quantity INT)")
        mycursor.close()
        print("order_db table created")

    elif dataBase == "inventory_db":
        mycursor.execute("CREATE TABLE car_inventory(productId VARCHAR(255) PRIMARY KEY,"
                         " name VARCHAR(255), price VARCHAR(255), quantity INT);")
        mycursor.close()
        print("inventory_db table created")

    elif dataBase == "payment_db":
        mycursor.execute("CREATE TABLE car_payment(paymentId VARCHAR(255) PRIMARY KEY, orderId VARCHAR(255),"
                         " paymentStatus VARCHAR(255), timeStamp DATE);")
        mycursor.close()
        print("payment_db table created")

    elif dataBase == "shipment_db":
        mycursor.execute("CREATE TABLE car_Shipment(shipmentId VARCHAR(255) PRIMARY KEY,"
                         " orderId VARCHAR(255), shipmentStatus VARCHAR(255));")
        mycursor.close()
        print("shipment_db table created")

    else:
        print("invalid database")
