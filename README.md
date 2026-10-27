# Kongu Engineering College (Autonomous) - Regulation 2024
## Student Academic & Continuous Evaluation System using Java JDBC

[![Java](https://img.shields.io/badge/Java-JDK%2021-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0%2B-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![JDBC](https://img.shields.io/badge/JDBC-Type%204%20Connector-007396?style=for-the-badge&logo=java&logoColor=white)](https://dev.mysql.com/downloads/connector/j/)
[![Regulation](https://img.shields.io/badge/KEC-Regulation%202024-darkgreen?style=for-the-badge)](https://kongu.ac.in)
[![Status](https://img.shields.io/badge/Status-Verified%20Pass-brightgreen?style=for-the-badge)]()

An enterprise-grade, database-driven academic management system developed in **Java** utilizing **JDBC (Type-4 MySQL Connector)**. Built strictly in compliance with **Kongu Engineering College (Autonomous) - Regulation 2024**, this application manages student admissions, maintains dynamic course catalogs (`THEORY`, `PRACTICAL`, `EMBEDDED`), tracks continuous attendance, implements 3-phase incremental marks entry ("Then and Now" academic lifecycle), enforces strict evaluation weightage engines, and generates formal institutional grade sheets, defaulter registers, and department performance analytics.

---

### 👨‍💻 Student Developer & Project Details
* **Author / Candidate**: Vetrivel Murugan
* **Roll Number**: 25ITR169
* **Department**: Information Technology
* **Institution**: Kongu Engineering College (Autonomous), Perundurai, Erode - 638 060
* **Course / Subject**: Object-Oriented Programming with Java / Database Driven Applications
* **Repository**: [vetrivelmurugan-svg/JAVA-PROJECT](https://github.com/vetrivelmurugan-svg/JAVA-PROJECT)

---

## Table of Contents
1. [Key Features & Highlights](#-key-features--highlights)
2. [System Architecture](#-system-architecture)
3. [KEC Regulation 2024 Evaluation Engine](#-kec-regulation-2024-evaluation-engine)
   - [Course Types & Weightage Distribution](#course-types--weightage-distribution)
   - [Continuous Internal Assessment (CIA) Formula](#continuous-internal-assessment-cia-formula)
   - [End Semester Examination (ESE) Passing Thresholds](#end-semester-examination-ese-passing-thresholds)
   - [Attendance Rules & Condonation Gate](#attendance-rules--condonation-gate)
   - [10-Point Letter Grading Scale](#10-point-letter-grading-scale)
   - [SGPA & Equivalent Percentage Formulas](#sgpa--equivalent-percentage-formulas)
4. [Incremental Marks Entry ("Then and Now" Lifecycle)](#-incremental-marks-entry-then-and-now-lifecycle)
5. [Database Schema & ER Model](#-database-schema--er-model)
6. [Visual Execution Proofs & Screenshots](#-visual-execution-proofs--screenshots)
7. [Sample Reports & Console Previews](#-sample-reports--console-previews)
8. [Project Directory Structure](#-project-directory-structure)
9. [Getting Started & Installation](#-getting-started--installation)
   - [Prerequisites](#prerequisites)
   - [1. Database Configuration & Schema Import](#1-database-configuration--schema-import)
   - [2. Compile the Project](#2-compile-the-project)
   - [3. Run Interactive Console Application](#3-run-interactive-console-application)
   - [4. Run Automated Demonstration Showcase](#4-run-automated-demonstration-showcase)
10. [Artifacts & Media Links](#-artifacts--media-links)

---

## 🌟 Key Features & Highlights

- **Robust Relational Schema**: 4 interconnected MySQL tables with foreign key constraints, unique indexing, and cascade delete rules ensuring strict referential integrity.
- **Three-Tier Architecture (DAO Pattern)**: Clear separation of concerns separating domain entities ([`Student`](Student.java), [`Course`](Course.java), [`Enrollment`](Enrollment.java), [`AssessmentMarks`](AssessmentMarks.java)), business evaluation logic ([`CourseEvaluationResult`](CourseEvaluationResult.java)), and persistence layers ([`StudentDAO`](StudentDAO.java), [`AcademicDAO`](AcademicDAO.java)).
- **Course Category Specialization**: Built-in specialized weightage rules for **Theory** (40/60), **Practical** (60/40), and **Embedded** (50/50) courses.
- **Automated Attendance Gatekeeper**: Prevents attendance defaulters ($<65\%$ or $65\%-74.9\%$ without medical condonation) from registering for End Semester Examinations and awards them `SA` grade automatically.
- **Phased Incremental Evaluation**: Mirrors real-world university workflows across Mid-Semester (CIA 1 & 2), Semester End (Assignments / Lab records), and Post-Semester (ESE / Absentee processing).
- **Formal Academic Report Generation**:
  - Individual **Student Semester Grade Sheets** with course-wise breakdown, earned credits, SGPA, and equivalent percentage.
  - Institutional **Attendance Defaulter Report** flagging all barred students (`SA`).
  - Departmental **Class Performance & Result Summary** detailing pass/fail counts, class average SGPA, grade distribution histograms, and rank lists.
- **Dual Operating Modes**:
  1. **Interactive Console CLI**: Full-featured menu for administrative operations.
  2. **Automated Verification Showcase (`--demo`)**: End-to-end automated verification run with zero manual intervention.

---

## 🏗 System Architecture

```
                       ┌───────────────────────────────────────────────┐
                       │                   MainApp                     │
                       │    (Interactive CLI & Automated Demo Mode)    │
                       └───────────────────────┬───────────────────────┘
                                               │
                                 Invokes Service & Business Logic
                                               │
                       ┌───────────────────────▼───────────────────────┐
                       │       CourseEvaluationResult & Entities       │
                       │    (KEC R2024 Rule Engine, SGPA, Attendance)  │
                       └───────────────────────┬───────────────────────┘
                                               │
                                 Calls DAO Persistence Layer
                                               │
                    ┌──────────────────────────┴──────────────────────────┐
                    ▼                                                     ▼
       ┌────────────────────────┐                            ┌────────────────────────┐
       │       StudentDAO       │                            │      AcademicDAO       │
       │ (Students, Catalog,    │                            │ (Phased Marks Entry,   │
       │  Enrollments & Attd)   │                            │  SGPA & Class Summary) │
       └────────────┬───────────┘                            └────────────┬───────────┘
                    │                                                     │
                    └──────────────────────────┬──────────────────────────┘
                                               │
                                Uses JDBC Driver Connection
                                               │
                                 ┌─────────────▼─────────────┐
                                 │     DBConnection.java     │
                                 │  (MySQL Connector/J 8.4)  │
                                 └─────────────┬─────────────┘
                                               │
                                 ┌─────────────▼─────────────┐
                                 │   MySQL Database Engine   │
                                 │     (kec_schema.sql)      │
                                 └───────────────────────────┘
```

---

## 📐 KEC Regulation 2024 Evaluation Engine

### Course Types & Weightage Distribution

| Course Type | CIA Weightage | ESE Weightage | ESE Passing Threshold | Overall Pass Minimum |
| :--- | :---: | :---: | :---: | :---: |
| **THEORY** | **40%** | **60%** | **45% (27.0 / 60)** | **50.0 Marks** |
| **PRACTICAL** | **60%** | **40%** | **50% (20.0 / 40)** | **50.0 Marks** |
| **EMBEDDED** | **50%** | **50%** | **45% (22.5 / 50)** | **50.0 Marks** |

### Continuous Internal Assessment (CIA) Formula
Total CIA is computed on a 100-point normalized scale from three components:
$$\text{CIA}_{100} = (\text{CIA}_1 \times 0.30) + (\text{CIA}_2 \times 0.30) + (\text{Assignment / Lab} \times 0.40)$$

The final scaled course marks are determined by:
$$\text{Total Marks} = (\text{CIA}_{100} \times W_{\text{CIA}}) + (\text{ESE} \times W_{\text{ESE}})$$

### End Semester Examination (ESE) Passing Thresholds
Even if the total combined marks reach 50, a student **must satisfy the individual ESE threshold**:
- **Theory Courses**: Minimum **45%** in ESE (i.e. $\ge 27.0$ out of 60 scaled marks).
- **Practical Courses**: Minimum **50%** in ESE (i.e. $\ge 20.0$ out of 40 scaled marks).
- **Embedded Courses**: Minimum **45%** in ESE (i.e. $\ge 22.5$ out of 50 scaled marks).
*Failure to secure this threshold awards the student a **`RA` (Re-Appearance)** grade regardless of high internal marks.*

### Attendance Rules & Condonation Gate
$$\text{Attendance Percentage} = \left(\frac{\text{Classes Attended}}{\text{Classes Held}}\right) \times 100$$

```
   Attendance >= 75%  ───► ELIGIBLE for End Semester Exam
   65% <= Attd < 75%  ───► CONDITIONAL (Requires Approved Medical Condonation)
   Attendance < 65%   ───► BARRED from Exam ───► Awarded SA Grade (0 Grade Points)
```

### 10-Point Letter Grading Scale

| Scaled Total Marks | Letter Grade | Grade Point (GP) | Performance Standing |
| :---: | :---: | :---: | :--- |
| **91 – 100** | **O** | **10** | Outstanding |
| **81 – 90** | **A+** | **9** | Excellent |
| **71 – 80** | **A** | **8** | Very Good |
| **61 – 70** | **B+** | **7** | Good |
| **50 – 60** | **B** | **6** | Above Average |
| **< 50 or ESE Threshold Fail** | **RA** | **0** | Re-Appearance / Arrear |
| **Shortage of Attendance (<65%)**| **SA** | **0** | Barred from Exam |
| **Absent in ESE** | **AB** | **0** | Absent |

### SGPA & Equivalent Percentage Formulas

$$\text{SGPA} = \frac{\sum (\text{Course Credits} \times \text{Grade Points Earned})}{\sum \text{Total Enrolled Credits}}$$

$$\text{Equivalent Percentage} = \text{SGPA} \times 10$$

---

## 🔄 Incremental Marks Entry ("Then and Now" Lifecycle)

The system accurately simulates the collegiate academic semester progression:

1. **Phase 1: Mid-Semester Assessment**
   - Faculty enters CIA Test 1 and CIA Test 2 marks (each out of 100).
   - Saved incrementally without requiring subsequent phase data.
2. **Phase 2: End-of-Semester CIA Consolidation**
   - Faculty enters Assignment / Quiz / Tutorial / Lab assessment marks.
   - Computes weighted $\text{CIA}_{100}$ and scaled CIA mark.
3. **Phase 3: Post-Semester End Semester Evaluation**
   - Before accepting ESE marks, the engine queries the student's attendance and condonation status.
   - If attendance is $<65\%$ or non-condoned, **entry is blocked with an `ATTENDANCE VIOLATION` error**, and grade `SA` is committed.
   - Supports marking a student as `Absent` (`AB`), setting ESE to 0 with zero grade points.

---

## 🗄 Database Schema & ER Model

```mermaid
erDiagram
    STUDENTS ||--o{ ENROLLMENTS : "registers in"
    COURSES ||--o{ ENROLLMENTS : "catalog includes"
    ENROLLMENTS ||--|| ASSESSMENT_MARKS : "tracks"

    STUDENTS {
        varchar roll_number PK
        varchar name
        varchar department
        int current_semester
        timestamp created_at
    }

    COURSES {
        varchar course_code PK
        varchar title
        decimal credits
        enum course_type "THEORY, PRACTICAL, EMBEDDED"
        int semester
        timestamp created_at
    }

    ENROLLMENTS {
        int enrollment_id PK
        varchar roll_number FK
        varchar course_code FK
        int semester
        int classes_held
        int classes_attended
        boolean medical_condonation
        timestamp created_at
    }

    ASSESSMENT_MARKS {
        int mark_id PK
        int enrollment_id FK, UK
        decimal cia1_marks
        decimal cia2_marks
        decimal assignment_marks
        decimal ese_marks
        boolean is_absent_ese
        timestamp updated_at
    }
```

### Table Dictionary
- **`students`**: Master record of students with roll number (Primary Key), name, department, and current semester.
- **`courses`**: Academic catalog containing course code, title, credits (e.g. 1.5, 3.0, 4.0), type (`THEORY`, `PRACTICAL`, `EMBEDDED`), and semester.
- **`enrollments`**: Relates students to courses each semester; tracks `classes_held`, `classes_attended`, and `medical_condonation` flag.
- **`assessment_marks`**: One-to-one record per enrollment maintaining phased marks (`cia1_marks`, `cia2_marks`, `assignment_marks`, `ese_marks`, `is_absent_ese`).

---

## 📸 Visual Execution Proofs & Screenshots

### Proof 1: Student Registration & Course Catalog Setup
*Demonstrates registering a new student, adding course catalog items, and enrolling the student with attendance tracking.*
![Proof 1 - Add Student & Course](docs/screenshots/screenshot_1_add_student_course.png)

---

### Proof 2: Incremental Marks Entry ("Then and Now" Lifecycle)
*Demonstrates Phase 1 (CIA 1 & 2), Phase 2 (Assignments), and Phase 3 (ESE), including engine-level blocking of an attendance defaulter.*
![Proof 2 - Incremental Marks Entry](docs/screenshots/screenshot_2_incremental_marks_entry.png)

---

### Proof 3: Generated Student Semester Mark Sheet with SGPA
*Demonstrates the formal KEC R2024 Semester Grade Sheet for Roll Number `24ITR001` with SGPA 9.65 and 96.52% percentage.*
![Proof 3 - Grade Sheet & SGPA](docs/screenshots/screenshot_3_grade_sheet_sgpa.png)

---

### Proof 4: Attendance Defaulter Report (Grade SA)
*Demonstrates the automated screening of defaulters ($<65\%$ attendance or non-condoned shortage) awarded grade `SA`.*
![Proof 4 - Attendance Defaulter Report](docs/screenshots/screenshot_4_attendance_defaulters.png)

---

### Proof 5: Class Performance & Department Result Summary
*Demonstrates department analytics, pass percentage, average SGPA, grade distribution histograms, and merit rank list.*
![Proof 5 - Class Performance Summary](docs/screenshots/screenshot_5_class_performance_summary.png)

---

## 🖥 Sample Reports & Console Previews

### 1. Student Semester Grade Sheet (ASCII Format)
```text
========================================================================================================================
                              KONGU ENGINEERING COLLEGE (AUTONOMOUS), PERUNDURAI
                                  STUDENT SEMESTER GRADE SHEET - REGULATION 2024
========================================================================================================================
  Roll Number : 24ITR001              Student Name : Aadhavan K                    
  Department  : IT                    Semester     : 3
------------------------------------------------------------------------------------------------------------------------
Code      | Course Title                     | Type      | Cred | Attd%  | CIA1  | CIA2  | Asgn  | CIA-Sc | ESE   | ESE-Sc | Total | Grd  | GP | Status      
------------------------------------------------------------------------------------------------------------------------
24ITI31   | Digital Principles and System D  | EMBEDDED  |  4.0 |  91.7% |    90 |    86 |    95 |   45.4 |    88 |   44.0 |  89.4 | A+   |  9 | PASS        
24ITL31   | Data Structures & Java Laborato  | PRACTICAL |  1.5 | 100.0% |    96 |    94 |    98 |   57.7 |    95 |   38.0 |  95.7 | O    | 10 | PASS        
24ITT31   | Data Structures and Algorithms   | THEORY    |  3.0 |  93.3% |    95 |    92 |    98 |   38.1 |    94 |   56.4 |  94.5 | O    | 10 | PASS        
24ITT32   | Object Oriented Programming wit  | THEORY    |  3.0 |  96.7% |    88 |    90 |    92 |   36.1 |    91 |   54.6 |  90.7 | O    | 10 | PASS        
------------------------------------------------------------------------------------------------------------------------
  Total Enrolled Credits : 11.5                Total Credits Earned    : 11.5  
  Cumulative Grade Points: 111.00              Semester SGPA           : 9.65 / 10.00
  Equivalent Percentage  : 96.52 %             Overall Academic Result : PASS
========================================================================================================================
```

### 2. Attendance Defaulter Report (Grade SA)
```text
========================================================================================================
                             KONGU ENGINEERING COLLEGE (AUTONOMOUS)
                         ATTENDANCE DEFAULTER REPORT (BARRED - GRADE: SA)
                                  SEMESTER: 3 | DEPARTMENT: ALL DEPARTMENTS
========================================================================================================
Roll No    | Student Name           | Dept   | Course    | Held / Attd  | Attd %   | Condoned   | Awarded Grade
--------------------------------------------------------------------------------------------------------
24CSR003   | Jeevitha K             | CS     | 24CST31   |   50 / 28     |   56.0% | NO         | SA (BARRED) 
24ITR004   | Dinesh Kumar P         | IT     | 24ITT31   |   60 / 33     |   55.0% | NO         | SA (BARRED) 
24ITR004   | Dinesh Kumar P         | IT     | 24ITT32   |   60 / 32     |   53.3% | NO         | SA (BARRED) 
24ITR005   | Elango R               | IT     | 24ITT31   |   60 / 41     |   68.3% | NO         | SA (BARRED) 
--------------------------------------------------------------------------------------------------------
  Total Defaulter Cases Flagged: 4  |  Policy: Shortage <65% or Unapproved Condonation = SA Grade
========================================================================================================
```

---

## 📁 Project Directory Structure

```
.
├── mysql-connector-j-8.4.0.jar         # MySQL Type-4 JDBC Connector
├── kec_schema.sql                      # DDL & Seed Data Script
├── DBConnection.java                   # JDBC Connection Manager
├── CourseType.java                     # Enum for Theory, Practical, Embedded weights
├── Student.java                        # Student Domain Model
├── Course.java                         # Course Catalog Domain Model
├── Enrollment.java                     # Enrollment & Attendance Model
├── AssessmentMarks.java                # Phased Incremental Marks Model
├── CourseEvaluationResult.java         # Regulation 2024 Evaluation Engine
├── StudentMarkSheet.java               # Mark Sheet, SGPA & Percentage Model
├── StudentDAO.java                     # Student, Course & Enrollment DAO
├── AcademicDAO.java                    # Phased Entry, SGPA & Analytics DAO
├── MainApp.java                        # Interactive CLI & Automated Showcase
├── compile.bat / compile.ps1           # Build Scripts (Windows CMD / PowerShell)
├── run.bat / run.ps1                   # Interactive Application Launchers
├── run_demo.bat                        # One-Click Automated Verification
├── render_screenshots.ps1              # High-Resolution Screenshot Renderer
├── EXECUTION_DOCUMENTATION.md          # Comprehensive Execution & Verification Report
├── JAVA_ASSIGNMENT-VETRI_VEL_MURUGAN_25ITR169.pdf  # Submitted Assignment Documentation
├── Video Project(1).mp4                # Video Demonstration Recording
└── docs/
    └── screenshots/                    # High-Resolution Screenshot Assets
        ├── screenshot_1_add_student_course.png
        ├── screenshot_2_incremental_marks_entry.png
        ├── screenshot_3_grade_sheet_sgpa.png
        ├── screenshot_4_attendance_defaulters.png
        └── screenshot_5_class_performance_summary.png
```

---

## 🚀 Getting Started & Installation

### Prerequisites
- **Java Development Kit**: JDK 17 or higher (tested on **JDK 21**).
- **MySQL Database Server**: MySQL Community Server 8.0 or higher running on port `3306`.
- **Operating System**: Windows / Linux / macOS.

### 1. Database Configuration & Schema Import

1. Start your local MySQL server.
2. Ensure your password matches the configuration in [`DBConnection.java`](DBConnection.java) (default is `0000`).
   ```java
   private static final String URL  = "jdbc:mysql://localhost:3306/kec_eval?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
   private static final String USER = "root";
   private static final String PASS = "0000";
   ```
3. Import the database schema and sample records:
   - **Using PowerShell**:
     ```powershell
     Get-Content kec_schema.sql -Raw | mysql -u root -p0000
     ```
   - **Using MySQL CLI**:
     ```sql
     mysql -u root -p0000 < kec_schema.sql
     ```

### 2. Compile the Project

Compile all Java source files with the bundled MySQL connector:
```powershell
javac -cp ".;mysql-connector-j-8.4.0.jar" *.java
```
*Or simply execute the provided build script:*
```cmd
compile.bat
```
*(On PowerShell: `./compile.ps1`)*

### 3. Run Interactive Console Application

Launch the interactive menu-driven console:
```powershell
java -cp ".;mysql-connector-j-8.4.0.jar" MainApp
```
*Or simply execute:*
```cmd
run.bat
```
*(On PowerShell: `./run.ps1`)*

### 4. Run Automated Demonstration Showcase

To run the complete verification script demonstrating student enrollment, 3-phase incremental marks entry, attendance rule rejection, grade sheet generation, defaulter reporting, and class performance summary:
```powershell
java -cp ".;mysql-connector-j-8.4.0.jar" MainApp --demo
```
*Or simply run:*
```cmd
run_demo.bat
```

---

## 📦 Artifacts & Media Links

- **Documentation PDF**: [JAVA_ASSIGNMENT-VETRI_VEL_MURUGAN_25ITR169.pdf](JAVA_ASSIGNMENT-VETRI_VEL_MURUGAN_25ITR169.pdf)
- **Video Demonstration**: [Video Project(1).mp4](Video%20Project(1).mp4)
- **Detailed Execution Test Log**: [EXECUTION_DOCUMENTATION.md](EXECUTION_DOCUMENTATION.md)
- **Database DDL & Seed Script**: [kec_schema.sql](kec_schema.sql)

---

## 📜 License & Acknowledgments

Developed by **Vetrivel Murugan** (Roll No: `25ITR169`), Department of Information Technology, **Kongu Engineering College (Autonomous)**. 

All academic weightage formulations, grading brackets, and attendance condonation rules adhere to **Kongu Engineering College (Autonomous) - Regulation 2024**.
