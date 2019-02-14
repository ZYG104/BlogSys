package pers.zhuye.blogsys.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "article_info", schema = "blogweb", catalog = "")
public class ArticleInfoEntity {
    private long id;
    private String authorUsername;
    private String articleTitle;
    private String articleClassification;
    private String articleContent;
    private String pictureUrl;
    private byte whetherPublish;
    private Timestamp dateOfPublication;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "author_username")
    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    @Basic
    @Column(name = "article_title")
    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Basic
    @Column(name = "article_classification")
    public String getArticleClassification() {
        return articleClassification;
    }

    public void setArticleClassification(String articleClassification) {
        this.articleClassification = articleClassification;
    }

    @Basic
    @Column(name = "article_content")
    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Basic
    @Column(name = "picture_url")
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Basic
    @Column(name = "whether_publish")
    public byte getWhetherPublish() {
        return whetherPublish;
    }

    public void setWhetherPublish(byte whetherPublish) {
        this.whetherPublish = whetherPublish;
    }

    @Basic
    @Column(name = "date_of_publication")
    public Timestamp getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Timestamp dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleInfoEntity that = (ArticleInfoEntity) o;

        if (id != that.id) return false;
        if (whetherPublish != that.whetherPublish) return false;
        if (authorUsername != null ? !authorUsername.equals(that.authorUsername) : that.authorUsername != null)
            return false;
        if (articleTitle != null ? !articleTitle.equals(that.articleTitle) : that.articleTitle != null) return false;
        if (articleClassification != null ? !articleClassification.equals(that.articleClassification) : that.articleClassification != null)
            return false;
        if (articleContent != null ? !articleContent.equals(that.articleContent) : that.articleContent != null)
            return false;
        if (pictureUrl != null ? !pictureUrl.equals(that.pictureUrl) : that.pictureUrl != null) return false;
        if (dateOfPublication != null ? !dateOfPublication.equals(that.dateOfPublication) : that.dateOfPublication != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (authorUsername != null ? authorUsername.hashCode() : 0);
        result = 31 * result + (articleTitle != null ? articleTitle.hashCode() : 0);
        result = 31 * result + (articleClassification != null ? articleClassification.hashCode() : 0);
        result = 31 * result + (articleContent != null ? articleContent.hashCode() : 0);
        result = 31 * result + (pictureUrl != null ? pictureUrl.hashCode() : 0);
        result = 31 * result + (int) whetherPublish;
        result = 31 * result + (dateOfPublication != null ? dateOfPublication.hashCode() : 0);
        return result;
    }
}
