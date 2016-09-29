/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.booklibrary.model.impl;

import static com.mycompany.booklibrary.constants.LibraryControllerConstants.*;
import com.mycompany.booklibrary.model.dao.impl.DaoImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LibraryService
 * @author Zakhar
 */
public class LibraryService {
    private Pattern authorPattern = Pattern.compile(AUTHOR_REG);
    private Pattern bookPattern = Pattern.compile(BOOK_REG);
    private ArrayList<String> resultArray = new ArrayList<String>();
    private Pattern bookNamePattern = Pattern.compile(BOOK_REG);
    private String bookName;
    private DaoImpl daoImpl = new DaoImpl();
    private String author;
    private String book;
    
    /**
     * implements command "add"
     * adds new book and it's author to the db
     * @param inputedLine user's inputed line
     */
    public void addBook(String inputedLine){
        Matcher matcherAuthorCommand = authorPattern.matcher(inputedLine);
        while (matcherAuthorCommand.find()) {
            author = matcherAuthorCommand.group();
        }
        Matcher matcherBookCommand = bookPattern.matcher(inputedLine);
        while (matcherBookCommand.find()) {
            book = matcherBookCommand.group();
        }
        daoImpl.addBookToDb(author, book);
    }
    
    /**
     * selected all books with same name to edit
     * @param bookName - book name
     */
    public ArrayList<String> removeBook(String inputedLine){
        Matcher matchetBookName = bookNamePattern.matcher(inputedLine);
        while (matchetBookName.find()) {
            bookName = matchetBookName.group();
        }
        resultArray = daoImpl.select(bookName);
        return resultArray;
    }
    
    /**
     * removes book from db by it's id
     * @param bookId - book id
     */
    public String removeBook(int bookId){
        daoImpl.removeBookFromDb(bookId);
        return WAS_DELETED_MESSAGE;
    }
    
    /**
     * selected all books with same name to edit
     * @param inputedLine - inputed line
     */
    public ArrayList<String> editBook(String inputedLine){
        Matcher matchetBookName = bookNamePattern.matcher(inputedLine);
        while (matchetBookName.find()) {
            bookName = matchetBookName.group();
        }
        resultArray = daoImpl.select(bookName);
        return resultArray;
    }
    
    /**
     * updates book name
     * @param bookId - book id
     * @param bookNameNew - new name
     */
    public String editBook(String bookNameNew, int bookId){
        daoImpl.editBook(bookNameNew, bookId);
        return WAS_EDITED_MESSAGE;
    }
    
    /**
     * selects all books, ordered by the name
     * @param bookId - book id
     */
    public ArrayList<String> selectAll(){
        return sortArray(daoImpl.selectAll());
    }
    
    /**
     * sorts the array
     * @return 
     */
    public ArrayList<String> sortArray(ArrayList<String> array){
        resultArray = array;
        Collections.sort(resultArray);
        return resultArray;
    } 
}
