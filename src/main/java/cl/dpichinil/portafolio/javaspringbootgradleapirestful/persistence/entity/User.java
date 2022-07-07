package cl.dpichinil.portafolio.javaspringbootgradleapirestful.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@Data
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY )
    @Column(name = "user_id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "created_date", insertable = true)
    private Date createdDate;

    @Column(name = "modified_date", updatable = true)
    private Date modifiedDate;

    @Column(name = "last_login", updatable = true)
    private Date lastLogin;

    @Column(name = "token")
    private String token;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy="user", cascade = CascadeType.REMOVE)
    private List<Phone> listPhone;



}
