package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVDLibrary;

import java.io.*;
import java.util.*;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao{

    private Map<String, DVDLibrary> dvds = new HashMap<>();
    public static final String DVDLIBRARY_FILE = "dvdlibrary.txt";
    public static final String DELIMITER = "::";

    @Override
    public DVDLibrary addDVD(String title, DVDLibrary dvd) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary newDVD = dvds.put(title,dvd);
        try{
            writeDVD();
        }catch (Exception e ){
            throw new DVDLibraryDaoException("ERROR");
        }
        return newDVD;
    }

    @Override
    public List<DVDLibrary> getAllDVDs() throws DVDLibraryDaoException {
        loadDVD();
        return new ArrayList<DVDLibrary>(dvds.values());
    }

    @Override
    public DVDLibrary getDVDByTitle(String title) throws DVDLibraryDaoException {
        loadDVD();
        return dvds.get(title);
    }

    @Override
    public DVDLibrary removeDVD(String title) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary removedDVD = dvds.remove(title);
        try{
            writeDVD();
        }catch (Exception e ){
            throw new DVDLibraryDaoException("ERROR");
        }
        return removedDVD;
    }

    @Override
    public DVDLibrary editReleaseDate(String title, String newReleaseDate) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary currentDVD = dvds.get(title);
        currentDVD.setReleaseDate(newReleaseDate);
        try {
            writeDVD();
        }catch(Exception e){
            throw new DVDLibraryDaoException("ERROR");
        }
        return currentDVD;
    }

    @Override
    public DVDLibrary editMPAA(String title, String newMpaaRating) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary currentDVD = dvds.get(title);
        currentDVD.setMpaaRating(newMpaaRating);
        try {
            writeDVD();
        }catch(Exception e){
            throw new DVDLibraryDaoException("ERROR");
        }
        return currentDVD;
    }

    @Override
    public DVDLibrary editDirectorName(String title, String newDirectorName) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary currentDVD = dvds.get(title);
        currentDVD.setDirectorName(newDirectorName);
        try {
            writeDVD();
        }catch(Exception e){
            throw new DVDLibraryDaoException("ERROR");
        }
        return currentDVD;
    }

    @Override
    public DVDLibrary editUserRating(String title, String newUserRating) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary currentDVD = dvds.get(title);
        currentDVD.setMpaaRating(newUserRating);
        try {
            writeDVD();
        }catch(Exception e){
            throw new DVDLibraryDaoException("ERROR");
        }
        return currentDVD;
    }

    @Override
    public DVDLibrary editStudio(String title, String newStudioName) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary currentDVD = dvds.get(title);
        currentDVD.setStudio(newStudioName);
        try {
            writeDVD();
        }catch(Exception e){
            throw new DVDLibraryDaoException("ERROR");
        }
        return currentDVD;
    }

    @Override
    public DVDLibrary editNote(String title, String newUserNote) throws DVDLibraryDaoException {
        loadDVD();
        DVDLibrary currentDVD = dvds.get(title);
        currentDVD.setNote(newUserNote);
        try {
            writeDVD();
        }catch(Exception e){
            throw new DVDLibraryDaoException("ERROR");
        }
        return currentDVD;
    }

    public DVDLibrary unmarshallDVD(String dvdAsText){
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        String dvdTitle = dvdTokens[0];
        DVDLibrary dvdFromFile = new DVDLibrary(dvdTitle);

        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMpaaRating(dvdTokens[2]);
        dvdFromFile.setDirectorName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setNote(dvdTokens[5]);

        return dvdFromFile;
    }
    private void loadDVD() throws DVDLibraryDaoException {
        Scanner scanner;
        try {
            scanner = new Scanner( new BufferedReader( new FileReader(DVDLIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load roster data into memory.");
        }

        String currentLine;
        DVDLibrary currentDVD;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            dvds.put(currentDVD.getTitle(), currentDVD);

        }

        scanner.close();
    }

    private String marshallDVD(DVDLibrary dvd){
        String dvdAsText = dvd.getTitle() + DELIMITER;
        dvdAsText += dvd.getReleaseDate() + DELIMITER;
        dvdAsText += dvd.getMpaaRating() + DELIMITER;
        dvdAsText += dvd.getDirectorName() + DELIMITER;
        dvdAsText += dvd.getStudio() + DELIMITER;
        dvdAsText += dvd.getNote();

        return dvdAsText;
    }

    private void writeDVD() throws DVDLibraryDaoException{
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVDLIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save dvd data.");
        }

        String dvdAsText;
        List<DVDLibrary> dvdList = this.getAllDVDs();
        for (DVDLibrary currentDVD : dvdList) {

            dvdAsText = marshallDVD(currentDVD);
            out.println(dvdAsText);
            out.flush();

        }
        out.close();
    }
}
