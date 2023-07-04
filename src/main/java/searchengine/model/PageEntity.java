package searchengine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "page", indexes = {@Index(name = "path_list", columnList = "path")})
@Setter
@Getter
@NoArgsConstructor
public class PageEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "site_id", referencedColumnName = "id")
    private SiteEntity siteId;
    @Column(length = 1000, columnDefinition = "VARCHAR(515)", nullable = false)
    private String path;

    private int code;
    @Column(length = 16777215, columnDefinition = "mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci")
    private String content;


    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    private List<IndexEntity> index = new ArrayList<>();

    public PageEntity(SiteEntity siteId, String path, int code, String content) {
        this.siteId = siteId;
        this.path = path;
        this.code = code;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageEntity that = (PageEntity) o;
        return id == that.id && code == that.code &&
                siteId.equals(that.siteId) &&
                path.equals(that.path) &&
                content.equals(that.content) &&
                index.equals(that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, siteId, path, code, content, index);
    }

    @Override
    public String toString() {
        return "PageEntity{" +
                "id=" + id +
                ", siteId=" + siteId +
                ", path='" + path + '\'' +
                ", code=" + code +
                ", content='" + content + '\'' +
                ", index=" + index +
                '}';
    }
}
