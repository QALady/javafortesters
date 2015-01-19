package com.javafortesters.tests;

public class GroupData {
    private String groupName;
    private String header;
    private String footer;

    public GroupData(String groupName) {
        this.groupName = groupName;
    }

    public GroupData(String groupName, String header, String footer) {
        this.groupName = groupName;
        this.header = header;
        this.footer = footer;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
