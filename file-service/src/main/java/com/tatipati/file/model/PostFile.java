//package com.tatipati.file.model;
//
//import com.google.gson.annotations.SerializedName;
//import com.tatipati.file.util.serializer.GsonExcludeField;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.hateoas.RepresentationModel;
//
//import javax.persistence.*;
//import javax.xml.bind.annotation.XmlElement;
//import java.util.Date;
//
//
///**
// * @author Mahdi Sharifi
// * @version 1.0.0
// * https://www.linkedin.com/in/mahdisharifi/
// * @since ۶/۲۰/۲۰۲۰
// */
//@Getter  @Setter
//public class PostFile extends RepresentationModel<PostFile> {
//
//    // ======================================
//    // =             Attributes             =
//    // ======================================
//    private PostFileID id;
//    private Long mobileNo;
//
//    protected Date updatedAt;
//
//    private double indexNo;
//
//    @GsonExcludeField
//    private Long post;
//
//    @GsonExcludeField
//    private Long file;
//
//
//    public PostFile(PostFileID id) {
//        this.id = id;
//    }
//
//    //    public PostFile(long postID, long fileID) {
////        id.setPostID(postID);
////        id.setFileID(fileID);
////    }
////  public PostFile(Post post, FileStub file) {
////        this.id=new PostFileID(post.getId(),file.getId());
////        this.post=post;
////        this.file=file;
////    }
//
//    public PostFile(Post post, FileStub file) {
//        this.post = post;
//        this.file = file;
//        this.id = new PostFileID(post.getId(), file.getId());
//        this.mobileNo = post.getMobileNo();
////        this.next.setPostID(nextPostID);
////        this.next.setFileID(nextFileID);
////        linklist=new LinklistEmbedded(prevID,nextID);
//        post.getFiles().add(this);
////        video.get
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//
//        if (o == null || getClass() != o.getClass()) return false;
//
//        com.tatipati.entity.comment.PostFile postFile = (com.tatipati.entity.comment.PostFile) o;
//
//        return new EqualsBuilder()
//                .appendSuper(super.equals(o))
//                .append(id, postFile.id)
//                .isEquals();
//    }
//
//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder(17, 37)
//                .appendSuper(super.hashCode())
//                .append(id)
//
////                .append(post)
////                .append(file)
//                .toHashCode();
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("");
////        sb.append("prev= ").append(prev);
//        sb.append(" ,id= ").append(id);
//        sb.append(" ,indexNo= ").append(indexNo);
////        sb.append(" ,next= ").append(next);
//        return sb.toString();
//    }
//
//    public String toStringVerbose() {
//        return "Node:" +
//                " id=" + id ;
////                " ,next=" + next +
////                " ,prev=" + prev;
//    }
//}
