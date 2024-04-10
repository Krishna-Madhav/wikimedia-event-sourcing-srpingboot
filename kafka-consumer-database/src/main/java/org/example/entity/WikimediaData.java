package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wikimedia_recent_change")
@Data
public class WikimediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String wikiEventData;

}
