package model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "teamcity_project", catalog = "bot")
public class TeamCityProject {
    private Long id;
    private String teamcityId;
    private String name;
    private String description;
    private ApplicationProject applicationProject;
    private Credentials credentials;
    private Calendar lastUpdate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teamcity_project_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "teamcity_id")
    public String getTeamcityId() {
        return teamcityId;
    }

    public void setTeamcityId(String teamcityId) {
        this.teamcityId = teamcityId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "application_project_id")
    public ApplicationProject getApplicationProject() {
        return applicationProject;
    }

    public void setApplicationProject(ApplicationProject applicationProject) {
        this.applicationProject = applicationProject;
    }

    @ManyToOne
    @JoinColumn(name = "credentials_id")
    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Calendar lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "TeamCityProject{" +
                "id=" + id +
                ", teamcityId='" + teamcityId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", applicationProject=" + applicationProject +
                ", credentials=" + credentials +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}