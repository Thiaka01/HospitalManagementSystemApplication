Hospital Management System
A robust, Spring Boot-based enterprise solution designed to digitize healthcare workflows, manage real-time scheduling, and secure sensitive patient data through role-based access control.

Core Features
1. Role-Based Access Control (RBAC)
Customized permissions for four distinct user roles:

Admins: Full system oversight, inventory management, and account auditing.

Doctors: Patient consultation logs, history tracking, and prescription management.

Receptionists: Patient registration and front-desk operations.

Lab Technicians: Laboratory test management and digital result reporting.

2. Intelligent Appointment System
Real-time Scheduling: Live updates for booking and availability.

Conflict Prevention: Automated logic to prevent double-booking of doctors or rooms.

Rescheduling: Simple interface for rescheduling to improve patient convenience.

3. Clinic Digitization
Electronic Health Records (EHR): Secure storage of patient medical history.

Lab and Pharmacy: Digital integration of test requests and billing.

Automated Billing: Instant invoice generation based on consultations and tests.

4. Administrative and Security Tools
Inventory Management: Track medical supplies and equipment levels.

Operational Reports: Generate data-driven insights on hospital performance.

Data Security: Enhanced encryption and strict unauthorized access prevention.

Technical Stack
Backend: Java, Spring Boot

Security: Spring Security

Database: MySQL

ORM: Hibernate

Template Engine: Thymeleaf

How to Run the Application
Prerequisites
Java Development Kit (JDK) installed.

MySQL Server installed and running.

IDE (e.g., IntelliJ IDEA or Eclipse) installed.

Step 1: Database Setup
Open your MySQL terminal.

Create the target database:

SQL
CREATE DATABASE hospital_db;
Step 2: Configure Environment
Open src/main/resources/application.properties and update your database credentials:

Properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

Step 3: Build and Run
Open the project folder in your IDE (e.g., IntelliJ IDEA).

Allow the IDE to import the project dependencies (Maven/Gradle).

Locate the main class file: HospitalManagementSystemApplication.java.

Click the Run button (usually a green play icon) next to the main method.

Step 4: Access the Application
Open your web browser and go to:
http://localhost:8080

Troubleshooting Port 8080
If you encounter a "Port already in use" error on Windows:

Open Command Prompt as Administrator.

Run the following command to clear hanging Java processes:

taskkill /F /IM java.exe
Restart the application in your IDE.
