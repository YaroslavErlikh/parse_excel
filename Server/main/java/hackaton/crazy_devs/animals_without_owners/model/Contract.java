package hackaton.crazy_devs.animals_without_owners.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String nameContract;

    @Column
    private Date date;

    @OneToOne
    private Animal animal;

    @ManyToOne
    private Owner owner;
}
