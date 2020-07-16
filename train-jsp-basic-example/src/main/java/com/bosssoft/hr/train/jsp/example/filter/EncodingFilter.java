package com.bosssoft.hr.train.jsp.example.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 瘦明月
 */
public class EncodingFilter implements Filter {

    String encode;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(encode);
        resp.setCharacterEncoding(encode);
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        encode = config.getInitParameter("encode");
    }

}
