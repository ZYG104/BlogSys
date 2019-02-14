package pers.zhuye.blogsys.entities;

import javax.persistence.*;

@Entity
@Table(name = "file_info", schema = "blogweb", catalog = "")
public class FileInfoEntity {
    private int id;
    private String fileName;
    private String fileMd5;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "file_md5")
    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileInfoEntity that = (FileInfoEntity) o;

        if (id != that.id) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (fileMd5 != null ? !fileMd5.equals(that.fileMd5) : that.fileMd5 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (fileMd5 != null ? fileMd5.hashCode() : 0);
        return result;
    }
}
