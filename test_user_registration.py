# test_user_registration.py
#stuff
import unittest
from user_registration import UserRegistration

class TestUserRegistration(unittest.TestCase):

    def setUp(self):
        """Reset registered users before each test."""
        UserRegistration.registered_users = set()

    def test_register_user_success(self):
        """Test that a new user can register successfully."""
        response = UserRegistration.register_user("test@example.com", "password123")
        self.assertEqual(response, {"email": "test@example.com", "status": "registered"})
        self.assertIn("test@example.com", UserRegistration.registered_users)

    def test_register_user_already_registered(self):
        """Test that registering an already registered email raises an error."""
        UserRegistration.register_user("test@example.com", "password123")
        with self.assertRaises(ValueError) as context:
            UserRegistration.register_user("test@example.com", "newpassword")
        self.assertEqual(str(context.exception), "Email is already registered.")

    def test_register_user_weak_password(self):
        """Test that a weak password raises an error."""
        with self.assertRaises(ValueError) as context:
            UserRegistration.register_user("user@example.com", "123")
        self.assertEqual(str(context.exception), "Password must be at least 6 characters long.")

    def test_get_registered_users(self):
        """Test retrieving the list of registered users."""
        UserRegistration.register_user("user1@example.com", "password1")
        UserRegistration.register_user("user2@example.com", "password2")
        self.assertEqual(UserRegistration.get_registered_users(), ["user1@example.com", "user2@example.com"])

if __name__ == "__main__":
    unittest.main()
