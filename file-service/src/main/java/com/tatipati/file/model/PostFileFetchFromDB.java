package com.tatipati.file.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since ۶/۱۱/۲۰۲۰
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostFileFetchFromDB extends RepresentationModel<PostFileFetchFromDB> {
    private Long postID;
    private Long fileID;
}
