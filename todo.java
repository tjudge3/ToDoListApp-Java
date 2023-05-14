//Project5 - ToDoList - tjudge
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
public class todo {


    PriorityQueue<Task> taskPriorityQueue = new PriorityQueue<>();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        todo app = new todo();
        app.testPriorityQueue();
    }

    public void displayNextTask() {
        for (Task task : taskPriorityQueue) {
            if (task.priority == Priority.URGENT) {
                System.out.println(task);
                System.out.println("Is this task complete? y/n:");
                String input = scanner.nextLine();
                break;
            }
        }
    }

    public void showTaskDetail(int taskId)
    {
        Task task = getTaskById(taskId);
        if (task!=null)
            System.out.println(task);
    }

    public Task getTaskById(int taskId)  {

        for (Task task : taskPriorityQueue) {
            if (task.getTaskId() == taskId)
                return task;
        }
        System.out.println("TaskId not found...");
        return null;
    }

    public void removeTask(int taskId)  {
        for (Task task : taskPriorityQueue) {
            if (task.getTaskId() == taskId) {
                taskPriorityQueue.remove(task);
                System.out.println("Task removed...");
                return;
            }
        }
    }

    public void editTask(int taskId) {
        Iterator<Task> it = taskPriorityQueue.iterator();
        boolean checker=false;
        while (it.hasNext()) {
            Task task = it.next();
            if (task.getTaskId() == taskId)
            {
                System.out.println("Edit Task:");
                System.out.println("Enter new subject: ");
                String subject = scanner.nextLine();
                System.out.println("Enter new due date (yyyy-mm-dd):");
                String input = scanner.nextLine();
                LocalDate dueDate = LocalDate.parse(input);
                LocalDate startDate = LocalDate.now();
                System.out.println("Enter status abbreviation Not Started=n,Deferred=d,Waiting=w,Complete=c):");
                Status status = Status.DEFERRED;
                String statusInp = scanner.nextLine();
                switch (statusInp) {
                    case "n" -> status = Status.NOT_STARTED;
                    case "c" -> status = Status.IN_PROGRESS;
                    case "w" -> status = Status.WAITING;
                    case "d" -> {
                    }
                }

                Priority priority = Priority.NORMAL;
                System.out.println("Enter priority abbreviation Normal=n,Low=l,High=h,Urgent=u):");
                String abbrev = scanner.nextLine();
                switch (abbrev) {
                    case "n" -> {
                    }
                    case "l" -> priority = Priority.LOW;
                    case "h" -> priority = Priority.HIGH;
                    case "u" -> priority = Priority.URGENT;
                }

                if (!subject.equals("")) {
                    task.subject = subject;
                }
                if (!dueDate.equals("")) {
                    task.dueDate = dueDate;
                    task.startDate = startDate;
                }
                if (!statusInp.equals("")) {
                    task.status = status;
                }

                if (!abbrev.equals("")) {
                    task.priority = priority;
                }

                return;
            }
        }
        if (!checker)
        {
            System.out.println("TaskId not found...");
        }
    }


    public void testPriorityQueue() throws IOException {
        System.out.println("Welcome to My Task List\n");
        do {
            System.out.println("Choose action (Add(a), Next(n), List(l), Detail(d), Edit(e), Remove(r), Export(x), Import(i),  Quit(q):");

            String menuItem = scanner.nextLine();

            switch (menuItem) {
                case "a":
                    addTask();
                    break;
                case "n":
                    displayNextTask();
                    break;
                case "l":
                    showTaskSummaryList();
                    break;
                case "d":
                    System.out.println("Enter taskId: ");
                    int taskId = Integer.parseInt(scanner.nextLine());
                    showTaskDetail(taskId);
                    break;
                case "e":
                    System.out.println("Enter taskId: ");
                    int taskID = Integer.parseInt(scanner.nextLine());
                    editTask(taskID);
                    break;
                case "r":
                    System.out.println("Enter taskId: ");
                    taskId = Integer.parseInt(scanner.nextLine());
                    removeTask(taskId);
                    break;
                case "x":
                    exportToFile();
                    System.out.println("Todo List Exported to toDo.txt");
                    break;
                case "i":
                    importFromFile();
                    System.out.println("Todo List Imported from to toDo.txt");
                    break;
                case "q":
                    System.out.println("Goodbye.");
                    return;
            }
        } while (true);
    }

    void showTaskSummaryList() {
        for (Task task: taskPriorityQueue)
            System.out.println(task);
    }
    private Priority scanForPriority() {
        System.out.println("Enter priority: ");
        System.out.println("Urgent = u");
        System.out.println("High = h");
        System.out.println("Normal = n");
        System.out.println("Low = l");
        String priority = scanner.nextLine();
        return switch (priority) {
            case "u" -> Priority.URGENT;
            case "h" -> Priority.HIGH;
            case "n" -> Priority.NORMAL;
            case "l" -> Priority.LOW;
            default -> Priority.NORMAL;
        };
    }

    void addTask() {
        System.out.println("Adding new Task...");
        System.out.println("Enter subject:");
        Scanner scanner = new Scanner(System.in);
        String subject = scanner.nextLine();

        System.out.println("Enter due date (yyyy-MM-dd):");
        String input = scanner.nextLine();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dueDate = LocalDate.parse(input);
        Priority priority = scanForPriority();

        Status status = Status.NOT_STARTED;
        LocalDate date = LocalDate.now();
        LocalDate startDate = LocalDate.now();
        Task task = new Task(taskPriorityQueue.size() + 1, subject, priority, status, startDate, dueDate);
        taskPriorityQueue.add(task);
    }

    enum Priority {
        URGENT,
        HIGH,
        NORMAL,
        LOW
    }

    enum Status {
        NOT_STARTED,
        IN_PROGRESS,
        WAITING,
        DEFERRED
    }



    static class Task implements Comparable<Task> {

        int taskId;
        String subject;
        Priority priority;
        Status status;
        LocalDate startDate;
        LocalDate dueDate;

        public Task(int taskId, String subject, Priority priority, Status status, LocalDate startDate, LocalDate dueDate) {
            this.taskId = taskId;
            this.subject = subject;
            this.priority = priority;
            this.status = status;
            this.startDate = startDate;
            this.dueDate = dueDate;
        }


        public int getTaskId() {
            return taskId;
        }

        @Override
        public String toString() {
            return "Id:" + taskId + "; Subject: " + subject + "; Status: " + status + "; Priority: " + priority + "; StartDate:" + startDate.toString() + "; Due date:" + dueDate;
        }

        public Priority getPriority() {
            return priority;
        }


        @Override
        public int compareTo(Task task) {
            return this.getPriority().compareTo(task.getPriority());        }
    }

    public void exportToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("toDo.txt");
        for (Task task : taskPriorityQueue) {
            fileWriter.write(task.toString() + "\n");
        }
        fileWriter.close();
    }

    public void importFromFile() throws IOException {
        File file = new File("toDo.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] task = line.split(";");
            String subject = task[1].substring(9);
            String priority = task[2].substring(10);
            String status = task[3].substring(8);
            String startDate = task[4].substring(11);
            String dueDate = task[5].substring(10);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(startDate, dateFormat);
            LocalDate due = LocalDate.parse(dueDate, dateFormat);
            Priority priority1 = switch (priority) {
                case " URGENT" -> Priority.URGENT;
                case " HIGH" -> Priority.HIGH;
                case " LOW" -> Priority.LOW;
                default -> Priority.NORMAL;
            };
            Status status1 = switch (status) {
                case " IN_PROGRESS" -> Status.IN_PROGRESS;
                case " WAITING" -> Status.WAITING;
                case " DEFERRED" -> Status.DEFERRED;
                default -> Status.NOT_STARTED;
            };
            Task task1 = new Task(taskPriorityQueue.size() + 1, subject, priority1, status1, start, due);
            taskPriorityQueue.add(task1);
        }
    }
//I know only export was asked for, but I wanted to see if I could get the import to work as well.
//it was easier to pre-define the name of the file than ask for the user to decide.
}
