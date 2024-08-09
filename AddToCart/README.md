# Example of Code the AddToCartDispatcher.java
```java
package dispatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Book;
import model.CartItem;
import utility.AdmitBookStoreDAO;

public class AddToCartDispatcher implements IDispatcher{
    public String execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String nextPage = "/jsp/titles.jsp";
    Map cart = (Map) session.getAttribute("cart");
    String[] selectedBooks = request.getParameterValues("add");
    AdmitBookStoreDAO dao = new AdmitBookStoreDAO();
    
                if (cart == null) {
                cart = new HashMap();
                for (int i = 0; i < selectedBooks.length; i++) {
                    String isbn = selectedBooks[i];
                    int quantity = Integer.parseInt(request.getParameter(isbn));
                    Book book = this.getBookFromList(isbn, session);
                    CartItem item = new CartItem(book);
                    item.setQuantity(quantity);
                    cart.put(isbn, item);
                }  // end for
                session.setAttribute("cart", cart);
            } // end if
            else {
                for (int i = 0; i < selectedBooks.length; i++) {
                    String isbn = selectedBooks[i];
                    int quantity = Integer.parseInt(request.getParameter(isbn));
                    if (cart.containsKey(isbn)) {
                        CartItem item = (CartItem) cart.get(isbn);
                        item.setQuantity(quantity);
                    } // end if
                    else {
                        Book book = this.getBookFromList(isbn, session);
                        CartItem item = new CartItem(book);
                        item.setQuantity(quantity);
                        cart.put(isbn, item);
                    } // end else
                } // end for
            } //end else
            return nextPage;
    }
        private Book getBookFromList(String isbn, HttpSession session) {
        List list = (List) session.getAttribute("books");
        Book aBook = null;
        for (int i = 0; i < list.size(); i++) {
            aBook = (Book) list.get(i);
            if (isbn.equals(aBook.getIsbn())) {
                break;
            } // end if
        } // end for
        return aBook;
    } // end getBookFromList
}

```
 
