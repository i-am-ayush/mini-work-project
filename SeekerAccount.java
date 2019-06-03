import bean.Job;
import bean.Member;
import bean.Seeker;
import service.MemberService;
import service.SeekerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class SeekerAccount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Member member = (Member) session.getAttribute("member");
        Seeker seeker = (Seeker) member;
        List<Job> jobsPosted = SeekerService.allJobPostedBySeeker(seeker.getMemberId());
        req.setAttribute("jobposted",jobsPosted);
        RequestDispatcher rd = req.getRequestDispatcher("seekeraccount.jsp");
        rd.include(req, resp);
//        String title = "", status = "", startDate = "", endDate = "";
//        int jobId = 0;
//        for (Job jobPosted : jobsPosted) {
//            title = jobPosted.getTitle();
//            status = String.valueOf(jobPosted.getStatus());
//            DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
//            startDate = dateFormat.format(jobPosted.getStartDateTime()
//            endDate = dateFormat.format(jobPosted.getEndDateTime());
//            jobId = jobPosted.getId();
//
//        }
    }
}
