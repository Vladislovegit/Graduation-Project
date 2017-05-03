package model;

import javax.persistence.*;

@Entity
@Table(name = "teamcity_project_configuration", catalog = "bot")
public class ProjectConfiguration {
    private Long id;
    private String teamcityId;
    private String name;
    private TeamCityProject teamCityProject;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teamcity_project_configuration_id")
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

    @ManyToOne
    @JoinColumn(name = "teamcity_project_id")
    public TeamCityProject getTeamCityProject() {
        return teamCityProject;
    }

    public void setTeamCityProject(TeamCityProject teamCityProject) {
        this.teamCityProject = teamCityProject;
    }

    @Override
    public String toString() {
        return "ProjectConfiguration{" +
                "id=" + id +
                ", teamcityId='" + teamcityId + '\'' +
                ", name='" + name + '\'' +
                ", teamCityProject=" + teamCityProject +
                '}';
    }
}
