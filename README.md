this project allow user to store user informations in our secure website

In order to see the detailled API REST, pleace check the swagger ui 2 using <b>http://localhost:8080/swagger-ui.html</b>

to lunch the server, please 

    1/- cd ${project}
    2/- mvn clean install
    3/- cd client-management-services/
    4/- mvn clean install spring-boot:run


to teste the project, please perform the command :
        1/- curl -X localhost:8080/users/list
    the output has to be 
        [{"firstName":"none","lastName":"none"}]
    you can verify using swagger ui via 
        <b>http://localhost:8080/swagger-ui.html</b>