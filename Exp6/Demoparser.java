import java.io.*;
import java.util.Scanner;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Demoparser {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parse the student.xml file
        Document doc = builder.parse(new File("student.xml"));

        NodeList list = doc.getElementsByTagName("student");
        Scanner in = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        int n = in.nextInt();
        int flag = 0;

        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                int x = Integer.parseInt(e.getElementsByTagName("studentid").item(0).getTextContent());

                if (x == n) {
                    System.out.println("\n\nSTUDENT DETAILS");
                    System.out.println("===================");
                    System.out.println("Student ID: " + e.getElementsByTagName("studentid").item(0).getTextContent());
                    System.out.println("Student Name: " + e.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Address: " + e.getElementsByTagName("address").item(0).getTextContent());
                    System.out.println("Gender: " + e.getElementsByTagName("gender").item(0).getTextContent());
                    flag = 1;
                    break;
                }
            }
        }

        if (flag == 0) {
            System.out.println("Student ID is not present. Try Again!");
        }

        in.close();
    }
}
