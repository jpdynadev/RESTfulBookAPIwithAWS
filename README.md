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

Responses will look like the following: 

``
{
    "success": "Success",                                         <--- "success" object designating result of response
    "comment": "Sucessfully found all books",			  <--- "Comment" for the response, if failed will show Exception
    "object": [							  <--- "Object" sent as response, in this example a list with all books
        {
            "name": "test3",
            "serialID": "1",
            "description": "test book 3"
        },
        {
            "name": "test",
            "serialID": "402881707179c68a017179c69f850000",
            "description": "testing"
        },
        {
            "name": "test5",
            "serialID": "40288170717a7ead01717a82095d0000",
            "description": "test book 5"
        }
    ]
}
