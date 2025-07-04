/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.batch_3;
import com.mycompany.batch_3.db.DbProcessor;
import com.mycompany.batch_3.Student;
import java.util.Scanner;

/**
 *
 * @author Awng Awng
 */
public class Batch_3 {
    public static void main(String args[]) { 
        Scanner scanner = new Scanner(System.in);
        DbProcessor db = new DbProcessor();
        
        //Student[] stdArr = new DbProcessor().getAllStudents(4);
         
//        Student[] stdArr = db.getAllStudents(10);
//        
//        for(Student std : stdArr) {
//            if(std != null){
//                System.out.println(std.toString());        
//            }
//        }
        
        int result;

        do {
            System.out.println("\n=== Batch_3 Student Management System ===");
            System.out.println("1. Create Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            result = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (result) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();   
                    
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    
                    scanner.nextLine();
                    System.out.print("Enter gender (M/F): ");
                    
                    char gender = scanner.nextLine().charAt(0);
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    
                    Student std = new Student(0, name, age, gender, address);
                    db.save(std);
                    System.out.print("Student saved successfully...!");
                    break;

                case 2:
                    System.out.print("Enter ID to Update: ");
                    int up_id = scanner.nextInt();    
                    scanner.nextLine();
                    
                    System.out.print("Enter new name: ");
                    String up_name = scanner.nextLine();
                    
                    System.out.print("Enter new age: ");
                    int up_age = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("Enter new gender (M/F): ");
                    char up_gender = scanner.nextLine().charAt(0);
               
                    
                    System.out.print("Enter new address: ");
                    String up_address = scanner.nextLine();                    
                    
                    Student updateStudent = new Student(up_id, up_name, up_age, up_gender, up_address);
                    db.update(updateStudent);
                    System.out.println("Student updated successfully...!");
                    break;
                      
                case 3:
                    System.out.println("Enter ID to Delete: ");
                    int de_id = scanner.nextInt();
                    db.delete(de_id);
                    System.out.println("Student has been deleted successfully...!");
                    break;

                case 4:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid result, TRY AGAIN...");
            }

        } while (result != 4);

        scanner.close();
     
    }
}
        

