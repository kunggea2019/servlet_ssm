package servlet;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cap")
public class CapchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 关闭浏览器缓存（避免验证码刷新不更新）
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);


        // -------------- 方式 1：默认图形验证码（数字+字母+干扰线）--------------
        // 参数：宽度、高度、验证码位数、干扰线数量
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(120, 40, 4, 10);
        // 可选：指定验证码类型（纯数字/纯字母）
        // captcha.setGenerator(new DigitGenerator(4)); // 纯数字（需导入 cn.hutool.captcha.DigitGenerator）
        // captcha.setGenerator(new AlphaGenerator(4)); // 纯字母（需导入 cn.hutool.captcha.AlphaGenerator）

        // -------------- 方式 2：算术验证码（推荐，安全性更高）--------------
        // 参数：宽度、高度、数字位数、运算符号数（0=无运算，1=加减，2=加减乘除）
        // ArithmeticCaptcha captcha = CaptchaUtil.createArithmeticCaptcha(120, 40, 3, 1);
        // 注意：算术验证码的 getCode() 返回的是运算结果（如 "3+5=8" 返回 "8"）

        // 2. 关键：将验证码存入 Session（后续验证用户输入时用）
        req.getSession().setAttribute("capchaCode", captcha.getCode());

        // 3. 设置响应格式为图片（告诉浏览器用图片解析）
        resp.setContentType("image/png");

        // 4. 将验证码图片输出到浏览器（Hutool 自动处理流关闭）
        captcha.write(resp.getOutputStream());
    }
}