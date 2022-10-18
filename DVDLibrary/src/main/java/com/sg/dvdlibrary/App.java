package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DVDLibraryController;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.ui.*;

public class App {
    public static void main(String[] args) {

        UserIO io = new UserIOConsoleImpl();
        DVDLibraryView crv = new DVDLibraryView((UserIOConsoleImpl) io);
        DVDLibraryDao dao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(crv, dao);

        controller.run();
    }
}
