package com.sg.dvdlibrary.ui;

//This interface has some basic operations for user input.
public interface UserIO {

    void print(String prompt);

    String readString(String prompt);

    int readInt(String prompt);
    int readInt(String prompt, int min, int max);


}
