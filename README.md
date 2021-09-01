# CMS
this project contains an apis to handle:

- Create, edit and delete employees, projects, and departments
- Assign Employees to Projects, and unassign them as well
- Change the Employee department

the controllers and their apis in this project listed below:
- **Login controller**
  
  - **POST** /login with json`{"username":"user-name or email","password":"user password"}`
  

- **Departments controller**
    - **GET** /departments?size=10&offset=0&name= (to get all departments with pagination and search by name)
    - **GET** /departments/{id} (to get a specific department)
    - **GET** /departments/{id}/users (to get a specific department users)
    - **POST** /departments (to add new department)
    - **PUT** /departments/{id} (to update exist department)
    - **DELETE** /departments/{id} (to delete exist department)


- **Projects controller**
    - **GET** /projects?size=10&offset=0&name= (to get all projects with pagination and search by name)
    - **GET** /projects/{id} (to get a specific project)
    - **POST** /projects (to add new project)
    - **PUT** /projects/{id} (to update exist project)
    - **DELETE** /projects/{id} (to delete exist project and its project-users relation)


- **Users controller**
    - **GET** /users?size=10&offset=0&name= (to get all users with pagination and search by name or user_name or email)
    - **GET** /users/{id} (to get a specific user)
    - **POST** /users (to add new user)
    - **PUT** /users/{id} (to update exist user)
    - **DELETE** /users/{id} (to delete exist user and its project-users relation)

- **project Users controller**
    - **GET** /project-users/{project_id} (to get all users related to a specific project)
    - **POST** /project-users/{project_id} (to assign user to specific project)
    - **DELETE** /project-users/{project_id}/{user_id} (to delete exist project-user relation)
## APIs Notes:
- all APIs authenticated and authorized with **JWT** tokens as **bearer** token
- **Managers** authorized to do all operations
- **employees** allowed to do the GET operations only
- **admin account** (username= `sysadmin`, password = `Abcd1234`)
# technology's stack used:
- java 8
- spring boot framework
- postgresql database
- angular (front end portal)

# How to run this project locally
- you have to run the sql script `schema.sql` under src/main/resources
- modify the DB configuration in `application.yml` under src/main/resources
- run using `mvn spring-boot:run` command
- the project will run at `localhost:8899`
## run the Portal
- got to portal directory
- run `npm install`
- then run `ng serve`
- go to `localhost:4200`
- login to the portal using the default account
 # Admin account credentials
- username : `sysadmin`
- password : `Abcd1234`

