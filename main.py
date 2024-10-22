# main.py
from math_utils import add, subtract, multiply, divide

def main():
    # Example usage of the math functions
    print("Add: ", add(2, 3))            # Output: Add: 5
    print("Subtract: ", subtract(5, 2))  # Output: Subtract: 3
    print("Multiply: ", multiply(4, 3))  # Output: Multiply: 12
    try:
        print("Divide: ", divide(10, 2))  # Output: Divide: 5.0
        print("Divide by zero: ", divide(10, 0))  # This will raise an error
    except ValueError as e:
        print("Error: ", e)

if __name__ == "__main__":
    main()
