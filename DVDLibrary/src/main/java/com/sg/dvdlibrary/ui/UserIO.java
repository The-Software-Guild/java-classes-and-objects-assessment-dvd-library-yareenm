package com.sg.dvdlibrary.ui;

public interface UserIO {

    void print(String prompt);

    String readString(String prompt);

    int readInt(String prompt);
    int readInt(String prompt, int min, int max);


}
