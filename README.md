WEB APPLICATION DEVELOPMENT USING JAVA EE - SA DIPLOMA CA Page 1 of 10.
 ATA/SA-TERM2/JAVA3/SA52-CA-ProjectDocument/V10.0 © 2021 NUS. All rights reserved 
Continuous Assessment Instructions
Course Application Processing System (CAPS)
2021 NUS. The contents contained in this document may not be reproduced in 
any form or by any means, without the written permission of ISS, NUS, other 
than for the purpose for which it has been supplied.WEB APPLICATION DEVELOPMENT USING JAVA EE - SA DIPLOMA CA Page 2 of 10.
 ATA/SA-TERM2/JAVA3/SA52-CA-ProjectDocument/V10.0 © 2021 NUS. All rights reserved 
Contents
Objective............................................................................................................................................................ 3
Architecture ..................................................................................................................................................... 3
Software Development tools ..................................................................................................................... 3
Design Consideration ................................................................................................................................... 4
Functional Requirements ........................................................................................................................... 4
General Features........................................................................................................................................ 4
The Administrator Module .................................................................................................................... 5
The Student Module ................................................................................................................................. 6
The Lecturer Module................................................................................................................................ 6
Additional Features .................................................................................................................................. 7
Evaluation Criteria......................................................................................................................................... 7
Stages of work ................................................................................................................................................. 8
Deliverables...................................................................................................................................................... 8
Interaction with the Lecturer.................................................................................................................... 8
Schedule............................................................................................................................................................. 9WEB APPLICATION DEVELOPMENT USING JAVA EE - SA DIPLOMA CA Page 3 of 10.
 ATA/SA-TERM2/JAVA3/SA52-CA-ProjectDocument/V10.0 © 2021 NUS. All rights reserved 
Objective
The objective of this project is to apply the knowledge and skills that you have gained 
from attending and practicing the lectures, demonstrations and workshops. 
Management information systems for academic institutions cover major transactions like 
student applications, student scores and performance, scheduling, attendance 
monitoring, and course list development. All these transactions are done manually by 
officers and administrators, which may cause overheads on resources, time and cost.
You will demonstrate your team’s software craftsmanship by building a web application 
for processing course enrolment. This application will be called CAPS.
The CAPS is used by three kinds of users: students, lecturers and administrators. For 
simplicity, let us assume the system is used to manage course enrolment for the current 
year and each course is run only once in the=is year. 
Architecture
The team will use MVC Architecture for constructing CAPS. The purpose of building MVC 
applications is not only for the sake of the new knowledge you acquire, but also for the 
maintainability and the manageability of codes after project delivery.
Software Development tools
Version Tool Name Details
Java SE JDK Version 
1.8
You can download a copy on line 
STS or any IDE 
with a server
Local Tomcat 
Server
You can download a copy on line 
Data Base JDBC API Preferably MySQL. If your team is 
comfortable, you can chose any other 
valid RDBMS in the market. 
Framework Spring Usage of framework is mandatory. 
The team can explore additional 
features of the Spring framework if all 
members are comfortable
Client Side ReactJS Select one or two use cases to 
implement as a client-side app. For 
example, student views. The team can 
perform client-side validation as 
necessary 
Optional Team can explore any additional 
features for pagination, email etc for 
additional creditsWEB APPLICATION DEVELOPMENT USING JAVA EE - SA DIPLOMA CA Page 4 of 10.
 ATA/SA-TERM2/JAVA3/SA52-CA-ProjectDocument/V10.0 © 2021 NUS. All rights reserved 
Design Consideration
The following are the design considerations:
 The system is developed using Java and Spring framework components. ReactJS is 
optional. 
 You should strictly follow the MVC Architecture for server side.
 You should strictly follow JPA based persistence architecture. 
 The system uses MySQL Database to store the data. Do design appropriate data 
scripts and also populate the database with sufficient test data. You can explore 
any NoSQL for extra credits for a selected use case of your choice
 Use of client-side additional technologies such as Java Script, JSON and JQuery are 
optional. 
 Credits would be awarded for any creative work your team decides to explore 
using additional frameworks or libraries.
 We are interested in versatility than volume, please create enough test cases with 
dummy test data to thoroughly test the system before the final presentations.
Functional Requirements
This CAPS software is a typical management information system for course enrolment 
and award of grades in academic institutions that has automated selective administrative 
functions. Through integrating the concept of relational database management systems
such as MySQL, the accuracy, and integrity, of simultaneous and redundant access to 
these data, are monitored and audited periodically.
General Features 
This wireframe shows the façade of the CAPS. To attract aspirants and students, we plan 
for this page to contain some marketing concepts, like the presence of images, news 
scoops, and other campus-related images. Teams can design this as a simple static web 
page. Teams can also use style sheets to standardize the look and feel across the 
application.
CAPS 
Dialog Title NEWS
Column 1
Home Admin Log In Student Log In Lecturer Log In
2
1
0
Some text about the institution
Some ButtonWEB APPLICATION DEVELOPMENT USING JAVA EE - SA DIPLOMA CA Page 5 of 10.
 ATA/SA-TERM2/JAVA3/SA52-CA-ProjectDocument/V10.0 © 2021 NUS. All rights reserved 
 An actor can be admin or student or staff.
 The teams can choose to have three different log in pages or one integrated page 
with automated role selection.
 CAPS application has a centralized data store to manage student, lecturer and 
course and enrolment information. 
 The CAPS system has a common logout page. 
 Once a user with proper credential logs into the system, the system authorizes that 
user and displays appropriate welcome page befitting the role. Also menu 
functionalities must be relevant to that particular user role. 
The Administrator Module 
This module of CAPS adds, removes, updates and retrieves all master lists of courses, 
students, lecturers and current year enrolment. Only administrators can access this 
module. The wireframe could be like the following:
CAPS 
Home Admin Log In Student Log In Lecturer Log In
Manage Students
Manage Lecturers
Manage Courses
Manage Enrolment
Log Out
Manage Students Page
Add Student
Edit Delete
Edit Delete
Edit Delete
Edit Delete
Edit Delete
Edit Delete
Edit Delete
Edit Delete
 Administrators create and manage student related information.
 Administrators create and manage lecturer related information.
 Administrators create and manage course and enrolment related information
such as class size etc.
 Administrators update the status of enrolment provided there is a request from 
the lecturer to do so. Normally students enrol themselves to courses of their 
interest. Administrators are empowered to override this selection. WEB APPLICATION DEVELOPMENT USING JAVA EE - SA DIPLOMA CA Page 6 of 10.
 ATA/SA-TERM2/JAVA3/SA52-CA-ProjectDocument/V10.0 © 2021 NUS. All rights reserved 
The Student Module 
This wireframe below is dedicated to the page that manages student profiles and 
scholastic information. The student's individual grades, courses they are enrolled in, and 
the overall courses they took with the GPA, are all the concerns of this area.
CAPS 
Home Admin Log In Student Log In Lecturer Log In
Grades and GPA
View Courses
Enroll for a Course
Log Out
Grades and GPA
Course Id Course Name Credits Grade
 Any student can enrol for a class. Enrolment is on a first-come-first-served basis. 
Each course has a capacity. When a student attempts to enrol for a course that is 
full, an appropriate directive message is provided.
 A student can view list of courses that he/she can enrol. This list must not show 
courses that the student has completed.
 A student can also look at his courses, grades and the Grade Point Average. Teams 
can devise their own formula for such calculations.
The Lecturer Module 
This wireframe is to be accessed by any valid faculty member (lecturers) of the institution 
wherein they can input grades, manage students' performance, and manages their course 
loads and schedules.
 A lecturer can look at the list of enrolled students for a particular course that he 
teaches.
 A lecturer can input scores for individual student for a particular course he 
teaches.WEB APPLICATION DEVELOPMENT USING JAVA EE - SA DIPLOMA CA Page 7 of 10.
 ATA/SA-TERM2/JAVA3/SA52-CA-ProjectDocument/V10.0 © 2021 NUS. All rights reserved 
CAPS 
Home Admin Log In Student Log In Lecturer Log In
View Course 
Enrolment
View Courses Taught
Grade a Course
Log Out
Course Taught
Course Id Course Name Size Current 
Enrollment
View a Student 
Performance
Additional Features 
 Pagination in View Course Enrolment Page.
 Email student after they enrol to confirm.
 Use calendar and time table facility to improve course details
 Use Style Sheets.
 A lecturer can view a particular student’s CGPA performance.
 Write More Automated Test Cases.
 Any other feature deems of business value!!!!
Evaluation Criteria
The followings are sources the evaluation criteria.
1. Workable Solution [eclipse workspace]
2. Java implementation best practices; for example, object encapsulation, entity 
modelling, layering of architecture etc. Credits would be allocated for proper 
exception handling implementations, validation logic, test cases and other utility 
classes.
3. Demonstration must have minimal presentation slides (5 SLIDES ONLY).
4. You will be marked on the following attributes:
a. Mandatory Use Cases 
b. Screen flow 
c. Business Rules and Validation 
d. Exception Handling WEB APPLICATION DEVELOPMENT USING JAVA EE - SA DIPLOMA CA Page 8 of 10.
 ATA/SA-TERM2/JAVA3/SA52-CA-ProjectDocument/V10.0 © 2021 NUS. All rights reserved 
e. Test Cases (both good and bad)
5. Additional marks will be awarded for any additional creative work. For 
example, use of frameworks, pagination of records, use of good open source, 
validation, use of templates, security, style sheets etc.,
6. Your code demonstration must walk through aspects such as:
o Code Layering 
o Controller Layer 
o Model Layer 
o Service Later (Optional)
o DAO Layer 
o Quality of View Pages 
o A decent exception management
7. You will also be awarded bonus points for any exemplary extensions 
demonstrated.
8. Bonus points for good use of github and other important collaborative practices 
too! 
Stages of work
1. Stage One: Design of Data Base and Screen Flow
2. Stage Two: Building Prototype & Testing
3. Stage Three: Complete Development and Unit Testing
4. Stage Four: Continuous Integration, Testing and Fine Tuning
5. Stage Five: Delivery and final Acceptance Testing. 
Deliverables
This assignment is part of the continuous assessment for this course. You will be 
evaluated for 25 marks on the whole. You will work in your team. No individual work will 
be accepted. The followings are the deliverables.
1) A 20 minute presentation, explaining the design and code of your team work. 
• Architectural diagram and (general class diagram or ER Model).
• LuniNUS submission: A zip file of your workspace containing all the 
source code, compiled classes, and any other files required to run the 
application. If the team is using additional plug-in or library, it is to be 
bundled with deliverables. Write the names of your group members on 
the disk. You may wish to submit the workspace making it easier to 
evaluate.
• Peer evaluation form.
Interaction with the Lecturer
 Lecturer is available for discussion based on previous appointment for design 
review
Please enrol into Luminus Project Groups Coming Week. WEB APPLICATION DEVELOPMENT USING JAVA EE - SA DIPLOMA CA Page 9 of 10.
 ATA/SA-TERM2/JAVA3/SA52-CA-ProjectDocument/V10.0 © 2021 NUS. All rights reserved 
Schedule
Presentation and Submission of Deliverables
Task Date 
Presentation of Java Solution 2nd JULY 2021 (Friday) 
Submission of Deliverables 
• Eclipse or STS workspace 
• SQL Queries 
• PPT 
• Any other information essential
to run the application 
• Peer evaluation form 
 
Upload to IVLE by the end of 4th 
July 2021. 
Individual Schedule for teams will be announced in IVLE later
Plagiarism Notice: All students share the responsibility for upholding the 
academic standards and reputation of the University. Academic honesty is a 
prerequisite condition in the pursuit and acquisition of knowledge. Academic 
dishonesty is any misrepresentation with the intent to deceive or failure to 
acknowledge the source or falsification of information or inaccuracy of 
statements or cheating at examinations/tests or inappropriate use of 
resources. Please avoid sharing code, you will be penalized if found guilty. 
Sharing of ideas, design and teaching your peers are most welcome.
Everybody contributes and no excusesWEB APPLICATION DEVELOPMENT USING JAVA EE - SA DIPLOMA CA Page 10 of 10.
 ATA/SA-TERM2/JAVA3/SA52-CA-ProjectDocument/V10.0 © 2021 NUS. All rights reserved 
LUMINUS GROUP ENROLLMENT 
https://wiki.nus.edu.sg/pages/viewpage.action?pageId=22716829
