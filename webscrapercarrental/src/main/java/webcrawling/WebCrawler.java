package webcrawling;

import org.openqa.selenium.WebElement;

import java.io.*;

import java.util.*;
/**
* WebCrawler class for finding hyperlinks and writing content to files.
*/
public class WebCrawler {

	/**
     * Finds hyperlinks from a list of WebElements.
     *
     * @param lnks List of WebElements
     * @return List of URLs
     */
    public static List<String> find_Hyper_Links(List<WebElement> lnks) {
        List<String> list_Of_URL = new ArrayList<String>();
        for (WebElement Elements : lnks) {
  
            if (Elements.equals(null))
                continue;
            else {

                list_Of_URL.add(Elements.getAttribute("href"));
            }
        }
       
        list_Of_URL.remove(null);
        return (list_Of_URL);
    }
    /**
     * Writes content to a file in the specified folder.
     *
     * @param folderName   Name of the folder
     * @param content      Content to be written
     * @param fileName     Name of the file
     * @param fileExtension File extension (e.g., ".html")
     */
    public static void content_Write(String nameOf_Folder, String contentt, String file_Name, String extensionn) {
        try {
            File check_Folder = new File(nameOf_Folder);
            File ff = new File(nameOf_Folder + file_Name + extensionn);
            if (!check_Folder.exists()) {
               
                boolean creatted = check_Folder.mkdirs(); 
                if (creatted) {
                  
                    FileWriter fiWriter = new FileWriter(ff, false);
                    fiWriter.write(contentt);
                    fiWriter.close();
                } else {
                    System.out.println("Failed to create the folder.");
                }
            } else {
              
                FileWriter ffWriter = new FileWriter(ff, false);
                ffWriter.write(contentt);
                ffWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurring in file");
        }
    }
    /**
     * Creates a file, writes content to it, and returns a Hashtable containing file information.
     *
     * @param url      URL associated with the file
     * @param content  Content to be written
     * @param fileName Name of the file
     * @param folder   Folder in which the file is created
     * @return Hashtable containing file information
     */
    
    public static Hashtable<String, String> createF_ile(String url, String cnt, String name_OfFile,String folderr) {
        Hashtable<String, String> mapOfURL = new Hashtable<String, String>();
        mapOfURL.put(name_OfFile + ".html", url);
        content_Write(folderr, cnt, name_OfFile, ".html");
        return mapOfURL;
    }
}
