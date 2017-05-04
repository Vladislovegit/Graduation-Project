package model;

import javax.persistence.*;

@Entity
@Table(name = "telegram_group", catalog = "bot")
public class TelegramGroup {
    private Long id;
    private Long telegramId;
    private String name;
    private ApplicationProject project;
    private Credentials jiraCreds;
    private Credentials teamcityCreds;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "telegram_group_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "telegram_group_chat_id")
    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
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
    public ApplicationProject getProject() {
        return project;
    }

    public void setProject(ApplicationProject project) {
        this.project = project;
    }

    @ManyToOne
    @JoinColumn(name = "jira_credentials_id")
    public Credentials getJiraCreds() {
        return jiraCreds;
    }

    public void setJiraCreds(Credentials jiraCreds) {
        this.jiraCreds = jiraCreds;
    }

    @ManyToOne
    @JoinColumn(name = "teamcity_credentials_id")
    public Credentials getTeamcityCreds() {
        return teamcityCreds;
    }

    public void setTeamcityCreds(Credentials teamcityCreds) {
        this.teamcityCreds = teamcityCreds;
    }

    @Override
    public String toString() {
        return "TelegramGroup{" +
                "id=" + id +
                ", telegramId=" + telegramId +
                ", name='" + name + '\'' +
                ", project=" + project +
                ", jiraCreds=" + jiraCreds +
                ", teamcityCreds=" + teamcityCreds +
                '}';
    }
}
