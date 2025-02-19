package tech.abdel_hamid.stoneagesocialbackend.controller;

import tech.abdel_hamid.stoneagesocialbackend.entity.CommentEntity;
import tech.abdel_hamid.stoneagesocialbackend.entity.CommentPostRequestEntity;
import tech.abdel_hamid.stoneagesocialbackend.entity.IdObjectEntity;
import tech.abdel_hamid.stoneagesocialbackend.service.CommentService;
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
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * Insert a comment to a post
     * @param postedComment
     * @return
     */
    @PostMapping("/insertcomment")
    public ResponseEntity<ResponseObjectService> insertComment(@RequestBody CommentPostRequestEntity postedComment) {
        CommentEntity inputComment = postedComment.getCommentEntity();
        IdObjectEntity inputPostId = postedComment.getPostId();
        return new ResponseEntity<ResponseObjectService>(commentService.insertComment(inputComment, inputPostId.getId()), HttpStatus.OK);
    }
    /**
     * Get comments of a post
     * @param inputPostId
     * @return
     */

    @PostMapping("/getcomments") 
    public ResponseEntity<ResponseObjectService> getComments(@RequestBody IdObjectEntity inputPostId) {
        return new ResponseEntity<ResponseObjectService>(commentService.getComments(inputPostId.getId()), HttpStatus.OK);
    }
}
