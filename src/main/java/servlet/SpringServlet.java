package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/springboot")
public class SpringServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 关键：设置响应编码为 UTF-8，告诉浏览器用 UTF-8 解码
        resp.setContentType("text/html;charset=UTF-8");


        resp.getWriter().write("springboot is best 看看是否有乱码");
    }

}
