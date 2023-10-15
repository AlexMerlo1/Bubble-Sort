import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;


public class BubbleSort {
    //Create the Random Array
    public static int[] createRandomArray(int arrayLength){
        int[] randomNumbers = new int[arrayLength];
        Random random = new Random();

        for (int i = 0; i < arrayLength; i++) {
            randomNumbers[i] = random.nextInt(0,101);
        }
        return randomNumbers;
    }

    //Create BubbleSort Function
    public static void bubbleSort(int[] array) {
        int temp;

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap elements 
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void writeArrayToFile(int[] array, String filename){
        try {
            File myObj = new File(filename);
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } 
        else {
        System.out.println("File already exists.");
            }
        } 
        catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(filename);
            for (int i = 0; i <array.length; i++) {
                myWriter.write(Integer.toString(array[i]) + "\n");
            }
            myWriter.close();
            System.out.println("File has successfully been written.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
          }
        }
    public static int[] readFileToArray(String filename){
        ArrayList<Integer> lines = new ArrayList<>();

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
    
            while (myReader.hasNextLine()) {
                // Trim any leading/trailing white spaces
                String data = myReader.nextLine().trim(); 
    
                if (!data.isEmpty()) { 
                    // Check if the line is not empty
                    try {
                        int value = Integer.parseInt(data);
                        lines.add(value);
                    } 
                    catch (NumberFormatException e) {
                        System.out.println("Skipping non-integer value: " + data);
                    }
                }
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    
        int[] file_as_array = new int[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            file_as_array[i] = lines.get(i);
        }
        return file_as_array;
    }

    public static String file_name(Scanner file) {   
        //Prompt for File Name
        String file_name = file.nextLine();
        return file_name;
    }

    public static void main(String[] args){
        int[] randomInts = null;
        String file_name;
        
        //See if reading a file or if a file needs to be created
        Scanner scanner = new Scanner(System.in);
        String response = "";

        boolean valid_response = false;
        while (valid_response != true) {
            System.out.println("Do you want to read in a file as an array? (yes or no)");
            response = scanner.nextLine().toLowerCase();

            if (response.toLowerCase().equals("yes")) {
                valid_response = true;
                System.out.println("What is the name of your file?");
                file_name = file_name(scanner);
                randomInts = readFileToArray(file_name);

            } 
            else if (response.toLowerCase().equals("no")) {
                valid_response = true;
                continue;
            }
            else {
                valid_response = false;
                System.out.println("Please enter a valid response");
            }
        }


        //Prompt for File Name
        System.out.print("Where would you like to save the results (Enter a file name): ");
        Scanner file_name_scan = new Scanner(System.in);
        String new_file_name = file_name_scan.nextLine();
        
        //Prompt for list size if one needs to be created
        if (response.toLowerCase().equals("no")) {
            System.out.print("Enter a list size: ");
            Scanner scanner2 = new Scanner(System.in);
            int arr_length = scanner2.nextInt();
            // Create a list of random numbers
            randomInts = createRandomArray(arr_length);
            // Sort the list of random numbers
            bubbleSort(randomInts);
            writeArrayToFile(randomInts, new_file_name);
            scanner2.close();
        } 
        else {
            // Sort the list of random numbers
            bubbleSort(randomInts);
        
            // Write list to file
            writeArrayToFile(randomInts, new_file_name);
        }
        scanner.close();
        file_name_scan.close();      

    }

}
