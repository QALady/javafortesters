package com.javafortesters.tests;

import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.javafortesters.tests.TestBase.generateRandomString;

/**
 * Created by QA_Lady on 3/05/2015.
 */
public class GroupDataGenerator {
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

        List<GroupData> groups = generateRandomGroups(amount);
        if ("csv".equals(format)) {
            saveGroupsToCsvFile(groups, file);
        } else if ("xml".equals(format)) {
            saveGroupsToXmlFile(groups, file);
        } else {
            System.out.println("Unknown Format " + format);
            return;
        }
    }

    private static void saveGroupsToCsvFile(List<GroupData> groups, File file) throws IOException {
//        try {
        FileWriter writer = new FileWriter(file);
        for (GroupData group : groups) {
            writer.write(group.getGroupName() + "," + group.getHeader() + "," + group.getFooter() + ",|" + "\n");
        }
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
        writer.close();
    }

    protected static List<GroupData> loadGroupsFromCsvFile(File file) throws IOException {
        List<GroupData> list = new ArrayList<GroupData>();
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] part = line.split(",");
            GroupData group = new GroupData();
            group.setGroupName(part[0]);
            group.setHeader(part[1]);
            group.setFooter(part[2]);
            //
            line = bufferedReader.readLine();
            list.add(group);
        }
        bufferedReader.close();
        return list;

    }

    private static void saveGroupsToXmlFile(List<GroupData> groups, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("group", GroupData.class);
        String xml = xStream.toXML(groups);
        FileWriter writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    protected static List<GroupData> loadGroupsFromXmlFile(File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("group", GroupData.class);
        return (List<GroupData>) xStream.fromXML(file);

    }

    protected static List<GroupData> generateRandomGroups(int amount) {
        List<GroupData> list = new ArrayList<GroupData>();
        for (int i = 0; i < amount; i++) {
            GroupData group = new GroupData();
            group.setGroupName(generateRandomString("Group"));
            group.setHeader(generateRandomString("header"));
            group.setFooter(generateRandomString("footer"));
            list.add(group);
        }
        return list;
    }

}
