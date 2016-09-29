import com.mycompany.booklibrary.controller.LibraryManagingController;
import com.mycompany.booklibrary.model.dao.impl.DaoImpl;
import com.mycompany.booklibrary.view.LibraryManagingView;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Zakhar
 */
public class Main {
    
    public static void main(String[] args){
        LibraryManagingController libManage = new LibraryManagingController();
        libManage.startManaging();
    }
}
