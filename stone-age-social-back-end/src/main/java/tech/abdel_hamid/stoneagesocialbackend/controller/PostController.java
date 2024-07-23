package tech.abdel_hamid.stoneagesocialbackend.controller;

import tech.abdel_hamid.stoneagesocialbackend.entity.DoubleIdObjectEntity;
import tech.abdel_hamid.stoneagesocialbackend.entity.IdObjectEntity;
import tech.abdel_hamid.stoneagesocialbackend.entity.PostEntity;
import tech.abdel_hamid.stoneagesocialbackend.service.PostService;
import tech.abdel_hamid.stoneagesocialbackend.service.ResponseObjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PostController {
    @Autowired
    private PostService postService;

    /**
     * Insert a post
     * @param inputPost
     * @return
     */
    @PostMapping("/insertpost")
    public ResponseEntity<ResponseObjectService> insertPost(@RequestBody PostEntity inputPost) {
        return new ResponseEntity<ResponseObjectService>(postService.insertPost(inputPost), HttpStatus.OK);
    }

    /**
     * Get posts of a user
     * @param inputUserId
     * @return
     */
    @PostMapping("/myposts")
    public ResponseEntity<ResponseObjectService> findPostByUserId(@RequestBody IdObjectEntity inputUserId) {
        return new ResponseEntity<ResponseObjectService>(postService.findPostByUserId(inputUserId), HttpStatus.OK);
    }

    /**
     *  Get posts of users that the user is following
     * @param inputUserId
     * @return
     */
    @PostMapping("/followingposts")
    public ResponseEntity<ResponseObjectService> findPostByFollowing(@RequestBody IdObjectEntity inputUserId) {
        return new ResponseEntity<ResponseObjectService>(postService.findPostByFollowing(inputUserId), HttpStatus.OK);
    }

    // currently not in use, post is update via comment controller
    // @PutMapping("/updatebycomment")
    // public ResponseEntity<ResponseObjectService> updateByComment(@RequestBody PostEntity inputPost) {
    //     return new ResponseEntity<ResponseObjectService>(postService.updatePostByComment(inputPost), HttpStatus.OK);
    // }

    /**
     * Update post by love
     * @param doubleId
     * @return
     */
    @PostMapping("/lovepost")
    public ResponseEntity<ResponseObjectService> lovePost(@RequestBody DoubleIdObjectEntity doubleId) {
        return new ResponseEntity<ResponseObjectService>(postService.updatePostByLove(doubleId), HttpStatus.OK);
    }
    /**
     * Update post by share
     * @param doubleId
     * @return
     */
    @PostMapping("/sharepost")
    public ResponseEntity<ResponseObjectService> sharePost(@RequestBody DoubleIdObjectEntity doubleId) {
        return new ResponseEntity<ResponseObjectService>(postService.updatePostByShare(doubleId), HttpStatus.OK);
    }
}
