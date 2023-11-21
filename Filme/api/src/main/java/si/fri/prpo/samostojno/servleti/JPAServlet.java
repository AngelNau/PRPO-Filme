package si.fri.prpo.samostojno.servleti;

import si.fri.prpo.samostojno.zrna.FilmiZrno;
import si.fri.prpo.samostojno.entitete.Film;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private FilmiZrno filmiZrno;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Film> filmi = filmiZrno.getFilmi();

        // izpis filmov na spletno stran
        PrintWriter writer = resp.getWriter();

        // izpis vseh uporabnikov
        writer.append("Seznam obstojecih filmov:\n");
        filmi.forEach(f -> writer.append(f.toString()).append("\n"));
    }
}
