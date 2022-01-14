# projectfactory_java
Working with: OTM, MTM, Login/Registration
Also: Using choose/when/otherwise for functionality


# Objectives:
- Build a functioning Registration/Login page. 
  ->User must provide a unique email address
  ->User password/password confirmation must match
  ->Working login/logout functionality
  ->If user is not logged-in or registered, they cannot proceed past that page, even if they manually type it in the url. 
  
- Build a one-to-many relationship to display all created objects
  ->Once a user has logged in, they can create an object to be displayed. 
  ->Only the user who created the object may edit or delete it. 
  
- Build a many-to-many 
  ->Any user logged in, may like any object another user created.
  ->Any user logged in, may unlike any object another user created.
  ->Keep a count of the number of likes an object has.
  ->Display the names of the user's who have liked an object.
  ->This functionality needs to be displayed and updated in real time.
