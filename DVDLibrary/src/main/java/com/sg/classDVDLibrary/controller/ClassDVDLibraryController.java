package com.sg.classDVDLibrary.controller;

import com.sg.classDVDLibrary.dao.ClassDVDLibraryDao;
import com.sg.classDVDLibrary.dao.ClassDVDLibraryDaoException;
import com.sg.classDVDLibrary.dto.DVD;
import com.sg.classDVDLibrary.ui.ClassDVDLibraryView;
import com.sg.classDVDLibrary.ui.UserIO;
import com.sg.classDVDLibrary.ui.UserIOConsoleImpl;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassDVDLibraryController {

    private ClassDVDLibraryView view;
    private ClassDVDLibraryDao dao;

    public ClassDVDLibraryController(ClassDVDLibraryDao dao, ClassDVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

//   private ClassDVDLibraryView view = new ClassDVDLibraryView();
//   private ClassDVDLibraryDao dao = new ClassDVDLibraryFileDaoImpl();
    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try{
        while (keepGoing) {
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listDVDs();
                    break;
                case 2:
                    addDVDs();
                    break;
                case 3:
                    viewDVD();
                    break;
                case 4:
                    removeDVD();
                    break;

                case 5: EditDVDInfo();
                    io.print("EDIT DVD INFO");
                    break;
                case 6:
                    searchDVD();
                    break;

                case 7:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
        } catch (ClassDVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }




    // To Add DVDs second option of the menu
    private void addDVDs() throws ClassDVDLibraryDaoException {

        int  addStopSelection = 0;
        boolean keepGoing = true;

        switch (addStopSelection) {
                case 1:
                    keepGoing = false;
                    break;
                case 2:
                    keepGoing = true;
                    break;
                default:
                    unknownCommand();
            }
        while (keepGoing) {
            view.displayAddDVDtBanner();
            DVD newDVD = view.getNewDVDInfo();
            dao.addDVD(newDVD.getTitle(), newDVD);
            view.displayAddSuccessBanner();
            addStopSelection = view.getAddContinueGetSelection();
            switch (addStopSelection) {
                case 1:
                    keepGoing = false;
                    break;
                case 2:
                    keepGoing = true;
                    break;
                default:
                    unknownCommand();
            }
//
        }
    }


    // List DVDs part first option of the menu
    private void listDVDs() throws ClassDVDLibraryDaoException{
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }


    // Find DVD part 3
    private void viewDVD() throws ClassDVDLibraryDaoException{
        view.displayDisplayDVDBanner();
        String dvdTitle = view.getDVDTitleChoice();
        DVD dvd = dao.getDVD(dvdTitle);
        view.displayDVD(dvd);
    }

// Remove a DVD part 4
private void removeDVD()throws ClassDVDLibraryDaoException {
    int removeStopSelection = 0;
    boolean keepGoing = true;


    while (keepGoing) {
        view.displayRemoveDVDBanner();
        String dvdtitle = view.getDVDTitleChoice();
        DVD removedDVD = dao.removeDVD(dvdtitle);
        view.displayRemoveResult(removedDVD);

        removeStopSelection = view.getRemoveContinueGetSelection();
        switch (removeStopSelection) {
            case 1:
                keepGoing = false;
                break;
            case 2:
                keepGoing = true;
                break;
            default:
                unknownCommand();
        }
    }

}


//Search DVD part 6

    private void searchDVD() throws ClassDVDLibraryDaoException{

        view.displaySearchDVDBanner();
        ArrayList<String> result= dao.searchDVD(view.getSearchKeyword());
        for (int i=0;i<result.size();i++) {
            io.print(result.get(i));
            }
        view.displaySearchResultBanner();

        // Particular DVD Info
        //To create two option main menu or continuing to see DVDs Info
        int  showInfoStopSelection = 0;
        boolean keepGoing = true;

//
        while (keepGoing) {
            int selected = view.getFullInfoMenuSelection();
        switch (selected) {
            case 1:
                keepGoing = false;
                break;
            case 2:
                keepGoing = true;
                break;
            default:
                unknownCommand();
        }

            String keyword = view.displayEnterKeywordBanner();
             DVD dvdInfo =dao.getDVD(keyword);
             io.print("\n");
             io.print( "Title: " + dvdInfo.getTitle() + " " + "Director Name: " +dvdInfo.getDirectorName() + " " +
                     "MPAA Rating: "+  dvdInfo.getMpaaRating() + " " + "Release Date: " +dvdInfo.getReleaseDate() + " "
                     + "Note "+dvdInfo.getUserNote());

        }

    }


  // Edit

   public void EditDVDInfo()throws ClassDVDLibraryDaoException{
       String dvdTitle = view.getDVDTitleChoice();
       String  newTitle=view.getTitleToEdit();
       DVD newRecord =  dao.EditDVDInfo( dvdTitle, newTitle);
       io.print("\n");
       io.print( "Title: "+ newRecord.getTitle() + "   " + "Director Name: "+ newRecord.getDirectorName() + "   " +
               "MPAA Rating: "+ newRecord.getMpaaRating() + "   " +  "Release Date: "+ newRecord.getReleaseDate() + "   "
               + "Note "+ newRecord.getUserNote());

       view.displayEditResultBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }


   }
