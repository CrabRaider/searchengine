package searchengine.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Setter
@Getter
@Table(name = "site")
public class SiteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "status_time")
    private Date statusTime;

    @Column(name = "last_error")
    private String lastError;

    private String url;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "siteId", cascade = CascadeType.ALL)
    protected List<PageEntity> pageEntityList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "siteEntityId", cascade = CascadeType.ALL)
    protected List<LemmaEntity> lemmaEntityList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiteEntity that = (SiteEntity) o;
        return id == that.id && status == that.status &&
                statusTime.equals(that.statusTime) &&
                Objects.equals(lastError, that.lastError) &&
                url.equals(that.url) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, statusTime, lastError, url, name);
    }

    @Override
    public String toString() {
        return "SiteEntity{" +
                "id=" + id +
                ", status=" + status +
                ", statusTime=" + statusTime +
                ", lastError='" + lastError + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
