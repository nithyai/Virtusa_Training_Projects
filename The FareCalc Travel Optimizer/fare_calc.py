def calculate_fare(km, vehicle_type, hour):

    rates = {
        "Economy": 10,
        "Premium": 18,
        "SUV": 25
    }

    if vehicle_type not in rates:
        return None

    fare = km * rates[vehicle_type]

    if 17 <= hour <= 20:
        fare = fare * 1.5

    return fare


km = float(input("Enter distance (km): "))
vehicle_type = input("Enter vehicle type (Economy/Premium/SUV): ")
hour = int(input("Enter hour (0-23): "))

result = calculate_fare(km, vehicle_type, hour)

if result is None:
    print("Service Not Available")
else:
    print("----- Ride Receipt -----")
    print("Distance:", km)
    print("Vehicle:", vehicle_type)
    print("Hour:", hour)
    print("Total Fare:", result)