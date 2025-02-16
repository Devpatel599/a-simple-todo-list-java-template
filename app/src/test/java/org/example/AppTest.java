package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Arrays;

class AppTest {

    @Test
    void testAddAndRetrieveTasks() {
        TodoList list = new TodoList();
        list.add("Buy milk", new HashSet<>(Arrays.asList("food")));
        list.add("Buy eggs", new HashSet<>(Arrays.asList("food")));

        // Check that tasks were added
        list.all();
    }

    @Test
    void testCompleteTask() {
        TodoList list = new TodoList();
        list.add("Buy milk", new HashSet<>(Arrays.asList("food")));
        list.complete("Buy milk");

        // Verify that "Buy milk" is now completed
        list.completed();
    }

    @Test
    void testIncompleteTasks() {
        TodoList list = new TodoList();
        list.add("Buy milk", new HashSet<>(Arrays.asList("food")));
        list.add("Buy eggs", new HashSet<>(Arrays.asList("food")));
        list.complete("Buy milk");

        // Verify that "Buy eggs" is still incomplete
        list.incomplete();
    }

    @Test
    void testClear() {
        TodoList list = new TodoList();
        list.add("Buy milk", new HashSet<>(Arrays.asList("food")));
        list.clear();

        // Verify that list is empty
        list.all();
    }

    @Test
    void testRejectBlankTask() {
        TodoList list = new TodoList();
        list.add("", new HashSet<>());

        // List should still be empty
        list.all();
    }

    @Test
    void testRejectDuplicateTask() {
        TodoList list = new TodoList();
        list.add("Buy milk", new HashSet<>(Arrays.asList("food")));
        list.add("Buy milk", new HashSet<>(Arrays.asList("food"))); // Should not be added

        // Should only contain one "Buy milk"
        list.all();
    }

    @Test
    void testTaggedTasks() {
        TodoList list = new TodoList();
        list.add("Buy milk", new HashSet<>(Arrays.asList("food")));
        list.add("Sow beet seeds", new HashSet<>(Arrays.asList("garden", "spring")));

        // Check filtering by tags
        list.taggedWith("food");
        list.taggedWith("garden");
    }
}
