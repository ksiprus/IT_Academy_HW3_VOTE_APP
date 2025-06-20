package by.ksiprus.controller;

import by.ksiprus.dto.Stats;
import by.ksiprus.service.VoteService;
import by.ksiprus.service.api.IVoteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/results")
public class ResultsServlet extends HttpServlet {

    private final IVoteService voteService = new VoteService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Stats stats = voteService.getStats();
        req.setAttribute("artists", stats.getArtists());
        req.setAttribute("genres", stats.getGenres());
        req.setAttribute("abouts", stats.getAbouts());
        req.getRequestDispatcher("/results.jsp").forward(req, resp);
    }
}
