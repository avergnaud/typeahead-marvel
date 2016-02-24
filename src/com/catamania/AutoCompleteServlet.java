package com.catamania;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * service d'autocomplétion
 */
public class AutoCompleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String q = req.getParameter("q");
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter(); 
		
		List<PersoBean> listeBeans = Init.persos
				.stream()
				.map(c -> com.catamania.util.Characters.toPersoBean(c))
				.filter(perso -> perso.getNom().startsWith(q))
				.collect(Collectors.toList());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(listeBeans);
		out.print(json);
		out.flush();
	}

	
	
}
