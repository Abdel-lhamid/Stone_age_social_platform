package tech.abdel_hamid.stoneagesocialbackend.controller;

import java.security.Principal;
import java.util.Optional;

import tech.abdel_hamid.stoneagesocialbackend.entity.AuthorizedEntity;
import tech.abdel_hamid.stoneagesocialbackend.entity.DoubleIdObjectEntity;
import tech.abdel_hamid.stoneagesocialbackend.entity.IdObjectEntity;
import tech.abdel_hamid.stoneagesocialbackend.entity.UserEntity;
import tech.abdel_hamid.stoneagesocialbackend.entity.UserSignInEntity;
import tech.abdel_hamid.stoneagesocialbackend.repository.UserRepository;
import tech.abdel_hamid.stoneagesocialbackend.service.JWTUtil;
import tech.abdel_hamid.stoneagesocialbackend.service.ResponseObjectService;
import tech.abdel_hamid.stoneagesocialbackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepo;

    /**
     * Get all users
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<ResponseObjectService> findAllUsers() {
        return new ResponseEntity<ResponseObjectService>(userService.findAll(), HttpStatus.OK);
    }
    /**
     * Get user by id
     * @param inputId
     * @return
     */
    @PostMapping("/users/profile")
    public ResponseEntity<ResponseObjectService> findById(@RequestBody IdObjectEntity inputId) {
        return new ResponseEntity<ResponseObjectService>(userService.findById(inputId.getId()), HttpStatus.OK);
    }
    /**
     * Follow a user
     * @param doubleId
     * @return
     */

    @PostMapping("/users/follow")
    public ResponseEntity<ResponseObjectService> followUser(@RequestBody DoubleIdObjectEntity doubleId) {
        return new ResponseEntity<ResponseObjectService>(userService.followUser(doubleId), HttpStatus.OK);
    }
    /**
     * Unfollow a user
     * @param doubleId
     * @return
     */
    @PostMapping("/users/unfollow")
    public ResponseEntity<ResponseObjectService> unfollowUser(@RequestBody DoubleIdObjectEntity doubleId) {
        return new ResponseEntity<ResponseObjectService>(userService.unfollowUser(doubleId), HttpStatus.OK);
    }
    /**
     * Get users that the user is following
     * @param inputId
     * @return
     */
    @PostMapping("/users/getfollowing")
    public ResponseEntity<ResponseObjectService> findFollowing(@RequestBody IdObjectEntity inputId) {
        return new ResponseEntity<ResponseObjectService>(userService.findFollowing(inputId.getId()), HttpStatus.OK);
    }
    /**
     * Get users that are following the user
     * @param inputId
     * @return
     */
    @PostMapping("/users/getfollower")
    public ResponseEntity<ResponseObjectService> findFollower(@RequestBody IdObjectEntity inputId) {
        return new ResponseEntity<ResponseObjectService>(userService.findFollower(inputId.getId()), HttpStatus.OK);
    }
    /**
     * Save a user
     * @param inputUser
     * @return
     */
    @PostMapping("/users/save")
    public ResponseEntity<ResponseObjectService> saveUser(@RequestBody UserEntity inputUser) {
        return new ResponseEntity<ResponseObjectService>(userService.saveUser(inputUser), HttpStatus.OK);
    }
    /**
     * Sign in a user
     * @param inputUser
     * @return
     */
    @PostMapping("/users/signin")
    public ResponseEntity<ResponseObjectService> userSignIn(@RequestBody UserSignInEntity inputUser) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(inputUser.getEmail(), inputUser.getPassword()));
            String token = jwtUtil.generateToken(inputUser.getEmail());
            
            Optional<UserEntity> optUser = userRepo.findByEmail(inputUser.getEmail());
            UserEntity user = optUser.get();
            user.setPassword("");
            return new ResponseEntity<ResponseObjectService>(new ResponseObjectService("success", "authenticated", new AuthorizedEntity(user, token)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<ResponseObjectService>(new ResponseObjectService("fail", "unauthenticated", null), HttpStatus.OK);
        }
    }
    /**
     * Update a user
     * @param inputUser
     * @return
     */

    @PutMapping("/users/update")
    public ResponseEntity<ResponseObjectService> update(@RequestBody UserEntity inputUser) {
        return new ResponseEntity<ResponseObjectService>(userService.update(inputUser), HttpStatus.OK);
    }
    /**
     * Delete a user
     * @param inputId
     * @return
     */
    @GetMapping("/getdata")
    public ResponseEntity<String> testAfterLogin(Principal p) {
        return ResponseEntity.ok("Welcome. You are: " + p.getName());
    }
}
