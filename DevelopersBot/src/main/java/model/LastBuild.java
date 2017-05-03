package model;

import javax.persistence.*;

@Entity
@Table(name = "last_build", catalog = "bot")
public class LastBuild {
    private Long buildTypeId;
    private ProjectConfiguration projectConfiguration;
    private String teamCityId;
    private String status;
    private String message;
    private Boolean notified;

    @Id
    @Column(name = "build_type_id")
    public Long getBuildTypeId() {
        return buildTypeId;
    }

    public void setBuildTypeId(Long buildTypeId) {
        this.buildTypeId = buildTypeId;
    }

    @OneToOne
    @JoinColumn(name = "build_type_id")
    public ProjectConfiguration getProjectConfiguration() {
        return projectConfiguration;
    }

    public void setProjectConfiguration(ProjectConfiguration projectConfiguration) {
        this.projectConfiguration = projectConfiguration;
    }

    @Column(name = "teamcity_id")
    public String getTeamCityId() {
        return teamCityId;
    }

    public void setTeamCityId(String teamCityId) {
        this.teamCityId = teamCityId;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name = "notified")
    public Boolean getNotified() {
        return notified;
    }

    public void setNotified(Boolean notified) {
        this.notified = notified;
    }
}
