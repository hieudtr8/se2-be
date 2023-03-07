# SE2 ecommerce project

## Development
- Clone develop branch of this repo to your local
  ```
  git clone -b develop https://github.com/hieudtr8/se2-be.git
  ```
- Make sure mysql is installed
- Edit file `Application.properties` to match with database info
    ```properties
    spring.datasource.url = jdbc:mysql://localhost:3306/yourDatabaseName?createDatabaseIfNotExist=true
    spring.datasource.username = yourUsername
    spring.datasource.password = yourPassword

    spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
    spring.jpa.properties.hibernate.globally_quoted_identifiers=true
    spring.jpa.generate-ddl=true
    spring.jpa.hibernate.ddl-auto=update
    ```
- Add configuration to run the main file
- Start the project. Api will be available in `http://localhost.8080`

## Contribute
- Commit and push to develop branch, **NOT** the main branch
- Pull the repo frequently to update to the newest version