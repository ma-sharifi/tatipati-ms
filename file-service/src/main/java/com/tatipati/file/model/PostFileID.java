package com.tatipati.file.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since ۶/۲۰/۲۰۲۰
 */
//https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
//https://thoughts-on-java.org/many-relationships-additional-properties/
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostFileID extends RepresentationModel<PostFileID> {
    private Long postID;
    private Long fileID;
}
