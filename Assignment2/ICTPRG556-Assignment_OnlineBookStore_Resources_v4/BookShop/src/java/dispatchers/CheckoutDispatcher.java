/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author dalevanh
 */
public class CheckoutDispatcher implements IDispatcher{
    public String execute(HttpServletRequest request) {
        String nextPage = "/jsp/checkout.jsp";
        return nextPage;
    }
    
}
