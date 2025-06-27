package by.ksiprus.controller;

import by.ksiprus.dto.Vote;
import by.ksiprus.service.VoteService;
import by.ksiprus.service.api.IVoteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {

    private final IVoteService servise = new VoteService();

    private static final String[] ARTISTS = new String[]{
            "Руки Вверх", "Mikaya", "Alon", "Chase & Status", "Rammstain"
    };
    private static final String[] GENRES = new String[]{
            "jazz", "d&b", "rock", "r&b", "pop", "Electronic", "classic", "hip-hop", "pop-rock", "disco"
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        req.setAttribute("artistsList", ARTISTS);
        req.setAttribute("genresList", GENRES);

        req.getRequestDispatcher("/vote.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String[] artists = req.getParameterValues("artists");
        String[] genres = req.getParameterValues("genres");
        String[] abouts = req.getParameterValues("abouts");

        Vote result = new Vote();
        result.setDtCreate(LocalDateTime.now());
        result.setArtist(artists[0]);
        result.setGenres(Arrays.asList(genres));
        result.setAbout(abouts[0]);
        servise.add(result);

        resp.sendRedirect(req.getContextPath() + "/results");
    }
}