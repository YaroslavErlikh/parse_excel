package hackaton.crazy_devs.animals_without_owners.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String surname; //фамилия

    @Column
    private String name;

    @Column
    private String patronymic; //отчество

    @OneToMany
    private List<Animal> animals;

    @OneToMany
    private List<Contract> contracts;
}
