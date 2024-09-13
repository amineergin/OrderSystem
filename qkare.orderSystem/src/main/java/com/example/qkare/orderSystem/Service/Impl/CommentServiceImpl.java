package com.example.qkare.orderSystem.Service.Impl;

import com.example.qkare.orderSystem.Dto.CommentDto;
import com.example.qkare.orderSystem.Entity.Comment;
import com.example.qkare.orderSystem.Entity.Customer;
import com.example.qkare.orderSystem.Entity.Product;
import com.example.qkare.orderSystem.Repository.CommentRepository;
import com.example.qkare.orderSystem.Service.CommentService;
import com.example.qkare.orderSystem.Service.CustomerService;
import com.example.qkare.orderSystem.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;


    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Customer customer = customerService.findCustomerById(commentDto.getCustomerId())
                .orElseThrow(()-> new RuntimeException("Customer not found!"));;
        Product product = productService.findProductById(commentDto.getProductId())
                .orElseThrow(()-> new RuntimeException("Product not found!"));

        List<Comment> comments = commentRepository.findAll();

        //Kullanıcının bir ürüne yalnızca bir kez yorum yapabilmesini sağlar.
        boolean hasCommented = comments.stream()
                .anyMatch(comment -> comment.getCustomer().getCustomerId().equals(commentDto.getCustomerId()) &&
                        comment.getProduct().getProductId().equals(commentDto.getProductId()));

        if (hasCommented) {
            throw new RuntimeException("Customer has already commented on this product!");
        }

        Comment comment = new Comment();
        comment.setCommentId(commentDto.getId());
        comment.setCommentDescription(commentDto.getComment());
        comment.setCustomer(customer);
        comment.setProduct(product);
        Comment savedComment = commentRepository.save(comment);
        return convertEntityToDto(savedComment);
    }

    @Override
    public String deleteCommentById(Integer commentId) {
        Optional<Comment> deletedComment = commentRepository.findById(commentId);
        if (deletedComment.isPresent()) {
            commentRepository.delete(deletedComment.get());
            return "Comment Deleted!";
        }else{
            return "Comment not found!";
        }
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public CommentDto convertEntityToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getCommentId());
        commentDto.setComment(comment.getCommentDescription());
        commentDto.setCustomerId(comment.getCustomer().getCustomerId());
        commentDto.setProductId(comment.getProduct().getProductId());
        return commentDto;
    }
}
