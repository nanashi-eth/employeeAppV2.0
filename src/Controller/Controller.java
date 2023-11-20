package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller implements CalculoFechas {
    private DoublyLinkedList<Empleado> employeeDoublyLinkedList;
    private List<Empleado> employeeArrayList;

    public Controller(DoublyLinkedList<Empleado> employeeDoublyLinkedList, List<Empleado> employeeArrayList) {
        this.employeeDoublyLinkedList = employeeDoublyLinkedList;
        this.employeeArrayList = employeeArrayList;
    }

    // Method to create employees as mentioned
    public void createEmpleados() {
        // Implementation to create employees
    }

    // Method to measure the time taken to sort the DoublyLinkedList and ArrayList
    public void measureSortingTime() {
        DoublyLinkedList<Empleado> copyDoublyLinkedList = new DoublyLinkedList<>(employeeDoublyLinkedList);
        List<Empleado> copyArrayList = new ArrayList<>(employeeArrayList);

        long startDoublyLinkedListTime = System.currentTimeMillis();
        sortDoublyLinkedListByEmpleadoNumber(employeeDoublyLinkedList);
        long endDoublyLinkedListTime = System.currentTimeMillis();
        long doublyLinkedListSortingTime = endDoublyLinkedListTime - startDoublyLinkedListTime;

        long startArrayListTime = System.currentTimeMillis();
        sortArrayListByEmpleadoNumber(employeeArrayList);
        long endArrayListTime = System.currentTimeMillis();
        long arrayListSortingTime = endArrayListTime - startArrayListTime;

        System.out.println("Time taken to sort DoublyLinkedList: " + doublyLinkedListSortingTime + " milliseconds");
        System.out.println("Time taken to sort ArrayList: " + arrayListSortingTime + " milliseconds");
    }

    // Method to sort the DoublyLinkedList by employee number
    private void sortDoublyLinkedListByEmpleadoNumber(DoublyLinkedList<Empleado> doublyLinkedList) {
        // Implementation to sort the doubly linked list by employee number
    }

    // Method to sort an ArrayList by employee number
    private void sortArrayListByEmpleadoNumber(List<Empleado> arrayList) {
//        Collections.sort(arrayList, (e1, e2) -> Integer.compare(e1.getEmpleadoNumber(), e2.getEmpleadoNumber()));
    }
    public boolean fulfillsMonthAnalyst(Analista analyst, int numberOfMonths) {
        return cumpleMes(analyst.getFechaAlta());
    }
    public boolean fulfillsYearProgrammer(Programador programmer, int numberOfYears) {
        return cumpleMes(programmer.getFechaAlta());
    }
    
    
}
