import matplotlib.pyplot as plt
def add_expense():
    print("\nAdd Expense")

    date = input("Enter date (YYYY-MM-DD): ")
    category = input("Enter category: ")
    amount = float(input("Enter amount: "))
    description = input("Enter description: ")

    # Step: open file in append mode
    file = open("expenses.csv", "a")

    # Step: create line
    line = date + "," + category + "," + str(amount) + "," + description

    # Step: write into file
    file.write(line + "\n")

    # Step: close file
    file.close()

    print("Expense saved successfully!")


def view_expenses():
    print("\nAll Expenses:")

    file = open("expenses.csv", "r")

    for line in file:
        # Step: split data
        data = line.strip().split(",")

        # Print in readable format
        print("Date:", data[0], "| Category:", data[1], "| Amount:", data[2], "| Desc:", data[3])

    file.close()


def monthly_summary():
    print("\nMonthly Summary")

    month = input("Enter month (YYYY-MM): ")

    total = 0

    file = open("expenses.csv", "r")

    for line in file:
        data = line.strip().split(",")

        # Step: check month
        if data[0].startswith(month):
            total = total + float(data[2])

    file.close()

    print("Total expense for", month, "is:", total)


def category_analysis():
    print("\nCategory Analysis")

    category_total = {}

    file = open("expenses.csv", "r")

    for line in file:
        data = line.strip().split(",")

        category = data[1]
        amount = float(data[2])

        # Step: add to dictionary
        if category in category_total:
            category_total[category] += amount
        else:
            category_total[category] = amount
    
    labels = list(category_total.keys())
    values = list(category_total.values())

    plt.pie(values, labels=labels, autopct='%1.1f%%')
    plt.title("Expense Distribution by Category")

    plt.show()

    file.close()


    print("\nSpending by Category:")
    for cat in category_total:
        print(cat, ":", category_total[cat])

    # Find highest spending category
    max_category = max(category_total, key=category_total.get)
    print("Highest spending category:", max_category)


# Main Program
while True:
    print("\n===== Expense Tracker =====")
    print("1. Add Expense")
    print("2. View Expenses")
    print("3. Monthly Summary")
    print("4. Category Analysis")
    print("5. Exit")

    choice = int(input("Enter your choice: "))

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