package model;

import javax.persistence.*;

@Entity
@Table(name = "telegram_group", catalog = "bot")
public class TelegramGroup {
    private Long id;
    private Long telegramId;
    private String name;
    private ApplicationProject project;
    private boolean isAuthenticatedInJira;
    private boolean isAuthenticatedInTeamCity;

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

    @Column(name = "is_authenticated_in_jira")
    public boolean isAuthenticatedInJira() {
        return isAuthenticatedInJira;
    }

    public void setAuthenticatedInJira(boolean authenticatedInJira) {
        isAuthenticatedInJira = authenticatedInJira;
    }

    @Column(name = "is_authenticated_in_teamcity")
    public boolean isAuthenticatedInTeamCity() {
        return isAuthenticatedInTeamCity;
    }

    public void setAuthenticatedInTeamCity(boolean authenticatedInTeamCity) {
        isAuthenticatedInTeamCity = authenticatedInTeamCity;
    }

    @Override
    public String toString() {
        return "TelegramGroup{" +
                "id=" + id +
                ", telegramId=" + telegramId +
                ", name='" + name + '\'' +
                ", project=" + project +
                ", isAuthenticatedInJira=" + isAuthenticatedInJira +
                ", isAuthenticatedInTeamCity=" + isAuthenticatedInTeamCity +
                '}';
    }
}
