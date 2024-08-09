# Example of Code the FrontController.java
```java
package controller;
import dispatchers.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import dispatchers.*;
import model.Book;
import model.CartItem;
import utility.AdmitBookStoreDAO;

public class FrontController extends HttpServlet {

    private final HashMap actions = new HashMap();

    //Initialize global variables
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        actions.put("titles", new TitlesDispatcher());
        actions.put("add_to_cart", new AddToCartDispatcher());
        actions.put("checkout", new CheckoutDispatcher());
        actions.put("continue", new ContinueDispatcher());
        actions.put("home", new HomeDispatcher());
        actions.put("update_cart", new UpdateCartDispatcher());
        actions.put("view_cart", new ViewCartDispatcher());
    }

    //Process the HTTP Get request
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("doGet()");
        doPost(request, response);
    }

    //Process the HTTP Post request
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String nextPage = "no view";
        String requestedAction = request.getParameter("action");
        if (requestedAction == null) {
            requestedAction = "titles";
        }
        IDispatcher dispatcher = (IDispatcher) actions.get(requestedAction);
        nextPage = dispatcher.execute(request);
        this.dispatch(request, response, nextPage);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException,
            IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    //Get Servlet information
    public String getServletInfo() {
        return "controller.FrontController Information";
    }
}


```
 
