package com.gabriel.userregistration.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.userregistration.dbconnection.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginRequest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json;
        String response;
        String line;
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        while((line = reader.readLine()) != null) {
            sb.append(line);
        }
        json = sb.toString();
        LoginDTO dto = mapper.readValue(json, LoginDTO.class);
        User user = new User(dto.getEmail(), dto.getPassword());
        response = user.login() ? "{\"response\" : true}" : "{\"response\" : false}";
        PrintWriter out = resp.getWriter();
        out.print(response);
        out.flush();
    }
}
