package com.sg.classDVDLibrary;

import com.sg.classDVDLibrary.controller.ClassDVDLibraryController;
import com.sg.classDVDLibrary.dao.ClassDVDLibraryDao;
import com.sg.classDVDLibrary.dao.ClassDVDLibraryFileDaoImpl;
import com.sg.classDVDLibrary.ui.ClassDVDLibraryView;
import com.sg.classDVDLibrary.ui.UserIO;
import com.sg.classDVDLibrary.ui.UserIOConsoleImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appConfig = new AnnotationConfigApplicationContext();
        appConfig.scan("com.sg.ClassDVDLibrary");
        appConfig.refresh();

//        UserIOConsoleImpl userIOConsole = appConfig.getBean("userIOConsoleImpl",UserIOConsoleImpl.class);
//        ClassDVDLibraryView classDVDLibraryView = appConfig.getBean("classDVDLibraryView",ClassDVDLibraryView.class);

        ClassDVDLibraryController controller =
                appConfig.getBean("classDVDLibraryController", ClassDVDLibraryController.class);
        controller.run();

//        UserIO myIo = new UserIOConsoleImpl();
//        ClassDVDLibraryView myView = new ClassDVDLibraryView(myIo);
//        ClassDVDLibraryDao myDao = new ClassDVDLibraryFileDaoImpl();
//        ClassDVDLibraryController controller = new ClassDVDLibraryController(myDao, myView);
//        controller.run();

    }

}
