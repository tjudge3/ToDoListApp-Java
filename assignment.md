
Priority queues (detailed tutorial here),  as you might expect are sorted by either "natural" or user-defined priority sorting rules.  For this assignment,  you will create an interactive To-Do (Task list)  application using a PriorityQueue data structure.  To-Do items or tasks have a priority associated with them and this priority will be the sort criteria for your PriorityQueue.

Unlike in the BinarySearch exercise you did,  if coded correctly,  priority queue elements will sort automatically.  

Your PriorityQueue will store Task objects, defined by the Task class below.

Two member attributes in Task use enum types Status and Priority, as defined as enum "classes" that will be used in your Task class.  Notice the order of the
Priority enumerations. This is the sort order, your queue will use:

class Task implements Comparable<Task> {
   int taskId;
   String subject;
   Priority priority; 
   Status status; 
   LocalDateTime startDate;
   LocalDateTime dueDate; 
}

enum Priority {
     URGENT,
     HIGH,
     NORMAL, 
     LOW}

enum Status{
     NOT_STARTED,
     IN_PROGRESS, 
     WAITING,
     DEFERRED
}


You will create an interactive test driver as we did for the BinarySearch exercise.  A text-based driver is fine but if you are feeling ambitious,  you are welcome to create a simple graphical UI  or web UI using Java JSP/Servlets (not required). Your interactive test driver loop will allow users to:

Add a task.  This will require you to capture the subject, priority, and dueDate.  Here are some tips on using Java LocalDates.  Use now() for the startDate.  TaskId should start at 1 and increment each time a task is added. 
View the next task in the queue using the peek() method and then optionally complete the current task using the poll() method. 
View a list of all tasks.   This will require the use of an iterator and the peek() method on each element.  Use isEmpty() to check if there are items in your queue and if not,  display "No tasks in the queue." 
View a single task by id.  This will require iterating the task list until the id is found and then displaying it. 
Remove a task by id.    This will require you to iterate through the Task queue until you find the task with the matching id.  Use remove(task) and confirm to the user that the task has been removed.  
Note;  All input must be validated, that is, checked for correctness
 

For readability,  create a public toString method so that you can easily print queue elements:  

@Override
public String toString() {
    return "Id:" + taskId + "; Subject: " + subject + "; Status: " + status + "; Priority: " + priority + "; StartDate: " + startDate.toString() + "; Due date: " + dueDate;
}
To force PriorityQueue to use Priority as your sorting criterion,  you should override the default comparator for the sort by adding this method to your Task class.  Task should implement the interface Comparable<Task> 
@Override
public int compareTo(Task task) {
        return this.getPriority().compareTo(task.getPriority());
}
Note:  If your PriorityQueue were just a list of numbers or simple strings, you wouldn't need your own comparator, but in our case, we are using a more complex object-type, so we are required to create our own.   

Here is a priority helper method for your test-driver if you want, as a freebie:

private Priority scanForPriority() {
 System.out.println("Enter priority abbreviation Normal=n,Low=l,High=h,Urgent=u): ");

String abbrev = scanner.nextLine();
switch (abbrev) {
    case "n":
        priority = Priority.NORMAL;
       break;
    case "l":
         priority = Priority.LOW;
        break;
    case "h":
        priority = Priority.HIGH;
       break;
   case "u":
      priority = Priority.URGENT;
     break;
  }
  return priority;
}
You should create your own scanForStatus() method that follows the pattern above. 

Use a Do-While loop to code up a test driver method for your PriorityQueue interactive testing.  Here is a screenshot of a portion of one solution for your "menus" as a hint, in case you are confused.
Try it before you look:
public void testPriorityQueue() {
System.out.println("Welcome to My Task List\n");
System.out.println("Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q): ");
String menuItem = scanner.nextLine();
do {
  switch (menuItem) {
  case "a":
    addTask();
    break;
 case "n":
   displayNextTask();
   break;
 case "l":
   showTaskList();
   break;
 case "e":
   System.out.println("Enter taskId: ");
   int taskId = parseInt(scanner.nextLine());
   Task task = getTaskById(taskId);
   editTask(task);
   break;
 case "d":
   System.out.println("Enter taskId: ");
   taskId = parseInt(scanner.nextLine());
   showTaskDetail(taskId);
   break;
 case "r":
   System.out.println("Enter taskId: ");
   taskId = parseInt(scanner.nextLine());
   removeTask(taskId);
 case "q":
   break;
}
System.out.println("Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q): ");
menuItem = scanner.nextLine();
if (menuItem.equals("q"))
  break;
}
while (menuItem != "q");
  System.out.println("Goodbye");
} 
Template Implementation of the Application:

A template of the program structure is provided here. Please try to code the app yourself and use the template only if you're stuck.  Download A template of the program structure is provided here. Please try to code the app yourself and use the template only if you're stuck.  

Extra Credit  (Strongly Recommended): 

Your app will be a lot more functional if you can edit your tasks after they are created.  For extra credit,  create a method with the following signature: 

public Task editTask(Task task) {
// Your code here to change task attributes. 

Better yet, make it optional to keep the existing attribute like this: 
System.out.println("Enter new subject or press enter to leave unchanged: ");
String subject = scanner.nextLine();
if (!subject.equals("")) {
    task.subject = subject;
}

// Same idea for other attributes. Use scanForPriority and scanForStatus to set those attributes. 
 
Extra, Extra Credit  (Strongly suggested but not mandatory) : 

Your app will be a lot more useful if it persists (saves) your data between sessions, instead of just storing it in memory. What's the use of a To-Do list that doesn't save your tasks between sessions? 
Fortunately, you can save your data to a file before you exit and read-in your data when you launch your app.   An easy way to do this is to store your data in a comma-separated-value File.   This topic is discussed in Unit 9.  
How to Read/Write your Task Data to a File
Write Tasks to CSV file.docx 

Sample Output

Welcome to My Task List

Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q):
a
Adding new Task...
Enter subject:
Square the circle
Enter due date (yyyy-MM-dd):
2020-11-31
Date format exception. Try again.
Enter due date (yyyy-MM-dd):
2020-10-31
Enter priority abbreviation Normal=n,Low=l,High=h,Urgent=u):
l
Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q):
a
Adding new Task...
Enter subject:
Remove the rubble
Enter due date (yyyy-MM-dd):
2020-11-20
Enter priority abbreviation Normal=n,Low=l,High=h,Urgent=u):
n
Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q):
a
Adding new Task...
Enter subject:
Drive the drivers
Enter due date (yyyy-MM-dd):
2020-11-15
Enter priority abbreviation Normal=n,Low=l,High=h,Urgent=u):
h
Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q):
a
Adding new Task...
Enter subject:
Circle the square
Enter due date (yyyy-MM-dd):
2021-11-05
Enter priority abbreviation Normal=n,Low=l,High=h,Urgent=u):
u
Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q):
l
Id:4; Subject: Circle the square; Status: Not Started; Priority: Urgent; StartDate: 2020-10-30; Due date: 2021-11-05
Id:3; Subject: Drive the drivers; Status: Not Started; Priority: High; StartDate: 2020-10-30; Due date: 2020-11-15
Id:2; Subject: Remove the rubble; Status: Not Started; Priority: Normal; StartDate: 2020-10-30; Due date: 2020-11-20
Id:1; Subject: Square the circle; Status: Not Started; Priority: Low; StartDate: 2020-10-30; Due date: 2020-10-31
Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q):
n
Id:4; Subject: Circle the square; Status: Not Started; Priority: Urgent; StartDate: 2020-10-30; Due date: 2021-11-05
Is this task complete? y/n:
y
Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q):
l
Id:3; Subject: Drive the drivers; Status: Not Started; Priority: High; StartDate: 2020-10-30; Due date: 2020-11-15
Id:1; Subject: Square the circle; Status: Not Started; Priority: Low; StartDate: 2020-10-30; Due date: 2020-10-31
Id:2; Subject: Remove the rubble; Status: Not Started; Priority: Normal; StartDate: 2020-10-30; Due date: 2020-11-20
Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q):
e
Enter taskId:
2
TaskId not found...
TaskId not found...
Edit Task:
Enter a new subject or press enter to leave unchanged:
Remove the rabble
Enter new due date (yyyy-MM-dd), or press enter to leave unchanged:

Enter status abbreviation Not Started=n,Deferred=d,Waiting=w,Complete=c):
u
Enter priority abbreviation Normal=n,Low=l,High=h,Urgent=u):
l
Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q):
l
Id:3; Subject: Drive the drivers; Status: Not Started; Priority: High; StartDate: 2020-10-30; Due date: 2020-11-15
Id:1; Subject: Square the circle; Status: Not Started; Priority: Low; StartDate: 2020-10-30; Due date: 2020-10-31
Id:2; Subject: Remove the rabble; Status: Not Started; Priority: Low; StartDate: 2020-10-30; Due date: 2020-11-20
Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q):
2
Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q):
e
Enter taskId:
2
TaskId not found...
TaskId not found...
Edit Task:
Enter a new subject or press enter to leave unchanged:

Enter new due date (yyyy-MM-dd), or press enter to leave unchanged:

Enter status abbreviation Not Started=n,Deferred=d,Waiting=w,Complete=c):
w
Enter priority abbreviation Normal=n,Low=l,High=h,Urgent=u):
u
Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q):
l
Id:3; Subject: Drive the drivers; Status: Not Started; Priority: High; StartDate: 2020-10-30; Due date: 2020-11-15
Id:1; Subject: Square the circle; Status: Not Started; Priority: Low; StartDate: 2020-10-30; Due date: 2020-10-31
Id:2; Subject: Remove the rabble; Status: Waiting; Priority: Urgent; StartDate: 2020-10-30; Due date: 2020-11-20
Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q):
q
Goodbye
