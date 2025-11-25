package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求和响应编码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        // 获取表单参数
        String productName = req.getParameter("productName");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        double price = Double.parseDouble(req.getParameter("price"));
        String customerName = req.getParameter("customerName");

        // 简单的订单处理逻辑（实际项目中这里会连接数据库）
        String orderId = generateOrderId();
        Date orderDate = new Date();
        double totalAmount = quantity * price;

        // 模拟保存订单到session或数据库
        req.getSession().setAttribute("orderId", orderId);

        // 输出订单确认信息
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>订单确认</title></head><body>");
        out.println("<h2>订单创建成功</h2>");
        out.println("<p>订单号: " + orderId + "</p>");
        out.println("<p>商品名称: " + productName + "</p>");
        out.println("<p>数量: " + quantity + "</p>");
        out.println("<p>单价: ¥" + price + "</p>");
        out.println("<p>总金额: ¥" + totalAmount + "</p>");
        out.println("<p>客户姓名: " + customerName + "</p>");
        out.println("<p>下单时间: " + orderDate.toString() + "</p>");
        out.println("</body></html>");
    }

    /**
     * 生成简单的订单ID（实际项目中可能会更复杂）
     *
     * @return 订单ID字符串
     */
    private String generateOrderId() {
        return "ORD" + System.currentTimeMillis();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 显示创建订单的表单
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>创建订单</title></head><body>");
        out.println("<h2>创建新订单</h2>");
        out.println("<form method='post' action='/servlet_demo/order'>");
        out.println("商品名称: <input type='text' name='productName' required><br><br>");
        out.println("数量: <input type='number' name='quantity' min='1' required><br><br>");
        out.println("单价: <input type='number' step='0.01' name='price' min='0.01' required><br><br>");
        out.println("客户姓名: <input type='text' name='customerName' required><br><br>");
        out.println("<input type='submit' value='创建订单'>");
        out.println("</form>");
        out.println("</body></html>");
    }
}
