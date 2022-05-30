package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.entity.CVEntity;
import com.example.happyprogramming.entity.CommentAndRateEntity;
import com.example.happyprogramming.entity.UserEntity;
import com.example.happyprogramming.repository.CVRepository;
import com.example.happyprogramming.repository.RateCommentRepository;
import com.example.happyprogramming.repository.UserRepository;
import com.example.happyprogramming.service.NotificationService;
import com.example.happyprogramming.service.RateCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RateCommentServiceImpl implements RateCommentService {

    @Autowired
    private RateCommentRepository rateCommentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CVRepository cvRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public String getRateComment(int mentorId, UserEntity mentee) {
        UserEntity user = userRepository.findById(mentorId);
        CVEntity mentor = cvRepository.findByUser(user);
        CommentAndRateEntity commentAndRate = rateCommentRepository.findByMentorAndMentee(mentor,mentee);
        String comment = commentAndRate.getComment()!=null?commentAndRate.getComment():"";
        String result="<form class=\"form\" action=\"\">\n" +
                "            <input id=\"mentor-id\" type=\"hidden\">\n" +
                "            <input id=\"rate-comment-id\" value =\""+commentAndRate.getId()+"\" type=\"hidden\">\n" +
                "            <a class=\"icon-close\" onclick=\"hideRate()\">Close</a>\n" +
                "            <input id=\"ratings-hidden\" name=\"rating\" type=\"hidden\">\n" +
                "            <textarea class=\"form-control animated\" cols=\"50\" id=\"new-comment\" name=\"comment\"\n" +
                "                      placeholder=\"Enter your review here...\" rows=\"5\">"+comment+"</textarea>\n" +
                "            <div class=\"container d-flex justify-content-center\">\n" +
                "                <div class=\"row\">\n" +
                "                    <div class=\"col-md-12\">\n" +
                "                        <div class=\"stars\">\n" +
                "                            <input "+getRateNumber(5,commentAndRate.getRate())+" class=\"star star-5\" id=\"star-5\" type=\"radio\" name=\"star\" value=\"5\"/>\n" +
                "                            <label class=\"star star-5\" for=\"star-5\"></label>\n" +
                "                            <input "+getRateNumber(4,commentAndRate.getRate())+" class=\"star star-4\" id=\"star-4\" type=\"radio\" name=\"star\" value=\"4\"/>\n" +
                "                            <label class=\"star star-4\" for=\"star-4\"></label>\n" +
                "                            <input "+getRateNumber(3,commentAndRate.getRate())+" class=\"star star-3\" id=\"star-3\" type=\"radio\" name=\"star\" value=\"3\"/>\n" +
                "                            <label class=\"star star-3\" for=\"star-3\"></label>\n" +
                "                            <input "+getRateNumber(2,commentAndRate.getRate())+" class=\"star star-2\" id=\"star-2\" type=\"radio\" name=\"star\" value=\"2\"/>\n" +
                "                            <label class=\"star star-2\" for=\"star-2\"></label>\n" +
                "                            <input "+getRateNumber(1,commentAndRate.getRate())+" class=\"star star-1\" id=\"star-1\" type=\"radio\" name=\"star\" value=\"1\"/>\n" +
                "                            <label class=\"star star-1\" for=\"star-1\"></label>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <button class=\"btn btn-success btn-lg\" type=\"button\" onclick=\"saveReview()\">Save</button>\n" +
                "        </form>";
        return result;
    }

    @Override
    public void saveRateComment(int id, String comment, int starNumber) {
        CommentAndRateEntity commentAndRate = rateCommentRepository.findById(id);
        CVEntity mentor = commentAndRate.getMentor();
        if(mentor.getRatedNumbers()==0 && commentAndRate.getRate()==0){
            mentor.setAverageStar(starNumber);
            mentor.setRatedNumbers(1);
        }else if(mentor.getRatedNumbers()!=0 && commentAndRate.getRate()==0){
            double aveStar = (mentor.getAverageStar() * mentor.getRatedNumbers() + starNumber) / (mentor.getRatedNumbers()+1);
            mentor.setAverageStar(aveStar);
            mentor.setRatedNumbers(mentor.getRatedNumbers()+1);
        }else{
            double aveStar = (mentor.getAverageStar() * mentor.getRatedNumbers() - commentAndRate.getRate() + starNumber)/mentor.getRatedNumbers();
            mentor.setAverageStar(aveStar);
        }
        commentAndRate.setComment(comment);
        commentAndRate.setRate(starNumber);
        rateCommentRepository.save(commentAndRate);
        cvRepository.save(mentor);
        notificationService.ratedNotification(mentor.getUser(),commentAndRate.getMentee());
    }

    @Override
    public void enableRateAndComment(UserEntity mentor, UserEntity mentee) {
        CommentAndRateEntity commentAndRate = new CommentAndRateEntity();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        CVEntity mentorUpdate = cvRepository.findByUser(mentor);
        commentAndRate.setCreatedDate(dtf.format(now));
        commentAndRate.setMentor(mentorUpdate);
        commentAndRate.setMentee(mentee);
        commentAndRate.setRate(0);
        rateCommentRepository.save(commentAndRate);
    }


    public String getRateNumber(int star, int ratedStar){
        return star==ratedStar?"checked":"";
    }
}
