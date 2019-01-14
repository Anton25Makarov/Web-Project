package com.epam.library.command;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.model.*;
import com.epam.library.service.LibrarianService;
import com.epam.library.service.ReaderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class OrderBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {


        String bookIdParameter = req.getParameter("bookId");

        Long bookId = null;
        if (bookIdParameter != null && !bookIdParameter.isEmpty()) {
            bookId = Long.parseLong(bookIdParameter);
        }

        Long authorIdParameter = Long.valueOf(req.getParameter("authorId"));
        Long genreIdParameter = Long.valueOf(req.getParameter("genreId"));
        String bookTitleParameter = req.getParameter("bookTitle");
        int bookYearParameter = Integer.parseInt(req.getParameter("bookYear"));
        int bookCountParameter = Integer.parseInt(req.getParameter("bookCount"));
        String inReadingRoomParameter = req.getParameter("inReadingRoom");
        boolean inReadingRoom = false;
        if (inReadingRoomParameter != null) {
            inReadingRoom = true;
        }

        Author author = new Author(authorIdParameter);
        BookGenre genre = new BookGenre(genreIdParameter);

        Book book = new Book(bookId, bookTitleParameter, bookYearParameter, bookCountParameter, author, genre);

        HttpSession session = req.getSession();


//        String takingDateParameter = req.getParameter("orderTakingDate");
//        String returnDateParameter = req.getParameter("orderReturnDate");
        Date takingDate = new Date();


        try (ReaderService service = new ReaderService()) {
            Reader reader = (Reader) session.getAttribute("user");

            Optional<Book> book1 = service.getBookInStoke(bookId);

            if (book1.isPresent()) {
                int count = book1.get().getCount();
                book1.get().setCount(count - 1);
                service.saveBook(book1.get());

                Order order = new Order(inReadingRoom, null, null, book1.get(), reader);

                service.saveOrder(order);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CommandResult.redirect("/controller?command=readersAllBooks&save=success");//page - const
    }
}
