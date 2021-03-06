# Banking API

## Project Description
The Banking API will manage the bank accounts of its users. It will be managed by the Bank's employees and admins. Employees and Admins count as Standard users with additional abilities.
* Employees can view all customer information, but not modify in any way.
* Admins can both view all user information, as well as directly modify it.
* Standard users should be able to register and login to see their account information.They can have either Checking or Savings accounts.
* All users must be able to update their personal information, such as username, password, first and last names, as well as email.
* Accounts owned by users must support withdrawal, deposit, and transfer.
* Transfer of funds should be allowed between accounts owned by the same user, as well as between accounts owned by different users.

## Technologies Used

* Spring Tool Suite
* Git Bash
* DBeaver
* Postman
* Amazon AWS

## Features

* You can Deposit Money
* Withdraw Money 
* Transfer money between Accounts

To-do list:
* Improve Securities between users.


## Getting Started
   * To clone the repo by going to your terminal then CD ` cd <directory> ` to where you want the file in.
   * `git clone https://github.com/210419-Appian/project-1-XDeric.git <name of the folder>`
   * You run the server with Tomcat.

## Usage

The User model keeps track of users information.
```java
public class User {
  private int userId; // primary key
  private String username; // not null, unique
  private String password; // not null
  private String firstName; // not null
  private String lastName; // not null
  private String email; // not null
  private Role role;
}
```
You may optionally consider to include a `List<Account>` field in the User model. Some tasks will be easier, and others harder. In particular, this complicates every request that would need an entire User object, such as `Register` and `Update User`, since they would need to include the accounts as well. It would be up to you to resolve this.

### **Role**
The Role model keeps track of user permissions. Can be expanded for more features later. Could be `Admin`, `Employee`, `Standard`, or `Premium`

```java
public class Role {
  private int roleId; // primary key
  private String role; // not null, unique
}
```

### **Account**
The Account model is used to represent a single account for a user
```java
public class Account {
  private int accountId; // primary key
  private double balance;  // not null
  private AccountStatus status;
  private AccountType type;
}
```

### **AccountStatus**
The AccountStatus model is used to track the status of accounts. Status possibilities are `Pending`, `Open`, or `Closed`, or `Denied`
```java
public class AccountStatus {
  private int statusId; // primary key
  private String status; // not null, unique
}
```

### **AccountType**
The AccountType model is used to track what kind of account is being created. Type possibilities are `Checking` or `Savings`
```java
public class AccountType {
  private int typeId; // primary key
  private String type; // not null, unique
}
```

# Endpoints
The below endpoints generally follow a RESTful pattern. Where the URI describes the relevant resource and the HTTP Method describes the action to perform. Path variables (e.g. `/:userId`) are used to identify specific resources as part of the URI. These are placeholders, such as for a userId. If not otherwise specified, the response status code should be `200 OK`.
## Security
  Security should be handled through session storage.
  If a user does not have permission to access a particular endpoint it should return the following:
  * **Status Code:** `401 UNAUTHORIZED`
    **Content:**
    ```json
    {
      "message": "The requested action is not permitted"
    }
    ```
    Occurs if they do not have the appropriate permissions.

## RPC Endpoints
These endpoints are not RESTful, but are included to more conveniently simulate user actions

### **Login**
* **URL:** `/login`

* **Method:** `POST`

* **Request:**
  ```json
  {
    "username": String,
    "password": String
  }
  ```

* **Response:**
  ```json
  User
  ```

* **Error Response:**
  * **Status Code:** `400 BAD REQUEST`

  ```json
  {
    "message": "Invalid Credentials"
  }
  ```

### **Logout**
* **URL:** `/logout`

* **Method:** `POST`

* **Response:**
  ```json
  {
    "message": "You have successfully logged out {username}"
  }
  ```
* **Error Response:**
  * **Status Code:** `400 BAD REQUEST`

  ```json
  {
    "message": "There was no user logged into the session"
  }
  ```

### **Register**
* **URL:** `/register`

* **Method:** `POST`

* **Allowed Roles:** `Admin`

* **Request:**
  Note: All fields must be included and the userId should be zero
  ```json
  User
  ```

* **Response:**
  Note: The userId should be updated
  * **Status Code:** `201 CREATED`
  ```json
  User
  ```

* **Error Response:**
  Note: In case username or email is already used
  * **Status Code:** `400 BAD REQUEST`
  ```json
  {
    "message": "Invalid fields"
  }
  ```

### **Withdraw**
* **URL:** `/accounts/withdraw`

* **Method:** `POST`

* **Allowed Roles:** `Admin` or if the account belongs to the current user

* **Request:**
  ```json
  {
    "accountId": int,
    "amount": double
  }
  ```

* **Response:**
  ```json
  {
    "message": "${amount} has been withdrawn from Account #{accountId}"
  }
  ```

### **Deposit**
* **URL:** `/accounts/deposit`

* **Method:** `POST`

* **Allowed Roles:** `Admin` or if the account belongs to the current user

* **Request:**
  ```json
  {
    "accountId": int,
    "amount": double
  }
  ```

* **Response:**
  ```json
  {
    "message": "${amount} has been deposited to Account #{accountId}"
  }
  ```

### **Transfer**
* **URL:** `/accounts/transfer`

* **Method:** `POST`

* **Allowed Roles:** `Admin` or if the source account belongs to the current user

* **Request:**
  ```json
  {
    "sourceAccountId": int,
    "targetAccountId": int,
    "amount": double
  }
  ```

* **Response:**
  ```json
  {
    "message": "${amount} has been transferred from Account #{sourceAccountId} to Account #{targetAccountId}"
  }
  ```

## RESTful Endpoints
These endpoints *are* RESTful, and generally provide basic CRUD operations for Employees/Admins

### **Find Users**
* **URL:** `/users`

* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin`

* **Response:**
  ```json
  [
    User
  ]
  ```

### **Find Users By Id**
* **URL:** `/users/:id`

* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin` or if the id provided matches the id of the current user

* **Response:**
  ```json
  User
  ```

### **Update User**
* **URL:** `/users`

* **Method:** `PUT`

* **Allowed Roles:** `Admin` or if the id provided matches the id of the current user

* **Request:**
  Note: All fields must be included
  ```json
  User
  ```

* **Response:**
  ```json
  User
  ```

### **Find Accounts**
* **URL:** `/accounts`

* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin`

* **Response:**
  ```json
  [
    Account
  ]
  ```

### **Find Accounts By Id**
* **URL:** `/accounts/:id`

* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin` or if the account belongs to the current user

* **Response:**
  ```json
  Account
  ```

### **Find Accounts By Status**
* **URL:** `/accounts/status/:statusId`

* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin`

* **Response:**
  ```json
  [
    Account
  ]
    ```

### **Find Accounts By User**
* **URL:** `/accounts/owner/:userId`
  For a challenge you could do this instead: `/accounts/owner/:userId?accountType=type`

* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin` or if the id provided matches the id of the current user

* **Response:**
  ```json
  [
    Account
  ]
  ```

### **Submit Account**
* **URL:** `/accounts`

* **Method:** `POST`

* **Allowed Roles:** `Employee` or `Admin` or if the account belongs to the current user

* **Request:**
  The accountId should be 0
  ```json
  Account
  ```

* **Response:**
  * **Status Code:** `201 CREATED`

  ```json
  Account
  ```


### **Update Account**
* **URL:** `/accounts`

* **Method:** `PUT`

* **Allowed Roles:** `Admin`

* **Request:**
  Note: All fields must be included
  ```json
  Account
  ```

* **Response:**
  ```json
  Account
  ```
