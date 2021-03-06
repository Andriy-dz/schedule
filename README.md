# University`s schedule

With this program you can create universities, groups, audience, teachers, lessons and students. You can also get all the lessons for the student on a particular day
### Postman commands

###### Inject Data:
- Get: localhost:8081/inject

###### University:
- Post: localhost:8081/universities    
  body: {"name":"name one", "city":"Lviv"}
- Post: localhost:8081/universities/add_all    
  body: [{"name":"name one", "city":"Lviv"}, {"name":"name two", "city":"Lviv"}]
- Get: localhost:8081/universities/{id}
- Get: localhost:8081/universities?universityId={id}
- Put: localhost:8081/universities/{id}   
  body {"name":"name one", "city":"Lviv"}
- Delete: localhost:8081/universities/{id}

###### Groups
- Post: localhost:8081/groups   
  body: {"universityId":1, "name":"D1", "students":[1,2]}
- Post: localhost:8081/groups//add_all  
  body: [{"universityId":1, "name":"D1", "students":[1,2]}, {"universityId":1, "name":"B1", "students":[3]}]
- Get: localhost:8081/groups/{id}
- Get: localhost:8081/groups/?universityId={id}
- Put: localhost:8081/groups/{id}    
  body: {"universityId":1, "name":"D1", "students":[1,2]}
- Delete: localhost:8081/groups/{id}

###### Teachers
- Post: localhost:8081/teachers  
  body: {"universityId":1, "firstName":"Name", "lastName":"LastName", "subject":"Math"}
- Post: localhost:8081/teachers/add_all  
  body: [{"universityId":1, "firstName":"Name", "lastName":"LastName", "subject":"Math"}, {"universityId":1, "firstName":"Name", "lastName":"LastName", "subject":"England"}]
- Get: localhost:8081/teachers/{id}
- Get: localhost:8081/teachers/?universityId={id}
- Put: localhost:8081/teachers/{id}  
  body: {"universityId":1, "firstName":"Name", "lastName":"LastName", "subject":"Math"}
- Delete: localhost:8081/teachers/{id}

###### Audiences
- Post: localhost:8081/audiences  
  body: {"universityId":1, "officeNumber":2}
- Post: localhost:8081/audiences/add_all  
  body: [{"universityId":1, "officeNumber":2}, {"universityId":1, "officeNumber":4}]
- Get: localhost:8081/audiences/{id}
- Get: localhost:8081/audiences/?universityId={id}
- Put: localhost:8081/audiences/{id}   
  body: {"universityId":1, "officeNumber":2}
- Delete: localhost:8081/audiences/{id}

###### Students
- Post: localhost:8081/students  
  body: {"firstName":"firstName", "lastName":"lastName"}
- Post: localhost:8081/students/add_all  
  body: [{"firstName":"firstName", "lastName":"lastName"}, {"firstName":"firstName", "lastName":"lastName"}]
- Get: localhost:8081/students/{id}
- Get: localhost:8081/students/?groupId={id}
- Put: localhost:8081/students/{id}    
  body: {"firstName":"firstName", "lastName":"lastName"}
- Delete: localhost:8081/students/{id}

###### Lessons
- Post: localhost:8081/lessons  
  body: {"groupId":1, "audienceId":1, "teacherId":1, "date":"01.01.2022 09:00"}
- Post: localhost:8081/lessons/add_all  
  body: [{"groupId":1, "audienceId":1, "teacherId":1, "date":"01.01.2022 09:00"}, {"groupId":2, "audienceId":2, "teacherId":2, "date":"01.01.2022 09:00"}]
- Get: localhost:8081/lessons/{id}
- Get: localhost:8081/lessons/?studentId=1&date=01.01.2022
- Put: localhost:8081/lessons/{id}    
  body: {"groupId":1, "audienceId":1, "teacherId":1, "date":"01.01.2022 09:00"}
- Delete: localhost:8081/lessons/{id}

## Implementation details and technologies

### Project based on 3-layer architecture:
>- Presentation layer (controllers)
>- Application layer (services)
>- Data access layer (DAO)

### Technologies
>- Spring Boot
>- Spring Boot WEB
>- Spring Boot DATA
>- Hibernate
>- Hibernate validator
>- PostgreSQL
>- JSON
>- Lombok
>- Maven

### Diagram DB
![drawing](http://dl3.joxi.net/drive/2022/01/26/0052/3292/3415260/60/e570df7e8d.jpg)

## Setup
Execute a command in the terminal : 
- mvn clean package
- docker-compose up
