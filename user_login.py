# user_login.py

class UserLogin:
    registered_users = set()  # This should ideally be managed with a proper database

    @staticmethod
    def login_user(email, password):
        """Logs in a user if the email is registered and password is correct."""
        # For this example, we'll assume all passwords are "password123" for simplicity
        if email not in UserLogin.registered_users:
            raise ValueError("Email is not registered.")
        
        if password != "password123":
            raise ValueError("Incorrect password.")

        return {"email": email, "status": "logged in"}

    @staticmethod
    def register_user(email):
        """Registers a new user for demonstration purposes."""
        UserLogin.registered_users.add(email)
        return {"email": email, "status": "registered"}

    @staticmethod
    def get_registered_users():
        """Returns a list of registered users."""
        return list(UserLogin.registered_users)

# Example usage
if __name__ == "__main__":
    user_email = "test@example.com"
    
    # Register a user
    print(UserLogin.register_user(user_email))
    
    # Attempt to log in the registered user
    try:
        print(UserLogin.login_user(user_email, "password123"))
    except ValueError as e:
        print(e)

    # Attempt to log in with incorrect password
    try:
        print(UserLogin.login_user(user_email, "wrongpassword"))
    except ValueError as e:
        print(e)
