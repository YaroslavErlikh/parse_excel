package hackaton.crazy_devs.animals_without_owners.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Shelters")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String nameShelter;

    @Column
    private String address;

    @Column
    private String operationOrganization; //эксплуатирующая организация

    @Column
    private String director;
}
