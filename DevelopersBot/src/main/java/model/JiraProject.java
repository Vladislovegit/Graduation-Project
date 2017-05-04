package model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "jira_project", catalog = "bot")
public class JiraProject {
    private Long id;
    private Long jiraId;
    private String name;
    private String description;
    private String key;
    private ApplicationProject applicationProject;
    private Calendar lastUpdate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jira_project_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String descriprion) {
        this.description = descriprion;
    }

    @Column(name = "key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Column(name = "jira_id")
    public Long getJiraId() {
        return jiraId;
    }

    public void setJiraId(Long jiraId) {
        this.jiraId = jiraId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "application_project_id")
    public ApplicationProject getApplicationProject() {
        return applicationProject;
    }

    public void setApplicationProject(ApplicationProject applicationProject) {
        this.applicationProject = applicationProject;
    }

    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Calendar lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
