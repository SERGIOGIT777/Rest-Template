package aston.project.servlet;

import aston.project.servlet.dto.PhoneNumberIncomingDto;
import aston.project.servlet.dto.PhoneNumberOutGoingDto;
import aston.project.servlet.dto.PhoneNumberUpdateDto;
import aston.project.service.PhoneNumberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import aston.project.exception.NotFoundException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import aston.project.service.impl.PhoneNumberServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/phone/*"})
public class PhoneNumberServlet extends HttpServlet {
    private final transient PhoneNumberService phoneNumberService;
    private final ObjectMapper objectMapper;

    public PhoneNumberServlet() {
        this.phoneNumberService = PhoneNumberServiceImpl.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    private static void setJsonHeader(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    private static String getJson(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader postData = req.getReader();
        String line;
        while ((line = postData.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);

        String responseAnswer = "";
        try {
            String[] pathPart = req.getPathInfo().split("/");
            if ("all".equals(pathPart[1])) {
                List<PhoneNumberOutGoingDto> phoneNumberDtoList = phoneNumberService.findAll();
                resp.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(phoneNumberDtoList);
            } else {
                Long phoneNumberId = Long.parseLong(pathPart[1]);
                PhoneNumberOutGoingDto phoneNumberDto = phoneNumberService.findById(phoneNumberId);
                resp.setStatus(HttpServletResponse.SC_OK);
                responseAnswer = objectMapper.writeValueAsString(phoneNumberDto);
            }
        } catch (NotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = e.getMessage();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Bad request.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String responseAnswer = "";
        try {
            String[] pathPart = req.getPathInfo().split("/");
            Long phoneNumberId = Long.parseLong(pathPart[1]);
            if (phoneNumberService.delete(phoneNumberId)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Bad request.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String json = getJson(req);

        String responseAnswer = null;
        Optional<PhoneNumberIncomingDto> phoneNumberResponse;
        try {
            phoneNumberResponse = Optional.ofNullable(objectMapper.readValue(json, PhoneNumberIncomingDto.class));
            PhoneNumberIncomingDto phoneNumber = phoneNumberResponse.orElseThrow(IllegalArgumentException::new);
            responseAnswer = objectMapper.writeValueAsString(phoneNumberService.save(phoneNumber));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect phoneNumber Object.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String json = getJson(req);

        String responseAnswer = "";
        Optional<PhoneNumberUpdateDto> phoneNumberResponse;
        try {
            phoneNumberResponse = Optional.ofNullable(objectMapper.readValue(json, PhoneNumberUpdateDto.class));
            PhoneNumberUpdateDto phoneNumberUpdateDto = phoneNumberResponse.orElseThrow(IllegalArgumentException::new);
            phoneNumberService.update(phoneNumberUpdateDto);
        } catch (NotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseAnswer = e.getMessage();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect phoneNumber Object.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }
}
