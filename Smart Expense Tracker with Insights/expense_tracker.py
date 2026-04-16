import matplotlib.pyplot as plt
import os

FILE_NAME = "expenses.csv"


def add_expense():
    print("\nAdd Expense")

    date = input("Enter date (YYYY-MM-DD): ")

    # Normalize category (first letter capital)
    category = input("Enter category: ").capitalize()

    # Validate amount
    try:
        amount = float(input("Enter amount: "))
    except:
        print("Invalid amount!")
        return

    # Remove commas from description
    description = input("Enter description: ").replace(",", " ")

    file = open(FILE_NAME, "a")
    line = date + "," + category + "," + str(amount) + "," + description
    file.write(line + "\n")
    file.close()

    print("Expense saved successfully!")


def view_expenses():
    print("\nAll Expenses:")

    if not os.path.exists(FILE_NAME):
        print("No data found!")
        return

    file = open(FILE_NAME, "r")

    lines = file.readlines()
    file.close()

    # Skip header row
    for line in lines[1:]:
        data = line.strip().split(",")

        if len(data) == 4:
            print("----------------------------------")
            print("Date       :", data[0])
            print("Category   :", data[1])
            print("Amount     :", data[2])
            print("Description:", data[3])


def monthly_summary():
    print("\nMonthly Summary")

    month = input("Enter month (YYYY-MM): ")

    total = 0

    if not os.path.exists(FILE_NAME):
        print("No data found!")
        return

    file = open(FILE_NAME, "r")

    lines = file.readlines()
    file.close()

    # Skip header row
    for line in lines[1:]:
        data = line.strip().split(",")

        if len(data) == 4 and data[0].startswith(month):
            total += float(data[2])

    print("Total expense for", month, "is:", total)


def category_analysis():
    print("\nCategory Analysis")

    category_total = {}

    if not os.path.exists(FILE_NAME):
        print("No data found!")
        return

    file = open(FILE_NAME, "r")

    lines = file.readlines()
    file.close()

    # Skip header row
    for line in lines[1:]:
        data = line.strip().split(",")

        if len(data) == 4:
            category = data[1]
            amount = float(data[2])

            if category in category_total:
                category_total[category] += amount
            else:
                category_total[category] = amount

    if not category_total:
        print("No data to analyze!")
        return

    # Print category totals
    print("\nSpending by Category:")
    for cat in category_total:
        print(cat, ":", category_total[cat])

    # Highest spending category
    max_category = max(category_total, key=category_total.get)
    print("Highest spending category:", max_category)

    # Pie chart
    labels = list(category_total.keys())
    values = list(category_total.values())

    plt.figure()
    plt.pie(values, labels=labels, autopct='%1.1f%%')
    plt.title("Expense Distribution by Category")
    plt.show()


# Main Program
while True:
    print("\n===== Expense Tracker =====")
    print("1. Add Expense")
    print("2. View Expenses")
    print("3. Monthly Summary")
    print("4. Category Analysis")
    print("5. Exit")

    try:
        choice = int(input("Enter your choice: "))
    except:
        print("Invalid input!")
        continue

    if choice == 1:
        add_expense()
    elif choice == 2:
        view_expenses()
    elif choice == 3:
        monthly_summary()
    elif choice == 4:
        category_analysis()
    elif choice == 5:
        print("Exiting program...")
        break
    else:
        print("Invalid choice")