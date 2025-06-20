
create region --name=People --type=PARTITION

curl -X PUT -H "Content-Type: application/json" -d '{"id": "1", "name": "Alice", "age": 30}' http://localhost:8080/persons
{"id":"1","name":"Alice","age":30}%                                                                                                       
(base) avannala@Q2HWTCX6H4 my-gemfire-app % curl http://localhost:8080/persons/1
{"id":"1","name":"Alice","age":30}%             

# gemfire-app
