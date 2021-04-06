package hackaton.crazy_devs.animals_without_owners.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

    @Column
    private String shelterName;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String breed;

    @Column
    private String sex;

    @Column
    private int age;

    @Column
    private String color;

    @Column
    private int numAviary; //№ вольера

    @Column
    private String dateSterilization;

    @ManyToOne
    private Owner newOwner;

    @ManyToMany
    private List<Health> health;

    @Column
    private Long idMark;

    @OneToOne
    private Contract contract;

    @Column
    private boolean socialized;

    public void setShelterName(Shelter shelter) {
        this.shelterName = shelter.getNameShelter();
    }
}
