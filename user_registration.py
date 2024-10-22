import re

class UserRegistration:
    registered_users = set()  # A set to store registered users

    @staticmethod
    def register_user(email, password):
        """Registers a new user if the email is not already registered."""
        if email in UserRegistration.registered_users:
            raise ValueError("Email is already registered.")
        
        # Validate password
        if not UserRegistration.is_valid_password(password):
            raise ValueError("Password must be at least 6 characters long, contain at least one digit, and one special character.")

        UserRegistration.registered_users.add(email)
        return {"email": email, "status": "registered"}

    @staticmethod
    def unregister_user(email):
        """Unregisters a user if the email is registered."""
        if email not in UserRegistration.registered_users:
            raise ValueError("Email is not registered.")
        
        UserRegistration.registered_users.remove(email)
        return {"email": email, "status": "unregistered"}

    @staticmethod
    def is_valid_password(password):
        """Validates the password strength."""
        # Check if the password meets the criteria
        if (len(password) < 6 or
                not re.search(r"\d", password) or  # At least one digit
                not re.search(r"[!@#$%^&*(),.?\":{}|<>]", password)):  # At least one special character
            return False
        return True

    @staticmethod
    def get_registered_users():
        """Returns a list of registered users."""
        return list(UserRegistration.registered_users)
