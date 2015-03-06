package com.javafortesters.tests;

import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.javafortesters.tests.TestBase.generateRandomString;

/**
 * Created by QA_Lady on 3/05/2015.
 */
public class ContactDataGenerator {
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Please specify parameters: <amount of test data>, <file>, <format>");
            return;
        }
        int amount = Integer.parseInt(args[0]);
        File file = new File(args[1]);
        String format = args[2];

        if (file.exists()) {
            System.out.println("File: \" " + file + " \" already exists. Please remove file manually.");
            return;
        }

        List<ContactsData> contacts = generateRandomContacts(amount);
        if ("csv".equals(format)) {
            saveContactsToCsvFile(contacts, file);
        } else if ("xml".equals(format)) {
            saveContactsToXmlFile(contacts, file);
        } else {
            System.out.println("Unknown Format " + format);
            return;
        }
    }

    private static void saveContactsToCsvFile(List<ContactsData> contacts, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (ContactsData contact : contacts) {
            writer.write(contact.getFirstname() + "," + contact.getLastname() + "," + contact.getAddress() + "," + contact.getHomeNumber() + "," + contact.getPhoneNumber() + "," + contact.getEmail() + "," + contact.getGroupID() + "," + contact.getDay() + "," + contact.getMonth() + "," + contact.getYear() + ",|" + "\n");
        }
        writer.close();
    }

    protected static List<ContactsData> loadContactsFromCsvFile(File file) throws IOException {
        List<ContactsData> list = new ArrayList<ContactsData>();
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] part = line.split(",");
            ContactsData contact = new ContactsData();
            contact.setFirstname(part[0]);
            contact.setLastname(part[1]);
            contact.setAddress(part[2]);
            contact.setHomeNumber(part[3]);
            contact.setPhoneNumber(part[4]);
            contact.setEmail(part[5]);
            contact.setGroupID(part[6]);
            contact.setDay(Integer.parseInt(part[7]));
            contact.setMonth(part[8]);
            contact.setYear(Integer.parseInt(part[9]));
            //
            line = bufferedReader.readLine();
            list.add(contact);
        }
        bufferedReader.close();
        return list;

    }

    private static void saveContactsToXmlFile(List<ContactsData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("contact", ContactsData.class);
        String xml = xStream.toXML(contacts);
        FileWriter writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    protected static List<ContactsData> loadContactsFromXmlFile(File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("contact", ContactsData.class);
        return (List<ContactsData>) xStream.fromXML(file);

    }

    protected static List<ContactsData> generateRandomContacts(int amount) {
        List<ContactsData> list = new ArrayList<ContactsData>();
        Random rnd = new Random();
        for (int i = 0; i < amount; i++) {
            ContactsData contact = new ContactsData();
            contact.setFirstname(generateRandomString("firstname"));
            contact.setLastname(generateRandomString("lastname"));
            contact.setAddress(generateRandomString("address"));
            contact.setHomeNumber(String.valueOf(rnd.nextInt(100)));
            Double d = rnd.nextDouble() * 100000000;
            String phonenumber = String.valueOf(d.longValue());
            contact.setPhoneNumber(phonenumber);
            contact.setEmail(generateRandomString("email") + "@" + ".com");
            contact.setGroupID(generateRandomString("Group"));
            contact.setDay(rnd.nextInt(29));
            contact.setMonth(ContactsData.Months.values()[rnd.nextInt(12)].name());
            contact.setYear(rnd.nextInt(2000));
            list.add(contact);
        }
        return list;
    }

}
