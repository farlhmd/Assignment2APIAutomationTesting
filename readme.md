For a better experience on reading this, open from: [Github](https://github.com/farlhmd/Assignment2APIAutomationTesting)
# Assignment 2 | API Testing
This project repository: [Assignment 2](https://github.com/farlhmd/Assignment2APIAutomationTesting)

This automation tested API from: https://jsonplaceholder.typicode.com

Automated with: [Katalon Studio](https://katalon.com)

The end-points are:
```
/posts
/posts/$id
/posts/$id/comments
```
```	
/comments	
```
```
/albums
/albums/$id
/albums/$id/photos
```
```
/photos
```	
```
/todos	
```
```
/users
/users/$id/albums
/users/$id/todos
/users/$id/posts
```



## Running Automation Test with Katalon Studio


1. Clone this project
```bash
  git clone https://github.com/farlhmd/Assignment2APIAutomationTesting
```
2. Open Katalon Studio, then navigate to folder that already cloned
3. Run test from "Complete TS Collection"
4. Enjoy other TS and TS Collection! 💪😌
   
### Test Cases

The test cases are devided by each endpoints:

- Albums&ensp;&ensp;&ensp;&ensp;&ensp; : Consist of 7 Test Cases
- Comments : Consist of 5 Test Cases
- Photos&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; : Consist of 5 Test Cases
- Posts&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; : Consist of 6 Test Cases
- Todos&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; : Consist of 5 Test Cases
- Users&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; : Consist of 8 Test Cases

##### *Notes: These test cases are implementing loop to verify data from JSON Slurper