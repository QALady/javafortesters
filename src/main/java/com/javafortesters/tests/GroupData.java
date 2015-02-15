package com.javafortesters.tests;

public class GroupData implements Comparable<GroupData> {
    private String groupName;
    private String header;
    private String footer;

    public GroupData(String groupName) {
        this.groupName = groupName;
    }

    public GroupData() {

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

    @Override
    public String toString() {
        return "GroupData [groupName=" + groupName + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (groupName != null ? !groupName.equals(groupData.groupName) : groupData.groupName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return groupName != null ? groupName.hashCode() : 0;
    }


    @Override
    public int compareTo(GroupData other) {
        return this.groupName.toLowerCase().compareTo(other.groupName.toLowerCase());
    }
}
