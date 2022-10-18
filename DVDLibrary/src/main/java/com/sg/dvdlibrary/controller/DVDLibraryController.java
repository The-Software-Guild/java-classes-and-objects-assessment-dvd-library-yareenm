package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.*;
import com.sg.dvdlibrary.dto.DVDLibrary;
import com.sg.dvdlibrary.ui.*;

import java.util.List;

/*
  Methods in the Controller to orchestrate the DVDs operations.
*/
public class DVDLibraryController {
    // declare and initialize variables.
    private UserIO io = new UserIOConsoleImpl();
    private DVDLibraryView crv;
    private DVDLibraryDao dao;

    //This is the constructor of our DVDLibraryController class.
    public DVDLibraryController(DVDLibraryView crv, DVDLibraryDao dao){
        this.crv = crv;
        this.dao=dao;
    }


    //This method display a menu of our project. There are 7 options on the menu. It asks the user and gets the selection.
    public void run() {
        boolean keepGoing = true;
        int selection = 0;

        try {
            while (keepGoing) {

                selection = getMenuSelection();

                switch (selection) {
                    case 1:
                        listAllDVDs();
                        break;
                    case 2:
                        addDVD();
                        break;
                    case 3:
                        viewDVDByTitle();
                        break;
                    case 4:
                        removeDVD();
                        break;
                    case 5:
                        editDVD();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
        }catch (DVDLibraryDaoException e){
            crv.displayErrorMessage(e.getMessage());
        }
        exitMessage();
    }//run function

    //This method is for getting menu selection from the User.
    private int getMenuSelection(){
        return crv.printMenuAndGetSelection();
    }

    //This method is for adding a new DVD into the file. It gets new DVD information from the user and pass it to the dao object.
    private void addDVD() throws DVDLibraryDaoException{
        crv.displayAddDVDBanner();
        DVDLibrary newDVD = crv.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        crv.displayAddDVDSuccessBanner();
    }

    //This method is for displaying all DVDs in the file. It gets all the information and displays it.
    private void listAllDVDs() throws DVDLibraryDaoException{
        crv.displayAllDVDsBanner();
        List<DVDLibrary> dvdList = dao.getAllDVDs();
        crv.displayDVDList(dvdList);
    }

    //This method is for displaying a particular DVD by its title.
    private void viewDVDByTitle()throws DVDLibraryDaoException{
        crv.displayDVDBanner();
        String dvdTitle = crv.getDVDTitleChoice();
        DVDLibrary dvd = dao.getDVDByTitle(dvdTitle);
        crv.displayDVD(dvd);
    }

    //This method is for removing a DVD from the file. It gets the title, and pass it to the dao object.
    //dao object returns removed DVD information to show the result to the User. Successfully delete or not.
    private void removeDVD()throws DVDLibraryDaoException{
        crv.displayRemoveDVDBanner();
        String dvdTitle = crv.getDVDTitleChoice();
        DVDLibrary removedDVD = dao.removeDVD(dvdTitle);
        crv.displayRemoveResult(removedDVD);
    }

    //This method displays an edit menu for the user. There are 6 options. It asks the user and gets the selection.
    private void editDVD() throws DVDLibraryDaoException{
        crv.displayEditDVDBanner();
        String title = crv.getDVDTitleChoice();
        DVDLibrary currentDVD = dao.getDVDByTitle(title);
        if (currentDVD == null) {
            crv.displayEditResult(currentDVD);
        } else {
            crv.displayDVD(currentDVD);
            int editMenuSelection = 0;
            boolean keepGoing = true;
            while (keepGoing) {
                editMenuSelection = getEditMenu();

                switch (editMenuSelection) {
                    case 1:
                        editReleaseDate(title);
                        break;
                    case 2:
                        editMPAA(title);
                        break;
                    case 3:
                        editDirectorName(title);
                        break;
                    case 4:
                        editStudioName(title);
                        break;
                    case 5:
                        editUserNote(title);
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
                if (keepGoing == false) {
                    break;
                }
            }
        }

    }

    //This method is for getting edit menu selection from the User.
    public int getEditMenu(){
        return crv.printEditMenuAndGetSelection();
    }

    //This method is for edit the Release Date of the DVD in the file. It gets the new release date information,
    //and pass it to the dao object to update on the DVD object. Displays if it successfully edited or not.
    private void editReleaseDate(String title) throws DVDLibraryDaoException {
        crv.displayEditReleaseDateBanner();
        String newReleaseDate = crv.getNewReleaseDate();
        dao.editReleaseDate(title, newReleaseDate);
        crv.displayEditDvdSuccess();
    }
    //This method is for edit the MPAA Rating of the DVD in the file. It gets the new MPAA rating information,
    //and pass it to the dao object to update on the DVD object. Displays if it successfully edited or not.
    private void editMPAA(String title) throws DVDLibraryDaoException {
        crv.displayEditMpaaBanner();
        String newMpaaRating = crv.getNewMpaaRating();
        dao.editMPAA(title, newMpaaRating);
        crv.displayEditDvdSuccess();
    }
    //This method is for edit the Director Name of the DVD in the file. It gets the new director name information,
    //and pass it to the dao object to update on the DVD object. Displays if it successfully edited or not.
    private void editDirectorName(String title) throws DVDLibraryDaoException {
        crv.displayEditDirectorNameBanner();
        String newDirectorName = crv.getNewDirectorName();
        dao.editDirectorName(title, newDirectorName);
        crv.displayEditDvdSuccess();
    }

    //This method is for edit the Studio Name of the DVD in the file. It gets the new studio name information,
    //and pass it to the dao object to update on the DVD object. Displays if it successfully edited or not.
    private void editStudioName(String title) throws DVDLibraryDaoException {
        crv.displayEditStudio();
        String newStudioName = crv.getNewStudio();
        dao.editStudio(title, newStudioName);
        crv.displayEditDvdSuccess();
    }
    //This method is for edit the User Note of the DVD in the file. It gets the new note information,
    //and pass it to the dao object to update on the DVD object. Displays if it successfully edited or not.
    private void editUserNote(String title) throws DVDLibraryDaoException {
        crv.displayEditNote();
        String newUserNote = crv.getNewNote();
        dao.editNote(title, newUserNote);
        crv.displayEditDvdSuccess();
    }

    private void unknownCommand() {
        crv.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        crv.displayExitBanner();
    }

}
