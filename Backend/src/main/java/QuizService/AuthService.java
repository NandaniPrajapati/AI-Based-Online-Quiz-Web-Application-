package QuizService;

import JWT.JwtUtil;
import Model.UserModel;
import QuizRepository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private JwtUtil jwtUtil;

    public String register(UserModel user) {
        userRepo.save(user);
        return "User Registered";
    }

    public String login(String email, String password) {

        UserModel user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(email);
    }
}
