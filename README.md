### 🧩 **TBL Activity: Use Case Identification**

#### **Prompt:**
Recall that a user story has the form:  
**"As a user, I want to [TASK] so that [GOAL]."**

You’ve read `TodoListPanel.java`. Based on its implementation, which subset of use cases is **best supported** by the code?

---

#### **Use Cases and Distractors (Randomized):**

1. I want to categorize my todo items so that I can organize my tasks better
2. I want to remove a todo item so that I can manage my tasks
3. I want to save my todo list so that I can keep my changes
4. I want to filter my todo list by done or not done items so that I can focus on what needs to be done
5. I want to mark a todo item as done so that I can track my progress
6. I want to view my todo list so that I can choose what to do next
7. I want to set reminders for my todo items so that I can be notified about upcoming tasks
8. I want to add a todo item so that I can keep track of tasks
9. I want to edit a todo item so that I can update task details
10. I want to sort my todo list by priority or due date so that I can prioritize my tasks
11. I want to search for a todo item so that I can quickly find specific tasks
12. I want to set due dates for my todo items so that I can manage my time effectively
13. I want to unmark a todo item as done so that I can revisit tasks
14. When I open the app, I want to see my todo list so that I can start working on my tasks

---

#### **Question:**
**Which of the following subsets of use cases is best supported by the code in `TodoListPanel.java`?**

**A.** Use cases 2, 5, 6, 8  
**B.** Use cases 2, 5, 6, 8, 13  
**C.** Use cases 2, 3, 5, 6, 8, 13  
**D.** Use cases 2, 3, 5, 6, 8, 13, 14

---

#### ✅ **Correct Answer: D**
Assuming `TodoListPanel.java` supports:
- Viewing, adding, removing, marking/unmarking items
- Saving the list
- Loading the list on startup

Then **use cases 2, 3, 5, 6, 8, 13, 14** are supported, while the others are not.

---

Would you like this randomized list exported to a spreadsheet or formatted for a classroom handout?