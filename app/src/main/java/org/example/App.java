package org.example;

import java.util.*;

class TodoList {
    private static class Task {
        String name;
        boolean isComplete;
        Set<String> tags;

        Task(String name, Set<String> tags) {
            this.name = name;
            this.isComplete = false;
            this.tags = tags;
        }
    }

    private final List<Task> tasks = new ArrayList<>();

    // Add a new task to the list
    public void add(String taskName, Set<String> tags) {
        if (taskName == null || taskName.trim().isEmpty()) {
            System.out.println("Task name cannot be empty!");
            return;
        }

        boolean isDuplicate = tasks.stream()
                .anyMatch(task -> task.name.equals(taskName) && !task.isComplete);
        
        if (isDuplicate) {
            System.out.println("Task already exists in the incomplete list.");
            return;
        }

        tasks.add(new Task(taskName, tags));
    }

    // Mark a task as complete
    public void complete(String taskName) {
        for (Task task : tasks) {
            if (task.name.equals(taskName) && !task.isComplete) {
                task.isComplete = true;
                return;
            }
        }
        System.out.println("Task not found or already completed.");
    }

    // Show all tasks
    public void all() {
        if (tasks.isEmpty()) {
            System.out.println("Your to-do list is empty.");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task.name + (task.isComplete ? " [Completed]" : " [Incomplete]"));
        }
    }

    // Show only completed tasks
    public void completed() {
        boolean found = false;
        for (Task task : tasks) {
            if (task.isComplete) {
                System.out.println(task.name);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No completed tasks.");
        }
    }

    // Show only incomplete tasks
    public void incomplete() {
        boolean found = false;
        for (Task task : tasks) {
            if (!task.isComplete) {
                System.out.println(task.name);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No incomplete tasks.");
        }
    }

    // Delete all tasks
    public void clear() {
        tasks.clear();
        System.out.println("To-do list cleared.");
    }

    // Show tasks with a specific tag
    public void taggedWith(String tag) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.tags.contains(tag)) {
                System.out.println(task.name);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found with tag: " + tag);
        }
    }
}

public class App {
    public static void main(String[] args) {
        TodoList list = new TodoList();

        // Adding tasks with tags
        list.add("Buy milk", new HashSet<>(Arrays.asList("food")));
        list.add("Buy eggs", new HashSet<>(Arrays.asList("food")));
        list.add("Prepare a lesson for CSC 122", new HashSet<>());
        list.add("Sow beet seeds", new HashSet<>(Arrays.asList("food", "garden", "spring")));

        // Completing a task
        list.complete("Buy eggs");

        // Display all tasks
        System.out.println("\nAll tasks:");
        list.all();

        // Display completed tasks
        System.out.println("\nCompleted tasks:");
        list.completed();

        // Display incomplete tasks
        System.out.println("\nIncomplete tasks:");
        list.incomplete();

        // Filter tasks by tag
        System.out.println("\nTasks with tag 'food':");
        list.taggedWith("food");

        // Clear the list
        list.clear();

        // Display all tasks after clearing
        System.out.println("\nAfter clearing:");
        list.all();
    }
}
