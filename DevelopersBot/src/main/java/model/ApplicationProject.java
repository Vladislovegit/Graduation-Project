package model;

import javax.persistence.*;

@Entity
@Table(name = "application_project", catalog = "bot")
public class ApplicationProject {
    private Long id;
    private String name;
    private TelegramLead lead;

    public ApplicationProject() {
    }

    public ApplicationProject(Long id, String name, TelegramLead lead) {
        this.id = id;
        this.name = name;
        this.lead = lead;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_project_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "telegram_lead_id")
    public TelegramLead getLead() {
        return lead;
    }

    public void setLead(TelegramLead lead) {
        this.lead = lead;
    }

    @Override
    public String toString() {
        return "ApplicationProject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lead=" + lead +
                '}';
    }
}
