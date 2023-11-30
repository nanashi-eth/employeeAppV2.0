package Controller;

import Model.*;
import View.CustomFileChooser;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Controller implements CalculoFechas {
    private DoublyLinkedList<Empleado> employeeDoublyLinkedList;
    private List<Empleado> employeeArrayList;

    public Controller(DoublyLinkedList<Empleado> employeeDoublyLinkedList, List<Empleado> employeeArrayList) {
        this.employeeDoublyLinkedList = employeeDoublyLinkedList;
        this.employeeArrayList = employeeArrayList;
    }

    // Method that performs the described actions
    public static void sortEmployees() {
        // Step 7.1: Create employees
        List<Empleado> employees = createEmployees(10000); // You can adjust the number of employees

        // Step 7.2: Insert employees into the linked list and a collection
        DoublyLinkedList<Empleado> linkedList = new DoublyLinkedList<>();
        List<Empleado> collectionList = new ArrayList<>(employees); // Using ArrayList as an example

        for (int i = 0; i < employees.size(); i++) {
            linkedList.add(i, employees.get(i));
        }

        // Step 7.3: Measure the sorting time
        long startTimeLinkedList = System.currentTimeMillis();
        linkedList.quicksort(0, linkedList.getSize() - 1);
        long endTimeLinkedList = System.currentTimeMillis();
        long sortingTimeLinkedList = endTimeLinkedList - startTimeLinkedList;

        long startTimeCollection = System.currentTimeMillis();
        Collections.sort(collectionList, Comparator.comparingInt(Empleado::getNumber));
        long endTimeCollection = System.currentTimeMillis();
        long sortingTimeCollection = endTimeCollection - startTimeCollection;

        // Step 7.4: Show times and the first 100 employees on the console
        showTimesAndEmployees(sortingTimeLinkedList, sortingTimeCollection, employees.subList(0, Math.min(100, employees.size())));
    }

    // Method to create employees randomly
    public static List<Empleado> createEmployees(int count) {
        List<Empleado> employees = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = "Employee" + i;
            double salary = Math.random() * 1000; // Random salary between 0 and 1000
            double maxSalary = salary + Math.random() * 500; // Random max salary between salary and salary + 500
            Date hireDate = new Date(); // Current date

            if (Math.random() < 0.5) {
                // Create Programador
                double sueldoExtra = Math.random() * 200; // Random sueldoExtra between 0 and 200
                String lenguajePrincipal = "Java"; // You can modify this as needed

                Programador programador = new Programador(name, salary, maxSalary, hireDate, lenguajePrincipal, ((int) (Math.random() * 500)));
                employees.add(programador);
            } else {
                // Create Analista
                double plusAnual = Math.random() * 100; // Random plusAnual between 0 and 100
                String tipoAnalisis = "Seguridad"; // You can modify this as needed

                Analista analista = new Analista(name, salary, maxSalary, hireDate, tipoAnalisis, ((int) (Math.random() * 500)));
                employees.add(analista);
            }
        }
        return employees;
    }

    // Method to show times and employees in a JDialog and on the console
    public static void showTimesAndEmployees(long timeLinkedList, long timeCollection, List<Empleado> firstEmployees) {
        String message = "Sorting times:\n\n";
        message += "Sorting time in linked list: " + timeLinkedList + " ms\n";
        message += "Sorting time in collection: " + timeCollection + " ms\n\n";
        String console ="First 100 employees:\n";

        for (Empleado employee : firstEmployees) {
            console += employee.getNombre() + ", Salary: " + employee.getSueldo() + "\n";
        }

        // Show in a JDialog
        JDialog dialog = new JDialog();
        JOptionPane.showMessageDialog(dialog, message);

        // Show in the console
        System.out.println(console);
    }
    public boolean fulfillsMonthAnalyst(Analista analyst, int numberOfMonths) {
        return cumpleMes(analyst.getFechaAlta());
    }
    public boolean fulfillsYearProgrammer(Programador programmer, int numberOfYears) {
        return cumpleMes(programmer.getFechaAlta());
    }


    // Método para cargar datos desde un archivo
    public void cargarDatosDesdeArchivo() {
        CustomFileChooser fileChooser = new CustomFileChooser();
        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == CustomFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                // Lee la lista desde el archivo y agrégala a la lista existente
                // Lee la lista desde el archivo y agrégala a la lista enlazada existente
                DoublyLinkedList<Empleado> listaCargada = (DoublyLinkedList<Empleado>) ois.readObject();
                // Limpia la lista existente antes de agregar la lista cargada
                employeeDoublyLinkedList.clear();
                employeeDoublyLinkedList = new DoublyLinkedList<>(listaCargada);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para guardar datos en un archivo
    public void guardarDatosEnArchivo() {
        CustomFileChooser fileChooser = new CustomFileChooser();
        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == CustomFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
                // Escribe la lista en el archivo
                oos.writeObject(employeeDoublyLinkedList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void saveEmployee(Empleado employee, int number) {
        employeeDoublyLinkedList.add(number, employee);
    }


    public DoublyLinkedList<Empleado> getEmployeeDoublyLinkedList() {
        return employeeDoublyLinkedList;
    }

    public List<Empleado> getEmployeeArrayList() {
        return employeeArrayList;
    }
}
