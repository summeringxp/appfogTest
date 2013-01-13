package org.cloudfoundry.samples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import tokenizer.Tokenizer;

public class TokenizeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		Tokenizer tokenizer = new Tokenizer();
		String tokenizedContent = tokenizer.getTokenizedContent("anyfile.java", code);
		int hash = tokenizedContent.hashCode();
		System.out.println(hash);
		JSONObject json = new JSONObject();

		JSONArray array = new JSONArray();
		JSONObject results = null;

		results = new JSONObject();
		results.put("result1", tokenizedContent);
		results.put("result2", String.valueOf(hash));
		array.add(results);

		
		json.put("jsonArray", array);
		
		PrintWriter pw = response.getWriter();
		pw.print(json.toString());
		pw.close();
	}
}
