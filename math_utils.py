# math_utils.py

def add(a, b):
    """Returns the sum of two numbers."""
    return a + b

def subtract(a, b):
    """Returns the difference between two numbers."""
    return a - b

def multiply(a, b):
    """Returns the product of two numbers."""
    return a * b

def addOne(a):
    """Returns the number plus one."""
    return a+1

def addTwo(a):
    """Returns the number plus two."""
    return a+2

def addThree(a):
    """Returns the number plus three."""
    return a+3

def addfour(a):
    """Returns the number plus four."""
    return a+4


def addfive(a):
    """Returns the number plus five."""
    return a+5

def divide(a, b):
    """Returns the quotient of two numbers.

    Raises:
        ValueError: If b is zero (cannot divide by zero).
    """
    if b == 0:
        raise ValueError("Cannot divide by zero.")
    return a / b
