package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.*;
import com.sg.dvdlibrary.dto.DVDLibrary;
import com.sg.dvdlibrary.ui.*;

import java.util.List;

public class DVDLibraryController {

    private UserIO io = new UserIOConsoleImpl();
    private DVDLibraryView crv;
    private DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryView crv, DVDLibraryDao dao){
        this.crv = crv;
        this.dao=dao;
    }

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

    private int getMenuSelection(){
        return crv.printMenuAndGetSelection();
    }

    private void addDVD() throws DVDLibraryDaoException{
        crv.displayAddDVDBanner();
        DVDLibrary newDVD = crv.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        crv.displayAddDVDSuccessBanner();
    }

    private void listAllDVDs() throws DVDLibraryDaoException{
        crv.displayAllDVDsBanner();
        List<DVDLibrary> dvdList = dao.getAllDVDs();
        crv.displayDVDList(dvdList);
    }

    private void viewDVDByTitle()throws DVDLibraryDaoException{
        crv.displayDVDBanner();
        String dvdTitle = crv.getDVDTitleChoice();
        DVDLibrary dvd = dao.getDVDByTitle(dvdTitle);
        crv.displayDVD(dvd);
    }//bytitle

    private int getEditMenuSelection(){
        return crv.printEditMenuAndGetSelection();
    }

    private void removeDVD()throws DVDLibraryDaoException{
        crv.displayRemoveDVDBanner();
        String dvdTitle = crv.getDVDTitleChoice();
        DVDLibrary removedDVD = dao.removeDVD(dvdTitle);
        crv.displayRemoveResult(removedDVD);
    }


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
                        editUserRating(title);
                        break;
                    case 5:
                        editStudioName(title);
                        break;
                    case 6:
                        editUserNote(title);
                        break;
                    case 7:
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

    public int getEditMenu(){
        return crv.printEditMenuAndGetSelection();
    }

    private void editReleaseDate(String title) throws DVDLibraryDaoException {
        crv.displayEditReleaseDateBanner();
        String newReleaseDate = crv.getNewReleaseDate();
        dao.editReleaseDate(title, newReleaseDate);
        crv.displayEditDvdSuccess();
    }
    private void editMPAA(String title) throws DVDLibraryDaoException {
        crv.displayEditMpaaBanner();
        String newMpaaRating = crv.getNewMpaaRating();
        dao.editMPAA(title, newMpaaRating);
        crv.displayEditDvdSuccess();
    }
    private void editDirectorName(String title) throws DVDLibraryDaoException {
        crv.displayEditDirectorNameBanner();
        String newDirectorName = crv.getNewDirectorName();
        dao.editDirectorName(title, newDirectorName);
        crv.displayEditDvdSuccess();
    }
    private void editUserRating(String title) throws DVDLibraryDaoException {
        crv.displayEditUserRating();
        String newUserRating = crv.getNewUserRating();
        dao.editUserRating(title, newUserRating);
        crv.displayEditDvdSuccess();
    }
    private void editStudioName(String title) throws DVDLibraryDaoException {
        crv.displayEditStudio();
        String newStudioName = crv.getNewStudio();
        dao.editStudio(title, newStudioName);
        crv.displayEditDvdSuccess();
    }

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
