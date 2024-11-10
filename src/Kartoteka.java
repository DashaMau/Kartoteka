import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Kartoteka {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> kartoteka = new List<>();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить студента");
            System.out.println("2. Удалить студента");
            System.out.println("3. Вывести всех студентов");
            System.out.println("4. Очистить картотеку");
            System.out.println("5. Проверить картотеку на пустоту");
            System.out.println("6. Сохранить в файл");
            System.out.println("7. Загрузить из файла");
            System.out.println("0. Выход");
            System.out.print("Введите номер операции: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки

            switch (choice) {
                case 1:
                    Student student = new Student();
                    student.setAttr(scanner);
                    kartoteka.add(student);
                    break;
                case 2:
                    if (kartoteka.isEmpty()) {
                        System.out.println("Картотека пуста!");
                    } else {
                        System.out.println("Введите имя студента для удаления: ");
                        String nameToDelete = scanner.nextLine();
                        kartoteka.delete(nameToDelete);
                    }
                    break;
                case 3:
                    kartoteka.display();
                    break;
                case 4:
                    kartoteka.clear();
                    System.out.println("Картотека очищена.");
                    break;
                case 5:
                    if (kartoteka.isEmpty()) {
                        System.out.println("Картотека пуста.");
                    } else {
                        System.out.println("Картотека не пуста.");
                    }
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор операции.");
            }
        }
    }
}

class Student {
    String name;
    int age;

    public void setAttr(Scanner scanner) {
        System.out.print("Введите имя: ");
        name = scanner.nextLine();
        System.out.print("Введите возраст: ");
        age = scanner.nextInt();
    }

    public void display() {
        System.out.println("Имя: " + name + ", Возраст: " + age);
    }
}

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

class List<T> {
    private Node<T> head;

    public List() {
        this.head = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void delete(String name) {
        if (head == null) {
            return;
        }
        if (head.data instanceof Student && ((Student) head.data).name.equals(name)) {
            head = head.next;
            return;
        }
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data instanceof Student && ((Student) current.next.data).name.equals(name)) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    public void display() {
        if (head == null) {
            System.out.println("Картотека пуста.");
            return;
        }
        Node<T> current = head;
        while (current != null) {
            if (current.data instanceof Student) {
                ((Student) current.data).display();
            }
            current = current.next;
        }
    }

    public void clear() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }


}
