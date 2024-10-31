import pytest
from unittest.mock import patch
from your_module import add, power, subtract, multiply, divide

# Test add function
def test_add_positive_numbers():
    assert add(1, 2) == 3

def test_add_negative_numbers():
    assert add(-1, -2) == -3

def test_add_zero():
    assert add(0, 0) == 0

# Test power function
def test_power_positive():
    assert power(2, 3) == 8

def test_power_zero_exponent():
    assert power(2, 0) == 1

def test_power_negative_exponent():
    assert power(2, -1) == 0.5

# Test subtract function
def test_subtract_positive_numbers():
    assert subtract(5, 3) == 2

def test_subtract_negative_numbers():
    assert subtract(-1, -2) == 1

def test_subtract_zero():
    assert subtract(0, 0) == 0

# Test multiply function
def test_multiply_positive_numbers():
    assert multiply(3, 4) == 12

def test_multiply_by_zero():
    assert multiply(5, 0) == 0

def test_multiply_negative_by_positive():
    assert multiply(-3, 4) == -12

# Test divide function
def test_divide_positive_numbers():
    assert divide(6, 3) == 2

def test_divide_by_zero():
    assert divide(5, 0) == "Error: Division by zero!"

def test_divide_negative_by_positive():
    assert divide(-6, 3) == -2

def test_divide_by_negative():
    assert divide(6, -3) == -2

# Test calculator UI interactions using mocking
@patch('builtins.input')
@patch('builtins.print')
def test_calculator_exit(mock_print, mock_input):
    mock_input.side_effect = ['exit']
    with pytest.raises(SystemExit):
        calculator()
    mock_print.assert_called_with("Exiting the calculator. Goodbye!")

@patch('builtins.input')
@patch('builtins.print')
def test_calculator_addition(mock_print, mock_input):
    mock_input.side_effect = ['1', '5', '3', 'exit']
    calculator()
    mock_print.assert_any_call("5.0 + 3.0 = 8.0")

@patch('builtins.input')
@patch('builtins.print')
def test_calculator_invalid_input(mock_print, mock_input):
    mock_input.side_effect = ['5', 'exit']  # 5 is an invalid choice
    calculator()
    mock_print.assert_any_call("Invalid input. Please enter a number from 1 to 4.")