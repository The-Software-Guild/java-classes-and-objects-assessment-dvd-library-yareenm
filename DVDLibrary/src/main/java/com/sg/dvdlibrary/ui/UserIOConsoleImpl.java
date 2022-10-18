package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVDLibrary;

import java.util.Scanner;

//This class implements UserIO interface methods.
public class UserIOConsoleImpl implements UserIO{

    final private Scanner inputSc = new Scanner(System.in);
    @Override
    public void print(String prompt) {
        System.out.println(prompt);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return inputSc.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        boolean invalidInput = true;
        int num = 0;

        while (invalidInput) {
            try {
                String stringValue = this.readString(prompt);
                num = Integer.parseInt(stringValue);
                invalidInput = false;
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int result;
        do {
            result = readInt(prompt);
        } while (result < min || result > max);

        return result;
    }


}
