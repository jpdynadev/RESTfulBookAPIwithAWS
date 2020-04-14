"# RESTfulBookAPIwithAWS" 

This is a RESTful API Project using SpringBoot with AWS RDS as a backend.

Simple CRUD functionality with logging capability. The LogFile.txt is stored on the server and could be set up with Splunk to parse results(Not Implemented).

To retrieve all books go to: http://localhost:8080/all using GET request. 

To retrieve specific book, add the serialID of the book to the end of the GET request uri:http://localhost:8080/book/{serialID}

To add a book use the following format in the body of the POST request uri: http://localhost:8080/add
```
{
	"name" : '<name of book>',
	"description" : '<description of book>'
}
```
To update a book, simply include the following in the body of the PUT request uri: http://localhost:8080/updateBook
```
{
	"name" : '<name of book>',
	"serialId" : '<serialId of book>',
	"description" : '<description of book>'
}
```
To delete book, simply include the following in the body of the DELETE request uri: http://localhost:8080/delete
```
{
	"name" : '<name of book>',
	"serialId" : '<serialId of book>',
	"description" : '<description of book>'
}
```
