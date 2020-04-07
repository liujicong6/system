package com.book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.BookService;
import com.mysql.jdbc.StringUtils;

@WebServlet("/delete_category")
public class DeleteCategoryAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 接收参数
		String sId = request.getParameter("id");
		if(StringUtils.isNullOrEmpty(sId)) {
			request.setAttribute("msg","错误的操作");
			request.getRequestDispatcher("/category_mgr").forward(request,response);
			return;
		}
		Integer id = Integer.valueOf(sId);
		// 调用服务，完成删除
		int result = bookService.deleteCategoryById(id);
		if(result == 0) {
			request.setAttribute("msg","删除分类失败");
		}
		else {
			request.setAttribute("msg","删除分类成功");
		}
		request.getRequestDispatcher("/category_mgr").forward(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
