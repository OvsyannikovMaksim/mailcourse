package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class ItemRep {

    private static final int Amount = 100;

    public static List<Item> mData = new ArrayList<>();


    // конструктор класса
    protected ItemRep() {
        mData = initializeData(Amount);
    }
    public ItemRep(int num) {
        mData = initializeData(num);
    }


    // получить список элементов
    public List<Item> list() {
        return mData;
    }

    public int size() {
        return mData.size();
    }

    // получить элемент по индексу
    public Item item(int index) {
        return mData.get(index);
    }

    public int add(){
        int num = mData.size()+1;
        int colour = num%2 == 0 ? 0: 1;
        final Item item = new Item();
        item.name = "" + num;
        item.colour = colour;

        mData.add(item);
        return num;
    }

    // инициализация списка элементов по правилам: цвет четного красный(0), нечетного синий (1)
    public List<Item> initializeData(int Amount){
        final List<Item> data = new ArrayList<>();
        if (Amount==0) return data;

        for(int position = 1; position<=Amount; position++) {

            String name = "" + (position);
            final int colour;
            if (position%2==1) {
                colour = 1;
            } else {
                colour = 0;
            }

            final Item item = new Item();
            item.name = name;
            item.colour = colour;

            data.add(item);
        }
        return data;
    }
}
