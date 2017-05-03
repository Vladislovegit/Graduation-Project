package model;

import javax.persistence.*;

@Entity
@Table(name = "telegram_lead", catalog = "bot")
public class TelegramLead {
    private Long id;
    private String name;
    private Long telegramId;

    public TelegramLead() {
    }

    public TelegramLead(Long id, String name, Long telegramId) {
        this.id = id;
        this.name = name;
        this.telegramId = telegramId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "telegram_lead_id")
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

    @Column(name = "telegram_id")
    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    @Override
    public String toString() {
        return "TelegramLead{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telegramId=" + telegramId +
                '}';
    }
}
