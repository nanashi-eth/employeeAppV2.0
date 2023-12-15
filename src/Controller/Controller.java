package Controller;

import Model.*;
import View.CustomFileChooser;
import View.View;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller implements CalculoFechas {
    private View view;
    private DoublyLinkedList<Empleado> employeeDoublyLinkedList;
    private List<Empleado> employeeArrayList;
    private Empleado currentEmployee;

    public Controller(DoublyLinkedList<Empleado> employeeDoublyLinkedList, List<Empleado> employeeArrayList) {
        this.employeeDoublyLinkedList = employeeDoublyLinkedList;
        this.employeeArrayList = employeeArrayList;
    }
    
    public Controller(View view){
        this.view = view;
        this.employeeDoublyLinkedList = new DoublyLinkedList<>();
        this.employeeArrayList = new ArrayList<Empleado>();
    }

    // Method that performs the described actions
    public void sortEmployees() {
        // Step 7.1: Create employees
        employeeArrayList = createEmployees(10000); // You can adjust the number of employees

        employeeDoublyLinkedList.clear();
        for (int i = 0; i < employeeArrayList.size(); i++) {
            employeeDoublyLinkedList.add(i, employeeArrayList.get(i));
        }

        // Step 7.3: Measure the sorting time
        long startTimeLinkedList = System.currentTimeMillis();
        employeeDoublyLinkedList.quicksort(0, employeeDoublyLinkedList.getSize() - 1);
        long endTimeLinkedList = System.currentTimeMillis();
        long sortingTimeLinkedList = endTimeLinkedList - startTimeLinkedList;

        long startTimeCollection = System.currentTimeMillis();
        Collections.sort(employeeArrayList, Comparator.comparingInt(Empleado::getNumber));
        long endTimeCollection = System.currentTimeMillis();
        long sortingTimeCollection = endTimeCollection - startTimeCollection;

        // Step 7.4: Show times and the first 100 employees on the console
        showTimesAndEmployees(sortingTimeLinkedList, sortingTimeCollection, employeeArrayList.subList(0, Math.min(100, employeeArrayList.size())));
    }

    // Method to create employees randomly
    public List<Empleado> createEmployees(int count) {
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
                // Limpia la lista existente antes de agregar la lista cargada
                employeeDoublyLinkedList.clear();
                employeeArrayList.clear();

                // Lee la lista completa desde el archivo y agrégala a las listas existentes
                List<Empleado> listaCargada = (List<Empleado>) ois.readObject();
                employeeDoublyLinkedList = new DoublyLinkedList<>(listaCargada);
                employeeArrayList.addAll(listaCargada);

                view.updateJList();
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
                // Escribe la lista completa en el archivo
                oos.writeObject(employeeArrayList);
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

    // Método para modificar un empleado en las listas
    public void modifyEmployee(int index, Empleado newEmployee) {
        if (index >= 0 && index < employeeDoublyLinkedList.getSize()) {
            employeeDoublyLinkedList.set(index, newEmployee);
            employeeArrayList.set(index, newEmployee);
            if (currentEmployee != null && currentEmployee.equals(employeeDoublyLinkedList.get(index))) {
                currentEmployee = newEmployee; // Actualizar el empleado actual si se está modificando
            }
        }
    }

    // Método para borrar un empleado de las listas
    public void deleteEmployee(int index) {
        if (index >= 0 && index < employeeDoublyLinkedList.getSize()) {
            if (currentEmployee != null && currentEmployee.equals(employeeDoublyLinkedList.get(index))) {
                currentEmployee = null; // Limpiar el empleado actual si se está eliminando
            }
            employeeDoublyLinkedList.remove(index);
            employeeArrayList.remove(index);
        }
    }

    // Método para guardar el empleado actual
    public void saveCurrentEmployee() {
        if (currentEmployee != null) {
            // Si hay un empleado actual, guárdalo en la lista
            saveEmployee(currentEmployee, currentEmployee.getNumber());
        }
    }


    // Método para obtener un array a partir de la DoublyLinkedList
    public Empleado[] toArray() {
        ArrayList<Empleado> employeeArrayList = new ArrayList<>(employeeDoublyLinkedList.getSize());

        // Copiar los elementos de la DoublyLinkedList al ArrayList
        for (int i = 0; i < employeeDoublyLinkedList.getSize(); i++) {
            employeeArrayList.add(employeeDoublyLinkedList.get(i));
        }

        // Convertir el ArrayList a un array y devolverlo
        return employeeArrayList.toArray(new Empleado[0]);
    }


    // Método para crear un empleado a partir de un array de datos
    public void createEmpleadoFromData(String[] data) {
        if (data.length < 7) {
            // El array debe tener al menos 7 elementos para representar todos los atributos de un empleado
            return;
        }

        String type = data[0];
        String name = data[1];
        String date = data[2];
        String salary = data[3];
        String maxSalary = data[4];
        String department = data[5];
        String special1 = data[6];
        String special2 = data.length > 7 ? data[7] : ""; // Puede que no haya un segundo campo especial

        // Crear una instancia de Analista o Programador según el tipo
        Empleado empleado = null;
        int number = generateEmployeeNumber(); // Implementa este método para generar un número único para cada empleado

        switch (type) {
            case "Analista":
                empleado = new Analista(name, Double.parseDouble(salary), Double.parseDouble(maxSalary), parseDate(date), special2, number);
                ((Analista) empleado).setPlusAnual(Double.parseDouble(special1));
                break;
            case "Programador":
                empleado = new Programador(name, Double.parseDouble(salary), Double.parseDouble(maxSalary), parseDate(date), special2, number);
                ((Programador) empleado).setSueldoExtraMensual(Double.parseDouble(special1));
                break;
            // Agregar más casos según sea necesario
        }

        setCurrentEmployee(empleado);
    }

    // Método para obtener un array de String a partir de un objeto Empleado
    public String[] extractDataFromEmpleado(Empleado empleado) {
        String[] data = new String[8]; // Array para contener los datos del empleado

        // Común a ambos tipos de empleados
        data[0] = null;
        data[1] = empleado.getNombre();
        data[2] = formatDate(empleado.getFechaAlta());
        data[3] = String.valueOf(empleado.getSueldo());
        data[4] = String.valueOf(empleado.getSueldoMaximo());
        data[5] = "Informatica";

        // Según el tipo de empleado, establece los campos específicos
        if (empleado instanceof Analista) {
            Analista analista = (Analista) empleado;
            data[6] = String.valueOf(analista.getPlusAnual());
            data[7] = analista.getTipoAnalisis();
        } else if (empleado instanceof Programador) {
            Programador programador = (Programador) empleado;
            data[6] = String.valueOf(programador.getSueldoExtraMensual());
            data[7] = programador.getLenguajePrincipal();
        }

        return data;
    }

    // Método auxiliar para formatear la fecha (puedes ajustarlo según tu implementación)
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    private int generateEmployeeNumber() {
        // El número del próximo empleado será el tamaño actual (índice del próximo elemento)
        return employeeDoublyLinkedList.getSize();
    }

    // Método para parsear la fecha desde un String con el formato "dd/mm/aaaa"
    public Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejar la excepción apropiadamente en tu aplicación
            return null;
        }
    }

    // Método para establecer el empleado actual
    public void setCurrentEmployee(Empleado employee) {
        this.currentEmployee = employee;
    }

    // Método para obtener el empleado actual
    public Empleado getCurrentEmployee() {
        return currentEmployee;
    }

    // Método para obtener el siguiente empleado al empleado actual
    public Empleado getNextEmployee() {
        if (currentEmployee != null) {
            int currentIndex = employeeDoublyLinkedList.getIndexByItem(currentEmployee);
            int nextIndex = currentIndex + 1;

            if (nextIndex < employeeDoublyLinkedList.getSize()) {
                return employeeDoublyLinkedList.get(nextIndex);
            }
        }
        return null;
    }

    // Método para obtener el empleado anterior al empleado actual
    public Empleado getPreviousEmployee() {
        if (currentEmployee != null) {
            int currentIndex = employeeDoublyLinkedList.getIndexByItem(currentEmployee);
            int previousIndex = currentIndex - 1;

            if (previousIndex >= 0) {
                return employeeDoublyLinkedList.get(previousIndex);
            }
        }
        return null;
    }

    // Método para obtener el primer empleado en la lista
    public Empleado getFirstEmployee() {
        if (employeeDoublyLinkedList.getSize() > 0) {
            return employeeDoublyLinkedList.get(0);
        }
        return null;
    }

    // Método para obtener el último empleado en la lista
    public Empleado getLastEmployee() {
        int lastIndex = employeeDoublyLinkedList.getSize() - 1;
        if (lastIndex >= 0) {
            return employeeDoublyLinkedList.get(lastIndex);
        }
        return null;
    }
}
