package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVDLibrary;

import java.util.List;

// This class is responsible for interact with the user.
public class DVDLibraryView {
    // declare variables
    private UserIO io;

    //This is a constructor of DVDLibraryView
    public DVDLibraryView(UserIOConsoleImpl io) {
        this.io = io;
    }

    //This method displays a main menu to the user. User can type min 1 and max 6.
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. Add a new DVD");
        io.print("3. Search a DVD by entering title");
        io.print("4. Remove a DVD");
        io.print("5. Edit the information in a DVD");
        io.print("6. Exit the program");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    // This method displays information about a particular DVD.
    public void displayDVD(DVDLibrary dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(String.valueOf(dvd.getMpaaRating()));
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getNote());
            io.print("");
        } else {
            io.print("No such dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDVDBanner () {
        io.print("=== Display DVD ===");
    }

    public String getDVDTitleChoice(){
        return io.readString("Please enter the title of DVD");
    }

    //This method is for add a new DVD.
    //It is asking User about the new information to be added.
    public DVDLibrary getNewDVDInfo() {
        String title = io.readString("Please enter the title");
        String releaseDate = io.readString("Please enter release date");
        String mpaaRating = io.readString("Please enter the MPAA rating");
        String directorName = io.readString("Please enter the director name");
        String studio = io.readString("Please enter the studio");
        String note = io.readString("Please enter your note about dvd");

        DVDLibrary currentDVD = new DVDLibrary(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setNote(note);
        return currentDVD;
    }

    public void displayAddDVDBanner() {
        io.print("=== Add DVD ===");
    }

    public void displayAddDVDSuccessBanner() {
        io.readString("DVD successfully created.  Please hit enter to continue");
    }

    //This method displays information about all DVDs in the file.
    public void displayDVDList(List<DVDLibrary> dvdList) {
        for (DVDLibrary currentDVD : dvdList) {
            String dvdInfo = String.format("#%s : %s %s %s %s %s",
                    currentDVD.getTitle(),
                    currentDVD.getReleaseDate(),
                    currentDVD.getMpaaRating(),
                    currentDVD.getDirectorName(),
                    currentDVD.getStudio(),
                    currentDVD.getNote());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayAllDVDsBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayRemoveDVDBanner () {
        io.print("=== Remove DVD ===");
    }

    //This method displays result of removing a DVD. There are 2 options successfull or not.
    public void displayRemoveResult(DVDLibrary dvd){
        if(dvd != null){
            io.print("DVD successfully removed.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    public void displayEditDVDBanner () {
        io.print("=== Edit DVD ===");
    }

    //This method displays a menu to the User about editing options of DVD. User can type min 1 and max 6.
    public int printEditMenuAndGetSelection() {
        io.print("Edit Menu");
        io.print("1. Edit Release Date");
        io.print("2. Edit MPAA Rating");
        io.print("3. Edit Director Name");
        io.print("4. Edit Studio Name");
        io.print("5. Edit User Note");
        io.print("6. Exit the program");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    //This method displays result of editing a DVD. There are 2 options successfully or not.
    public void displayEditResult(DVDLibrary dvd){
        if(dvd != null){
            io.print("DVD successfully editted.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayEditReleaseDateBanner() {
        io.print("=== Edit DVD Release Date ===");
    }

    //This method displays result of successfull editing.
    public void displayEditDvdSuccess() {
        io.readString(
                "DVD successfully Edited.  Please hit enter to continue");
    }
    public void displayEditMpaaBanner() {
        io.print("=== Edit DVD MPAA rating ===");
    }

    public void displayEditDirectorNameBanner() {
        io.print("=== Edit DVD Director's Name ===");
    }

    public void displayEditStudio() {
        io.print("=== Edit DVD Studio ===");
    }

    public void displayEditNote(){
        io.print("=== Edit DVD User Note ===");
    }

    public String getNewReleaseDate() {
        return io.readString("Please enter new release date.");
    }

    public String getNewMpaaRating() {
        return io.readString("Please enter new MPAA rating.");
    }

    public String getNewDirectorName() {
        return io.readString("Please enter new director's name.");
    }

    public String getNewStudio() {
        return io.readString("Please enter new studio.");
    }

    public String getNewNote() {return io.readString("Please enter new note");}

}
