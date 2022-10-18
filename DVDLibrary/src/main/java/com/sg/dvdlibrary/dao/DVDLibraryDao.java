package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVDLibrary;

import java.util.List;

//This interface includes some CRUD operations for our project. Create-Read-Update-Delete operations.
public interface DVDLibraryDao {

    DVDLibrary addDVD(String title, DVDLibrary dvd) throws DVDLibraryDaoException;

    List<DVDLibrary> getAllDVDs() throws DVDLibraryDaoException;

    DVDLibrary getDVDByTitle(String title) throws DVDLibraryDaoException;

    DVDLibrary removeDVD(String title) throws DVDLibraryDaoException;

    DVDLibrary editReleaseDate(String title, String newReleaseDate) throws DVDLibraryDaoException;

    DVDLibrary editMPAA(String title, String newMpaaRating) throws DVDLibraryDaoException;

    DVDLibrary editDirectorName(String title, String newDirectorName) throws DVDLibraryDaoException;

    DVDLibrary editStudio(String title, String newStudioName) throws DVDLibraryDaoException;

    DVDLibrary editNote(String title, String newStudioName) throws DVDLibraryDaoException;

}
