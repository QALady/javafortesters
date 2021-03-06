package com.javafortesters.utils;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SortedListOf<T extends Comparable<T>> extends ForwardingList<T> {
    private static Logger LOG = LoggerFactory.getLogger(SortedListOf.class.getName());

    private List<T> list = Lists.newArrayList();

    public SortedListOf() {
    }

    public SortedListOf(SortedListOf<T> listToCopy) {
        list = Lists.newArrayList(listToCopy.list);
        Collections.sort(list);
    }

    public SortedListOf(List<T> listToCopy) {
        list = Lists.newArrayList(listToCopy);
        Collections.sort(list);
    }

    @Override
    protected List<T> delegate() {
        return list;
    }

    @Override
    public boolean add(T element) {
        boolean temp = super.add(element);
        Collections.sort(list);
        return temp;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean temp = super.addAll(collection);
        Collections.sort(list);
        return temp;
    }

    public SortedListOf<T> withAdded(T item) {
        SortedListOf<T> newItems = new SortedListOf<T>();
        newItems.list = Lists.newArrayList(this.list);
        newItems.list.add(item);
        Collections.sort(newItems.list);
        return newItems;
    }

    public SortedListOf<T> without(T item) {
        SortedListOf<T> newItems = new SortedListOf<T>();
        newItems.list = Lists.newArrayList(this.list);
        newItems.list.remove(item);
        return newItems;
    }

    public SortedListOf<T> without(int index) {
        SortedListOf<T> newItems = new SortedListOf<T>();
        newItems.list = Lists.newArrayList(this.list);
        newItems.list.remove(index);
        return newItems;
    }

    public T getSome() {
        if (size() == 0) {
            return null;
        } else {
            return list.get(new Random().nextInt(size()));
        }
    }
}