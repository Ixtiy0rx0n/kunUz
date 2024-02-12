package org.example.kunuz.service;

import org.example.kunuz.dto.ArticleLikeDTO;
import org.example.kunuz.dto.CategoryDTO;
import org.example.kunuz.dto.CommentDTO;
import org.example.kunuz.dto.RegionDTO;
import org.example.kunuz.entity.*;
import org.example.kunuz.exp.AppBadException;
import org.example.kunuz.repository.ArticleRepository;
import org.example.kunuz.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    /* 1. CREATE (ANY)
        (content,article_id,reply_id)
    2. UPDATE (ANY and owner)
         (content,article_id)
    3. DELETE (ADMIN,ANY(only owner))
    4. Get Article Comment List By Article Id
        id,created_date,update_date,profile(id,name,surname)
    5. Comment List (pagination) (ADMIN)
        (id,created_date,update_date,profile(id,name,surname),content,article(id,title),reply_id,)
    6. Comment Filter(id,created_date_from,created_date_to,profile_id,article_id) with Pagination (ADMIN)
        id,created_date,update_date,profile_id,content,article_id,reply_id,visible
    7. Get Replied Comment List by Comment Id
        id,created_date,update_date,profile(id,name,surname)*/
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public CommentDTO create(CommentDTO dto) {
        CommentEntity entity = new CommentEntity();
        entity.setContent(dto.getContent());
        entity.setArticleId(dto.getArticleId());
        entity.setProfileId(dto.getProfileId());
        entity.setVisible(true);
        commentRepository.save(entity);
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        return dto;
    }

    public CommentDTO update(Integer id, CommentDTO dto) {
        Optional<CommentEntity> optional = commentRepository.findById(id);
        if (optional.isPresent()) {
            CommentEntity entity = optional.get();
            entity.setContent(dto.getContent());
            entity.setUpdatedDate(LocalDateTime.now());
            commentRepository.save(entity);
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setUpdateDate(entity.getUpdatedDate());
            return dto;
        }
        else {
            throw new AppBadException("comment not found");
        }
    }

    public Boolean delete(Integer id) {
        Optional<CommentEntity> optional = commentRepository.findById(id);
        if (optional.isPresent()) {
            commentRepository.delete(id);
            return true;
        } else {
            throw new AppBadException("Comment not found");
        }
    }

   /* public List<CommentDTO> getArticleComment(String id) {
        Optional<Integer> optional = articleRepository.getArticleEntityById(id);
        if (optional.isPresent()) {
            List<CommentDTO> listComment = new LinkedList<>();
            for (CommentDTO dto : listComment) {
                listComment.add(commentRepository.getAllComment(id));
            }
            return listComment;
        } else {
            throw new AppBadException("Article not found");
        }
    }*/

    public PageImpl getAll(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<CommentEntity> commentPage = commentRepository.findAllByVisible(paging, true);
        List<CommentEntity> entityList = commentPage.getContent();
        Long totalElement = commentPage.getTotalElements();

        List<CommentDTO> dtoList = new LinkedList<>();
        for (CommentEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }

        return new PageImpl<>(dtoList, paging, totalElement);
    }


    public CommentDTO toDTO(CommentEntity entity) {
        CommentDTO dto = new CommentDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setArticleId(entity.getArticleId());
        dto.setProfileId(entity.getProfileId());
        return dto;
    }
}
