### Wholesaler Order Management API - README

---

Welcome to the Wholesaler Order Management API! This API is designed to facilitate interactions between three main roles: **admin (wholesaler)**, **shop owner**, and **salesman**. The primary functionality is for shop owners to order products from the wholesaler.

### Roles and Responsibilities:

1. **Admin (Wholesaler)**:
   - Manages system-wide configurations, including user roles and permissions.
   - Can view and manage all users, orders, and products.
   - Oversees the approval and management of shop registrations.
   - Manages the inventory of products available for ordering.

2. **Shop Owner**:
   - Places orders for products from the wholesaler.
   - Manages their shop’s order history and current orders.
   - Updates their shop’s profile information and communicates with the wholesaler.

3. **Salesman**:
   - Assists shop owners in placing and managing orders.
   - Can view and manage orders on behalf of the shop owner.
   - Provides support to shop owners regarding product details and order issues.

### Components of the API:

- **User Controller**: Handles user-specific operations such as user registration, login, profile management, and order placement.
  
- **Order Controller**: Manages the creation, updating, and tracking of orders from shop owners to the wholesaler.
  
- **Product Controller**: Handles CRUD operations related to products available for order.
  
- **Cart Service**: Manages operations related to the shopping cart for shop owners.
  
- **Order Service**: Handles order processing, including order placement and status updates.
  
- **Product Service**: Manages product-related functionalities such as retrieval and modification.

- **Response**: Defines the structure of API responses to ensure consistency and clarity.
  
- **Service Interface and Implementation**:
  - **UserService**: Provides methods for user-related operations.
  - **OrderService**: Handles order processing and management.
  - **ProductService**: Manages product-related functionalities.

- **Repository**: Interfaces with the database to perform CRUD operations. Includes:
  - **UserRepository**
  - **OrderRepository**
  - **ProductRepository**
  - **CartRepository**: Manages data related to shopping carts.

### Model Structure:

- **User Model**: Represents basic user information and roles.
  
- **Order Model**: Contains details about an order, including products, quantities, status, and user/wholesaler associations.
  
- **Product Model**: Defines attributes for each product available for order, including name, description, price, and wholesaler association.

### Technologies Used:

- **Framework**: Spring Boot
- **Database**: MySQL
- **API Documentation**: Swagger

### Getting Started:

1. **Installation**:
   - Clone the repository.
   - Install dependencies using Maven or Gradle.

2. **Configuration**:
   - Set up database connections in `application.properties`.
   - Configure role-based access control if not already set by default.

3. **Running the API**:
   - Start the server using `mvn spring-boot:run`.
   - Ensure all services are up and running.

4. **API Documentation**:
   - Access API documentation through Swagger UI at `/swagger-ui.html`.
   - Explore available endpoints and their functionalities.

### Security:

- Implement appropriate security measures:
  - **Authentication**: JWT tokens, OAuth, etc.
  - **Authorization**: Role-based access control (RBAC).
  - **Data Protection**: Encryption of sensitive data (passwords, order details).

### Future Enhancements:

- **Real-time Order Tracking**: Implement live updates for order status.
- **Payment Integration**: Include payment gateway integration for order processing.
- **Analytics and Reporting**: Provide detailed reports and analytics for shop owners and admins.

### Contributors:

- Sameer Randive

### Feedback and Support:

For any questions, feedback, or issues, please contact on sameerrandive558@gmail.com.

---

Thank you for using the Wholesaler Order Management API. Happy coding and successful ordering! 📦🚚
