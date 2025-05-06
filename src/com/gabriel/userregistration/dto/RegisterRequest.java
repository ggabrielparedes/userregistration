package com.gabriel.userregistration.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.userregistration.dbconnection.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterRequest extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response;
        String json;
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
        RegisterDTO dto = mapper.readValue(json, RegisterDTO.class);
        User user = new User(dto.getUsername(), dto.getEmail(), dto.getPassword());
        response = user.addUser() ? "{\"response\" : true}" : "{\"response\" : false}";
        PrintWriter out = resp.getWriter();
        out.print(response);
        out.flush();

    }

}
