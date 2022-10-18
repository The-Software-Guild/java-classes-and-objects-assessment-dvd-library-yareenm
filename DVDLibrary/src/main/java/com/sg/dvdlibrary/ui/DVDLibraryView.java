package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVDLibrary;

import java.util.List;

public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIOConsoleImpl io) {
        this.io = io;
    }

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

    public int printEditMenuAndGetSelection() {
        io.print("Edit Menu");
        io.print("1. Edit Release Date");
        io.print("2. Edit MPAA Rating");
        io.print("3. Edit Director Name");
        io.print("4. Edit User Rating");
        io.print("5. Edit Studio Name");
        io.print("6. Edit User Note");
        io.print("7. Exit the program");

        return io.readInt("Please select from the above choices.", 1, 7);
    }
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

    public void displayEditUserRating() {
        io.print("=== Edit DVD User Rating ===");
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

    public String getNewUserRating() {
        return io.readString("Please enter new user rating.");
    }

    public String getNewStudio() {
        return io.readString("Please enter new studio.");
    }

    public String getNewNote() {return io.readString("Please enter new note");}

}
