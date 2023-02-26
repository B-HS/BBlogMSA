package dev.hyns.article.Controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hyns.article.DTO.ArticleDTO;
import dev.hyns.article.Service.ArticleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("article")
public class ArticleController {
    private final ArticleService aser;

    @PostMapping(value = "write", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> articleCreate(@RequestBody ArticleDTO dto) {
        return new ResponseEntity<>(aser.articleCreate(dto), HttpStatus.OK);
    }

    @PostMapping(value = "read")
    public ResponseEntity<ArticleDTO> articleRead(@RequestBody ArticleDTO dto) {
        return new ResponseEntity<>(aser.articleRead(dto.getAid()), HttpStatus.OK);
    }

    @PostMapping(value = "update")
    public ResponseEntity<Long> articleUpdate(@RequestBody ArticleDTO dto) {
        return new ResponseEntity<>(aser.articleUpdate(dto), HttpStatus.OK);
    }

    @PostMapping(value = "delete")
    public ResponseEntity<Boolean> articleDelete(@RequestBody ArticleDTO dto) {
        return new ResponseEntity<>(aser.articleDelete(dto.getAid()), HttpStatus.OK);
    }

    @PostMapping(value = "list/{page}/{size}")
    public ResponseEntity<List<ArticleDTO>> articleList(
            @PathVariable Integer page,
            @PathVariable Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "aid"));
        return new ResponseEntity<>(aser.articleList(pageable), HttpStatus.OK);
    }

}
