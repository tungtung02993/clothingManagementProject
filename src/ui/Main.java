package ui;
import java.util.*;
import util.DataInput;
import CustomerService.CustomerService;
import CustomerService.CustomerServiceImpl;
import repository.Customerrepository;
import repository.Customerrepositoryimpl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Thanh Dat
 */
public class Main {
 public static void main(String[] args) {
        String customerDataFile = "customers.txt";
        customerManagement CustomerManagement;
        try {
            Customerrepository customerRepository = new Customerrepositoryimpl(customerDataFile);
            CustomerService customerService = new CustomerServiceImpl(customerRepository);
            CustomerManagement = new customerManagement(customerService);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }
 
        while (true) {
            System.out.println(String.join("", Collections.nCopies(50, "*")));
            System.out.println("1. Print customer list");
            System.out.println("2. Add new customer");
            System.out.println("3. Remove customer");
            System.out.println("4. Update customer");
            System.out.println("5. Find customer by id");
            System.out.println("6. Find customer by name");
            System.out.println("7. Find customer by type (Regular/VIP)");
            System.out.println("8. Check customer is exist");
            System.out.println("9. Print customer list from file");
            System.out.println("10.Save customers to file");
            System.out.println("11.Calculate discounted price");
            System.out.println("0. Exit");
            System.out.print("Please enter your choice: ");
            try {
                int choice = DataInput.getIntegerNumber();
                switch (choice) {
                    case 1:
                        CustomerManagement.printCustomerList();
                        System.out.println();
                        break;
                    case 2:
                        CustomerManagement.addCustomer();
                        break;
                    case 3:
                        CustomerManagement.removeCustomer();
                        break;
                    case 4:
                        CustomerManagement.updateCustomer();
                        break;
                    case 5:
                        CustomerManagement.findCustomerById();
                        break;
                    case 6:
                        CustomerManagement.findCustomerByName();
                        break;
                    case 7:
                        CustomerManagement.findCustomerByType();
                        break;
                    case 8:
                        CustomerManagement.checkCustomerIsExist();
                        break;
                    case 9:
                        CustomerManagement.printCustomerListFromFile();
                        break;
                    case 10:
                        CustomerManagement.saveCustomers();
                        break;
                    case 11:
                        CustomerManagement.calculateDiscountedPrice();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
