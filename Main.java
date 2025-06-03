import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Task class
class Task {
    String id;
    String description;
    boolean isCompleted;

    public Task(String id, String description) {
        this.id = id;
        this.description = description;
        this.isCompleted = false;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Description: " + description + ", Completed: " + isCompleted;
    }
}

// TaskManager class
class TaskManager {
    private Map<String, Task> tasks;

    public TaskManager() {
        tasks = new HashMap<>();
    }

    public boolean addTask(String id, String description) {
        if (tasks.containsKey(id)) {
            return false;
        }
        tasks.put(id, new Task(id, description));
        return true;
    }

    public Task getTask(String id) {
        return tasks.get(id);
    }

    public boolean markTaskCompleted(String id) {
        Task task = tasks.get(id);
        if (task != null) {
            task.markCompleted();
            return true;
        }
        return false;
    }

    public boolean removeTask(String id) {
        return tasks.remove(id) != null;
    }

    public void listAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (Task task : tasks.values()) {
            System.out.println(task);
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println("\nTo-Do List Application");
            System.out.println("1. Add Task");
            System.out.println("2. View Task");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Remove Task");
            System.out.println("5. List All Tasks");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Task ID (Acc. to importance): ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Task Description: ");
                    String description = scanner.nextLine();
                    if (manager.addTask(id, description)) {
                        System.out.println("Task added successfully.");
                    } else {
                        System.out.println("Task with this ID already exists.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Task ID to view: ");
                    id = scanner.nextLine();
                    Task task = manager.getTask(id);
                    if (task != null) {
                        System.out.println("Task Details: " + task);
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Task ID to mark as completed: ");
                    id = scanner.nextLine();
                    if (manager.markTaskCompleted(id)) {
                        System.out.println("Task marked as completed.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    id = scanner.nextLine();
                    if (manager.removeTask(id)) {
                        System.out.println("Task removed successfully.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;

                case 5:
                    System.out.println("All tasks:");
                    manager.listAllTasks();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
