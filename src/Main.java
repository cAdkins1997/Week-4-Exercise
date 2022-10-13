
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<Integer> fahreinheit_list = new ArrayList<Integer>();
        ArrayList<Integer> celsius_list = new ArrayList<Integer>();
        Scanner temperatureScanner = new Scanner(Paths.get("farheinheit.txt"), StandardCharsets.UTF_8);

        /*while loop that grabs the fahrenheit values from the farheinheit.txt and adds them to both ArrayLists
        while also doing the math to convert the fahrenheit values to celsius
         */
        int currentTemp;
        while (temperatureScanner.hasNextInt()){
            currentTemp = temperatureScanner.nextInt();
            fahreinheit_list.add(currentTemp);
            celsius_list.add(fahreinheitToCelsius(currentTemp));
        }
        temperatureScanner.close();

        // Try block for catching file handling errors
        try {
            // manages writing the two temperature array lists into a file
            FileWriter writer = new FileWriter("temperature_conversion_table.txt");
            writer.write("Fahrenheit\tCelsius\n");
            for (int val1 = 0, val2 = 0; val1 < fahreinheit_list.size(); val1++, val2++) {
                writer.write(fahreinheit_list.get(val1) + "\t\t\t");
                writer.write(celsius_list.get(val2) + "\n");
            }
            writer.close();

            Scanner hashMapScanner = new Scanner(Paths.get("temperature_conversion_table.txt"), StandardCharsets.UTF_8);
            hashMapScanner.nextLine(); //this is used to skip the two strings at the start of the file
            HashMap<Integer, Integer> conversionTableHashMap = new HashMap<>();
            //this populates the hashmap with the actual temperatures
            while (hashMapScanner.hasNextInt()) {
                conversionTableHashMap.put(hashMapScanner.nextInt(), hashMapScanner.nextInt());
            }
            //outputs the hashmap's contents to the screen
            System.out.println("Fahrenheit\tCelsius");
            for (Map.Entry<Integer, Integer> set : conversionTableHashMap.entrySet()){
                System.out.println(set.getKey() + "\t\t\t" + set.getValue());
            }
            hashMapScanner.close();
        } catch (IOException e) {
            System.out.println("An error has occurred. File writing may have failed.");
        }

    }
    static int fahreinheitToCelsius(int farheinheit){
        return (farheinheit - 32) * 5/9;
    }
}