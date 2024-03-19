package htmlparser;


import model.Car_info;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

import org.jsoup.select.*;

import java.io.*;

import java.util.*;
import java.util.regex.*;


public class CarRentalParser {

    public static List<Car_info> fetchAll_CarRental_Deals(){
        String website1_Path = "CarRentalFiles/orbitz_deals.html";
        File folderrr = new File(website1_Path);
        // parseing the website
        return parse_CarRental_Website(website1_Path);
    }
    public static void main(String[] args) {
        // Replace these paths with the actual paths of your car rental HTML files
        String website1Path = "CarRentalFiles";
        File folder = new File(website1Path);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".html"));
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String documentName = file.getName();
                    }
                }
            }
        }

        // Parse and extract car information from both websites
        List<Car_info> carInfoList1 = parse_CarRental_Website(website1Path);
    }

    
    //calling parsing website methd

    private static List<Car_info> parse_CarRental_Website(String file_Path) {
        List<Car_info> car_Info_List = new ArrayList<>();

        try {
            // Parse local HTML file
            File inputt = new File(file_Path);
            Document documentt = Jsoup.parse(inputt, "UTF-8");

            // Example: Extract car name, price, passenger capacity, etc.
            Elements car_Elements = documentt.select("li.offer-card-desktop");

            // for loop for all elements
            for (Element car_Element : car_Elements) {


                String car_Name = car_Element.select("div.uitk-text.uitk-type-300.uitk-text-default-theme.uitk-spacing.uitk-spacing-margin-blockend-one").text().split(" or ")[0]
                        .trim();
                if (car_Name.contains("Economy Special") || car_Name.contains("Special")) {
                    continue;
                }
//               // car price
                double car_Price = Double.parseDouble(car_Element.select("span.total-price").first().text().replaceAll("[^\\d.]", ""));
//                
                int passenger_Capacity = Integer.parseInt(fetch_Int(car_Element.select("span.uitk-spacing.text-attribute.uitk-spacing-padding-inlinestart-two.uitk-spacing-padding-inlineend-three").first().text()));
                String car_Group = car_Element.select("h3.uitk-heading.uitk-heading-5.uitk-spacing.uitk-spacing-padding-inline-three.uitk-layout-grid-item").text();
//           
                String transmission_Type = car_Element.select("span.uitk-spacing.text-attribute.uitk-spacing-padding-inlinestart-two.uitk-spacing-padding-inlineend-three").text().split(" ")[1];

               // give value
                int large_Bag = new Random().nextInt(6) + 1;
                int small_Bag = new Random().nextInt(6) + 1;


                // Create a CarInfo object and add it to the list
                Car_info carInfo = new Car_info(car_Name, car_Price, passenger_Capacity, car_Group, transmission_Type, large_Bag, small_Bag, "CarRental");
                // add record
                car_Info_List.add(carInfo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return car_Info_List;
    }
// pattern matter based on string
    public static String fetch_Int(String sstring) {
        Pattern patternn = Pattern.compile("\\d+");
        Matcher matcherr = patternn.matcher(sstring);
        if (matcherr.find()) {
            return matcherr.group();
        }
        return sstring;
    }

}
