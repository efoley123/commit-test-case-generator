# user_registration.py

class UserRegistration:
    registered_users = set()  # A set to store registered users

    @staticmethod
    def register_user(email, password):
        """Registers a new user if the email is not already registered."""
        if email in UserRegistration.registered_users:
            raise ValueError("Email is already registered.")
        
        if len(password) < 6:
            raise ValueError("Password must be at least 6 characters long.")
        
        UserRegistration.registered_users.add(email)
        return {"email": email, "status": "registered"}

    @staticmethod
    def get_registered_users():
        """Returns a list of registered users."""
        return list(UserRegistration.registered_users)
